package moofarm.ssuckssuck.domain.image.exception;

import moofarm.ssuckssuck.global.error.exception.ErrorCode;
import moofarm.ssuckssuck.global.error.exception.SsuckException;

public class BadFileExtensionException extends SsuckException {

    public static final BadFileExtensionException EXCEPTION = new BadFileExtensionException();

    private BadFileExtensionException() {
        super(ErrorCode.MISSION_NOT_FOUND);
    }
}
