package moofarm.ssuckssuck.domain.certification.domain;

import jakarta.persistence.*;

import static jakarta.persistence.GenerationType.IDENTITY;
import static lombok.AccessLevel.PROTECTED;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import moofarm.ssuckssuck.domain.certification.domain.vo.CertificationinfoVO;
import moofarm.ssuckssuck.domain.user.domain.User;
import moofarm.ssuckssuck.global.database.BaseEntity;

@Entity
@Getter
@NoArgsConstructor(access = PROTECTED)
public class Certification extends BaseEntity {
    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "certification_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    private User user;

    //private Group group;
    private String certificationImage;

    private int likeCount;

    @Builder
    public Certification(Long id, User user, String certificationImage, int likeCount) {
        this.id = id;
        this.user = user;
        this.certificationImage = certificationImage;
        this.likeCount = likeCount;
    }
    public CertificationinfoVO getCertificationInfo() {
        return new CertificationinfoVO(
                id,
                certificationImage,
                likeCount
        );
    }
    public static Certification createCertification(User user, String certificationImage, int likeCount) {
        return builder()
                .user(user)
                .certificationImage(certificationImage)
                .likeCount(likeCount)
                .build();
    }
}
