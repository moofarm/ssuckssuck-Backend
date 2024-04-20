package moofarm.ssuckssuck.domain.image.exception;

import moofarm.ssuckssuck.global.error.exception.ErrorCode;
import moofarm.ssuckssuck.global.error.exception.SsuckException;

public class FileUploadFailException extends SsuckException {

    public static final FileUploadFailException EXCEPTION = new FileUploadFailException();

    private FileUploadFailException() {
        super(ErrorCode.FILE_UPLOAD_FAIL);
    }
}
