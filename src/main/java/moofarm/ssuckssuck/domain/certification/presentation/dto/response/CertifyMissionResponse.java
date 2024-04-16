package moofarm.ssuckssuck.domain.certification.presentation.dto.response;

import moofarm.ssuckssuck.domain.certification.domain.vo.CertificationinfoVO;
import moofarm.ssuckssuck.domain.user.domain.vo.UserInfoVO;

public record CertifyMissionResponse(
        String certificationImage,
        int likeCount
) {
    public CertifyMissionResponse(CertificationinfoVO certificationinfoVO) {
        this(certificationinfoVO.certificationImage(), certificationinfoVO.likeCount());
    }
}
