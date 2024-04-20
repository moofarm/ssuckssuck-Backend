package moofarm.ssuckssuck.domain.image.exception;

import moofarm.ssuckssuck.global.error.exception.ErrorCode;
import moofarm.ssuckssuck.global.error.exception.SsuckException;

public class FileEmptyException extends SsuckException {

    public static final FileEmptyException EXCEPTION = new FileEmptyException();

    private FileEmptyException() {
        super(ErrorCode.BAD_FILE_EXTENSION);
    }
}
