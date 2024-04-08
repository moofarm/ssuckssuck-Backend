package moofarm.ssuckssuck.global.exception;

import moofarm.ssuckssuck.global.error.exception.ErrorCode;
import moofarm.ssuckssuck.global.error.exception.SsuckException;

public class InconsistencyCategoryException extends SsuckException {

    public static final SsuckException EXCEPTION = new InconsistencyCategoryException();

    private InconsistencyCategoryException() {
        super(ErrorCode.INCONSISTENCY_CATEGORY);
    }
}
