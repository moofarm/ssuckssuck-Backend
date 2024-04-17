package moofarm.ssuckssuck.domain.certification.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import moofarm.ssuckssuck.domain.certification.domain.Certification;
import moofarm.ssuckssuck.domain.certification.domain.exception.CertificationNotFoundException;
import moofarm.ssuckssuck.domain.certification.domain.repository.CertificationRepository;
import moofarm.ssuckssuck.domain.certification.presentation.dto.request.CreateCertificationRequest;
import moofarm.ssuckssuck.domain.certification.presentation.dto.request.UpdateCertificationRequest;
import moofarm.ssuckssuck.domain.certification.presentation.dto.response.CertificationResponse;
import moofarm.ssuckssuck.domain.group.domain.Group;
import moofarm.ssuckssuck.domain.group.service.GroupServiceUtils;
import moofarm.ssuckssuck.domain.misson.domain.Mission;
import moofarm.ssuckssuck.domain.misson.service.MissionServiceUtils;
import moofarm.ssuckssuck.domain.user.domain.User;
import moofarm.ssuckssuck.global.utils.user.UserUtils;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class CertificationService implements CertificationServiceUtils{
    private final CertificationRepository certificationRepository;
    private final UserUtils userUtils;
    private final GroupServiceUtils groupServiceUtils;
    private final MissionServiceUtils missionServiceUtils;
    //미션 인증
    @Transactional
    public CertificationResponse createCertification(Long missionId, Long groupId, CreateCertificationRequest createCertificationRequest) {
        User user = userUtils.getUserFromSecurityContext();
        Mission mission = missionServiceUtils.queryMission(missionId);
        Group group = groupServiceUtils.queryGroup(groupId);

        Certification certification = Certification.createCertification(
                user,
                group,
                mission,
                createCertificationRequest.certificationImage()
        );

        certificationRepository.save(certification);

        mission.updateMissionStatus();

        return new CertificationResponse(certification.getCertificationInfoVO());
    }
    // 미션 인증 수정
    @Transactional
    public CertificationResponse updateCertification(Long id, UpdateCertificationRequest updateCertificationRequest) {
        User user = userUtils.getUserFromSecurityContext();
        Certification certification = queryCertification(id);

        certification.verifyOwner(user);
        certification.updateCertification(updateCertificationRequest.certificationImage());

        return new CertificationResponse(certification.getCertificationInfoVO());
    }

    //미션 인증 리스트 조회
    public Slice<CertificationResponse> getCertificationsByGroup(Long groupId, int pageNumber) {
        Pageable pageable = PageRequest.of(pageNumber, 10);
        Group group = groupServiceUtils.queryGroup(groupId);

        return certificationRepository.findAllByGroup(group, pageable).map(
                certification -> new CertificationResponse(certification.getCertificationInfoVO()));
    }
    // 미션 인증 삭제
    @Override
    @Transactional
    public void deleteCertification(Long id) {
        Certification certification = queryCertification(id);

        certificationRepository.delete(certification);
    }

    @Override
    public Certification queryCertification(Long id) {
        return certificationRepository.findById(id)
                .orElseThrow(() -> CertificationNotFoundException.EXCEPTION);
    }
}
