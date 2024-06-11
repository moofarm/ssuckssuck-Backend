package moofarm.ssuckssuck.domain.certificationLike.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import moofarm.ssuckssuck.domain.certification.domain.Certification;
import moofarm.ssuckssuck.domain.certification.service.CertificationServiceUtils;
import moofarm.ssuckssuck.domain.certificationLike.domain.CertificationLike;
import moofarm.ssuckssuck.domain.certificationLike.domain.repository.CertificationLikeRepository;
import moofarm.ssuckssuck.domain.certificationLike.exception.CertificationLikeNotFoundException;
import moofarm.ssuckssuck.domain.certificationLike.presentation.dto.request.CreateCertificationLikeRequest;
import moofarm.ssuckssuck.domain.certificationLike.presentation.dto.response.CertificationLikeResponse;
import moofarm.ssuckssuck.domain.user.domain.User;
import moofarm.ssuckssuck.global.utils.user.UserUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class CertificationLikeService {

    private final CertificationLikeRepository certificationLikeRepository;
    private final CertificationServiceUtils certificationServiceUtils;
    private final UserUtils userUtils;


    // 미션 인증 좋아요 상태 변경
    @Transactional
    public CertificationLikeResponse createCertificationLike(Long certificationId, CreateCertificationLikeRequest createCertificationLikeRequest) {
        User user = userUtils.getUserFromSecurityContext();
        Certification certification = certificationServiceUtils.queryCertification(certificationId);

        CertificationLike certificationLike = certificationLikeRepository.findByCertificationAndUser(certification, user)
                .orElse(null);

        if (certificationLike != null) {
            certificationLike.updateLikeStatus(createCertificationLikeRequest.likeStatus());
        } else {
            certificationLike = CertificationLike.createCertificationLike(certification, user);
            certificationLikeRepository.save(certificationLike);
        }
        if (createCertificationLikeRequest.likeStatus()) {
            certification.increaseLikeCount();
        } else {
            certification.decreaseLikeCount();
        }
        return new CertificationLikeResponse(certificationLike.getCertificationLikeInfoVO());

    }


}
