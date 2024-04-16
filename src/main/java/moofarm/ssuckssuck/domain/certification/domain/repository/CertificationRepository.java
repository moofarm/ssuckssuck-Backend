package moofarm.ssuckssuck.domain.certification.domain.repository;

import moofarm.ssuckssuck.domain.certification.domain.Certification;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.repository.JpaRepository;


public interface CertificationRepository extends JpaRepository<Certification, Long> {

    // 미션 인증 수정
    //미션 인증 리스트 조회
    //Slice<Certification> findAllByGroup(Group group, Pageable pageable);
    // 미션 인증 삭제
}
