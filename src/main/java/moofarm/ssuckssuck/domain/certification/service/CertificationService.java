package moofarm.ssuckssuck.domain.certification.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import moofarm.ssuckssuck.domain.certification.domain.Certification;
import moofarm.ssuckssuck.domain.certification.domain.repository.CertificationRepository;
import moofarm.ssuckssuck.domain.certification.presentation.dto.request.CertifyMssionRequest;
import moofarm.ssuckssuck.domain.certification.presentation.dto.response.CertifyMissionResponse;
import moofarm.ssuckssuck.domain.user.domain.User;
import moofarm.ssuckssuck.global.utils.user.UserUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class CertificationService {
    private final CertificationRepository certificationRepository;
    private final UserUtils userUtils;
    //미션 인증
    CertifyMissionResponse createCertification(CertifyMssionRequest certifyMssionRequest) {
        User user = userUtils.getUserFromSecurityContext();
        Certification certification = Certification.createCertification(
                user,
                certifyMssionRequest.certificationImage(),
                certifyMssionRequest.likeCount()
        );
        certificationRepository.save(certification);
        return new CertifyMissionResponse(
                certification.getCertificationInfo()
        );
    }
    // 미션 인증 수정
    public CertifyMissionResponse updateCertificaiton(Long certificationId, CertifyMssionRequest certifyMssionRequest) {
        User user = userUtils.getUserFromSecurityContext();
        Certification certification = certificationRepository.findById(certificationId)
                .orElseThrow(() -> new IllegalArgumentException("해당 미션 인증이 존재하지 않습니다."));
        certification.createCertification(
                user,
                certifyMssionRequest.certificationImage(),
                certifyMssionRequest.likeCount()
        );
        return new CertifyMissionResponse(
                certification.getCertificationInfo()
        );
    }
    //미션 인증 리스트 조회
    // 미션 인증 삭제
}
