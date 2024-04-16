package moofarm.ssuckssuck.domain.group.exception;

import moofarm.ssuckssuck.global.error.exception.ErrorCode;
import moofarm.ssuckssuck.global.error.exception.SsuckException;

public class GroupNotFoundException extends SsuckException {
    public static final SsuckException EXCEPTION = new GroupNotFoundException();

    private GroupNotFoundException() {
        super(ErrorCode.GROUP_NOT_FOUND);
    }
}
