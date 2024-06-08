package moofarm.ssuckssuck.domain.certificationLike.exception;

import moofarm.ssuckssuck.domain.certification.domain.exception.CertificationNotFoundException;
import moofarm.ssuckssuck.global.error.exception.ErrorCode;
import moofarm.ssuckssuck.global.error.exception.SsuckException;

public class CertificationLikeNotFoundException extends SsuckException {
    public static final SsuckException EXCEPTION = new CertificationNotFoundException();

    private CertificationLikeNotFoundException() {
        super(ErrorCode.CERTIFICATION_LIKE_NOT_FOUND);
    }
}
