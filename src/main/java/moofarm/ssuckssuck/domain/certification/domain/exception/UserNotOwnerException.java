package moofarm.ssuckssuck.domain.certification.domain.exception;

import moofarm.ssuckssuck.global.error.exception.ErrorCode;
import moofarm.ssuckssuck.global.error.exception.SsuckException;

public class UserNotOwnerException extends SsuckException {
    public static final SsuckException EXCEPTION = new UserNotOwnerException();

    private UserNotOwnerException() {
        super(ErrorCode.NOT_OWNER_CERTIFICATION);
    }
}
