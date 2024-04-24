package moofarm.ssuckssuck.domain.misson.exception;

import moofarm.ssuckssuck.global.error.exception.ErrorCode;
import moofarm.ssuckssuck.global.error.exception.SsuckException;

public class DuplicateParticipationException extends SsuckException {

    public static final DuplicateParticipationException EXCEPTION = new DuplicateParticipationException();

    private DuplicateParticipationException() {
        super(ErrorCode.DUPLICATION_PARTICIPATION_GROUP);
    }
}
