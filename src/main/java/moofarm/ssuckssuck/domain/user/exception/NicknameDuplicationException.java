package moofarm.ssuckssuck.domain.user.exception;

import moofarm.ssuckssuck.global.error.exception.ErrorCode;
import moofarm.ssuckssuck.global.error.exception.SsuckException;

public class NicknameDuplicationException extends SsuckException {
    public static final SsuckException EXCEPTION = new NicknameDuplicationException();

    private NicknameDuplicationException() {
        super(ErrorCode.NICKNAME_DUPLICATION);
    }
}
