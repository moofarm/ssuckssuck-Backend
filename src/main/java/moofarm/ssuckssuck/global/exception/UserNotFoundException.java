package moofarm.ssuckssuck.global.exception;

import moofarm.ssuckssuck.global.error.exception.ErrorCode;
import moofarm.ssuckssuck.global.error.exception.SsuckException;

public class UserNotFoundException extends SsuckException {

    public static final SsuckException EXCEPTION = new UserNotFoundException();

    private UserNotFoundException() {
        super(ErrorCode.REGISTER_EXPIRED_TOKEN);
    }}
