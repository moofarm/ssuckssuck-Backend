package moofarm.ssuckssuck.domain.image.exception;

import moofarm.ssuckssuck.global.error.exception.ErrorCode;
import moofarm.ssuckssuck.global.error.exception.SsuckException;

public class FileSizeException extends SsuckException {

    public static final FileSizeException EXCEPTION = new FileSizeException();

    private FileSizeException() {
        super(ErrorCode.IMAGE_SIZE_EXTENSION);
    }
}
