package moofarm.ssuckssuck.domain.certificationLike.presentation.dto.response;

import moofarm.ssuckssuck.domain.certificationLike.domain.vo.CertificationLikeInfoVO;

public record CertificationLikeResponse (
        Long certificationLikeId,
        Long certificationId,
        boolean likeStatus
){
    public CertificationLikeResponse(CertificationLikeInfoVO certificationLikeInfoVO) {
        this(certificationLikeInfoVO.id(), certificationLikeInfoVO.certificationId(), certificationLikeInfoVO.likeStatus());
    }
}
