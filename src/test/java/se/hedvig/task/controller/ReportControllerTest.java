package se.hedvig.task.controller;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import se.hedvig.task.dto.Event;
import se.hedvig.task.dto.Report;

import javax.annotation.Resource;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static se.hedvig.task.dto.EventType.*;


@ExtendWith(SpringExtension.class)
@SpringBootTest
class ReportControllerTest {

    @Resource
    private ReportController reportController;

    @Test
    public void testCase1() {
        List<Event> events = new ArrayList<>();
        Event event = new Event();
        event.setType(ContractCreatedEvent);
        event.setContactId(1L);
        event.setDate(LocalDate.of(2020, 1, 1));
        event.setValue(100);
        events.add(event);
        event = new Event();
        event.setType(ContractCreatedEvent);
        event.setContactId(2L);
        event.setDate(LocalDate.of(2020, 2, 1));
        event.setValue(100);
        events.add(event);
        event = new Event();
        event.setType(ContractTerminatedEvent);
        event.setContactId(1L);
        event.setDate(LocalDate.of(2020, 3, 31));
        events.add(event);
        event = new Event();
        event.setType(ContractTerminatedEvent);
        event.setContactId(2L);
        event.setDate(LocalDate.of(2020, 4, 30));
        events.add(event);
        Report report = reportController.generateReport(events);
        assertNotNull(report);
        assertEquals(12, report.getColumns().size());
        //TODO: assert field by fields
    }

    @Test
    public void testCase2() {
        List<Event> events = new ArrayList<>();
        Event event = new Event();
        event.setType(ContractCreatedEvent);
        event.setContactId(1L);
        event.setDate(LocalDate.of(2020, 1, 1));
        event.setValue(100);
        events.add(event);
        event = new Event();
        event.setType(PriceIncreasedEvent);
        event.setContactId(1L);
        event.setDate(LocalDate.of(2020, 2, 1));
        event.setValue(100);
        events.add(event);
        event = new Event();
        event.setType(PriceDecreasedEvent);
        event.setContactId(1L);
        event.setDate(LocalDate.of(2020, 3, 1));
        event.setValue(100);
        events.add(event);
        event = new Event();
        event.setType(ContractTerminatedEvent);
        event.setContactId(1L);
        event.setDate(LocalDate.of(2020, 4, 30));
        events.add(event);
        Report report = reportController.generateReport(events);
        assertNotNull(report);
        assertEquals(12, report.getColumns().size());
        //TODO: assert field by fields
    }


}