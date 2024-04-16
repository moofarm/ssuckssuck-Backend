package moofarm.ssuckssuck.domain.misson.exception;

import moofarm.ssuckssuck.global.error.exception.ErrorCode;
import moofarm.ssuckssuck.global.error.exception.SsuckException;

public class UserIsNotMissionHostException extends SsuckException {

    public static final UserIsNotMissionHostException EXCEPTION = new UserIsNotMissionHostException();

    private UserIsNotMissionHostException() {
        super(ErrorCode.NOT_HOST_MISSION);
    }
}
