package moofarm.ssuckssuck.domain.report.presentation;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import moofarm.ssuckssuck.domain.report.presentation.dto.request.CreateReportRequest;
import moofarm.ssuckssuck.domain.report.presentation.dto.request.ProcessReportRequest;
import moofarm.ssuckssuck.domain.report.service.ReportService;
import org.springframework.web.bind.annotation.*;

@Tag(name = "신고", description = "신고 관련 API")
@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class ReportController {

    private final ReportService reportService;

    @Operation(summary = "신고하기")
    @PostMapping("/report")
    public void createReport(@Valid @RequestBody CreateReportRequest createReportRequest) {
        reportService.createReport(createReportRequest);
    }

    @Operation(summary = "신고 처리")
    @PostMapping("/process")
    public void processUser(@Valid @RequestBody ProcessReportRequest processReportRequest) {
        reportService.processUser(processReportRequest);
    }
}
