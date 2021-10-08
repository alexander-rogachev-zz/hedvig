package se.hedvig.task.service;

import org.springframework.stereotype.Service;
import se.hedvig.task.dto.Column;
import se.hedvig.task.dto.Contract;
import se.hedvig.task.dto.Event;
import se.hedvig.task.dto.Report;

import java.time.Month;
import java.time.format.TextStyle;
import java.util.*;

import static se.hedvig.task.dto.EventType.ContractTerminatedEvent;

@Service
public class ReportServiceImpl implements ReportService {

    @Override
    public Report generateReport(List<Event> events) {
        Map<Month, List<Contract>> monthContacts = calculateContractsByMonths(events);
        return createReport(monthContacts);
    }

    private Report createReport(Map<Month, List<Contract>> monthContacts) {
        Report report = new Report();
        int agwp = 0;
        int egwp = 0;
        for (Month month : Month.values()) {
            Column column = new Column();
            column.setMonth(month.getDisplayName(TextStyle.SHORT, Locale.ENGLISH));
            List<Contract> mContacts = monthContacts.get(month);
            column.setNumberOfContacts(mContacts.size());
            egwp = agwp + mContacts.stream().map(Contract::getValue).reduce(0, Integer::sum) * (12 - (month.getValue() - 1));
            agwp = agwp + mContacts.stream().map(Contract::getValue).reduce(0, Integer::sum);
            column.setAGWP(agwp);
            column.setEGWP(egwp);
            report.getColumns().add(column);
        }
        return report;
    }

    private Map<Month, List<Contract>> calculateContractsByMonths(List<Event> events) {
        Map<Month, List<Contract>> monthContacts = new HashMap<>();

        Map<Long, Contract> contracts = new HashMap<>();
        for (Month month : Month.values()) {
            events.stream().sorted(Comparator.comparing(Event::getDate))
                    .forEach(event -> {
                        if (event.getDate().getMonth().equals(month) && !ContractTerminatedEvent.equals(event.getType())
                                || isTerminateEventFromPreviousMonth(event, month)) {
                            Contract contract = contracts.get(event.getContactId());
                            switch (event.getType()) {
                                case ContractCreatedEvent: {
                                    contracts.put(event.getContactId(), new Contract(event.getContactId(), event.getValue()));
                                    break;
                                }
                                case ContractTerminatedEvent: {
                                    if (contract != null) {
                                        contracts.remove(contract.getId());
                                    }
                                    break;
                                }
                                case PriceDecreasedEvent:
                                    if (contract != null) {
                                        contracts.put(contract.getId(), new Contract(contract.getId(), contract.getValue() - event.getValue()));
                                    }
                                    break;
                                case PriceIncreasedEvent:
                                    if (contract != null) {
                                        contracts.put(contract.getId(), new Contract(contract.getId(), contract.getValue() + event.getValue()));
                                    }
                                    break;
                                default:
                                    System.err.println("Unknown event type = " + event.getType());
                                    break;
                            }
                        }
                    });
            monthContacts.put(month, new ArrayList<>(contracts.values()));
        }
        return monthContacts;
    }

    private boolean isTerminateEventFromPreviousMonth(Event event, Month month) {
        return  event.getDate().getMonth().getValue() == month.getValue() - 1 && ContractTerminatedEvent.equals(event.getType());
    }

}
