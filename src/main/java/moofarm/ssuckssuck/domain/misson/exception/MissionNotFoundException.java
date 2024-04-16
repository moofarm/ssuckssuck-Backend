package moofarm.ssuckssuck.domain.misson.exception;

import moofarm.ssuckssuck.global.error.exception.ErrorCode;
import moofarm.ssuckssuck.global.error.exception.SsuckException;

public class MissionNotFoundException extends SsuckException {

    public static final MissionNotFoundException EXCEPTION = new MissionNotFoundException();

    private MissionNotFoundException() {
        super(ErrorCode.MISSION_NOT_FOUND);
    }
}
