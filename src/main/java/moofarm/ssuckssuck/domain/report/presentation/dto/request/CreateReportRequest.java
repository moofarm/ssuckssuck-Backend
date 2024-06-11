package moofarm.ssuckssuck.domain.report.presentation.dto.request;

import moofarm.ssuckssuck.domain.report.domain.ReportType;

public record CreateReportRequest(
        Long certificationId,
        String reportReason,
        ReportType reportType
) {
}
