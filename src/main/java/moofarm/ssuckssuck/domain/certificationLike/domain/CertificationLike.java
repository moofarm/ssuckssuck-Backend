package moofarm.ssuckssuck.domain.certificationLike.domain;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import moofarm.ssuckssuck.domain.certification.domain.Certification;
import moofarm.ssuckssuck.domain.certificationLike.domain.vo.CertificationLikeInfoVO;
import moofarm.ssuckssuck.domain.user.domain.User;

import static jakarta.persistence.GenerationType.IDENTITY;
import static lombok.AccessLevel.PROTECTED;

@Entity
@Getter
@NoArgsConstructor(access = PROTECTED)
public class CertificationLike {
    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "certification_like_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "certificaion_id")
    private Certification certification;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    private boolean likeStatus;

    @Builder
    public CertificationLike(Long id, Certification certification, User user, boolean likeStatus) {
        this.id = id;
        this.certification = certification;
        this.user = user;
        this.likeStatus = likeStatus;
    }
    public CertificationLikeInfoVO getCertificationLikeInfoVO() {
        return new CertificationLikeInfoVO(
                id,
                certification.getId(),
                likeStatus
        );
    }
    public static CertificationLike createCertificationLike(Certification certification, User user) {
        return CertificationLike.builder()
                .certification(certification)
                .user(user)
                .likeStatus(true)
                .build();
    }
}
