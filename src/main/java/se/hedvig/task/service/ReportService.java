package se.hedvig.task.service;

import se.hedvig.task.dto.Event;
import se.hedvig.task.dto.Report;

import java.util.List;

public interface ReportService {

    Report generateReport(List<Event> events);

}
