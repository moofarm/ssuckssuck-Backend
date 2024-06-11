package moofarm.ssuckssuck.domain.report.exception;

import moofarm.ssuckssuck.global.error.exception.ErrorCode;
import moofarm.ssuckssuck.global.error.exception.SsuckException;

public class ReportNotFoundException extends SsuckException {
    public static final SsuckException EXCEPTION = new ReportNotFoundException();

    private ReportNotFoundException() {
        super(ErrorCode.REPORT_NOT_FOUND);
    }
}
