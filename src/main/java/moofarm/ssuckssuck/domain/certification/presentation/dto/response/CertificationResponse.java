package moofarm.ssuckssuck.domain.certification.presentation.dto.response;

import moofarm.ssuckssuck.domain.certification.domain.vo.CertificationinfoVO;

public record CertificationResponse(
        Long certificationId,
        String certificationImage,
        int likeCount
) {
    public CertificationResponse(CertificationinfoVO certificationinfoVO) {
        this(certificationinfoVO.id(), certificationinfoVO.certificationImage(), certificationinfoVO.likeCount());
    }
}
