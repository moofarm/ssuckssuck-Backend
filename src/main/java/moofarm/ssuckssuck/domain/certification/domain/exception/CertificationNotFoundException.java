package moofarm.ssuckssuck.domain.certification.domain.exception;

import moofarm.ssuckssuck.global.error.exception.ErrorCode;
import moofarm.ssuckssuck.global.error.exception.SsuckException;

public class CertificationNotFoundException extends SsuckException {
    public static final SsuckException EXCEPTION = new CertificationNotFoundException();

    private CertificationNotFoundException() {
        super(ErrorCode.CERTIFICATION_NOT_FOUND);
    }
}

