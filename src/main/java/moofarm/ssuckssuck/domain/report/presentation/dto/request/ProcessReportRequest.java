package moofarm.ssuckssuck.domain.report.presentation.dto.request;

import moofarm.ssuckssuck.domain.report.domain.ProcessingStatus;

public record ProcessReportRequest(
        Long userId,
        Long reportId,
        ProcessingStatus processingStatus
) {
}
