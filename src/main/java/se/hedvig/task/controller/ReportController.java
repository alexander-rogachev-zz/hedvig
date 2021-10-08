package se.hedvig.task.controller;


import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import se.hedvig.task.dto.Event;
import se.hedvig.task.dto.Report;
import se.hedvig.task.service.ReportService;

import java.util.List;

@RestController
@RequestMapping("api/reports")
@RequiredArgsConstructor
public class ReportController {

    private final ReportService reportService;

    @PostMapping
    public Report generateReport(@RequestBody List<Event> events) {
        return reportService.generateReport(events);
    }

}
