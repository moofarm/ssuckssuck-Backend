package moofarm.ssuckssuck.domain.certification.domain;

import jakarta.persistence.*;

import static jakarta.persistence.GenerationType.IDENTITY;
import static lombok.AccessLevel.PROTECTED;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import moofarm.ssuckssuck.domain.certification.domain.exception.UserNotOwnerException;
import moofarm.ssuckssuck.domain.certification.domain.vo.CertificationinfoVO;
import moofarm.ssuckssuck.domain.group.domain.Group;
import moofarm.ssuckssuck.domain.misson.domain.Mission;
import moofarm.ssuckssuck.domain.misson.exception.UserIsNotMissionHostException;
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
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "group_id")
    private Group group;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "mission_id")
    private Mission mission;

    private String certificationImage;

    private int likeCount;

    @Builder
    public Certification(Long id, User user, Group group, Mission mission, String certificationImage, int likeCount) {
        this.id = id;
        this.user = user;
        this.group = group;
        this.mission = mission;
        this.certificationImage = certificationImage;
        this.likeCount = likeCount;
    }
    public CertificationinfoVO getCertificationInfoVO() {
        return new CertificationinfoVO(
                id,
                certificationImage,
                likeCount
        );
    }
    public static Certification createCertification(User user, Group group, Mission mission, String certificationImage) {
        return builder()
                .user(user)
                .group(group)
                .mission(mission)
                .certificationImage(certificationImage)
                .likeCount(0)
                .build();
    }
    public void addLikeCount() {
        this.likeCount++;
    }

    public void verifyOwner(User user) {
        if (!this.user.equals(user)) {
            throw UserNotOwnerException.EXCEPTION;
        }
    }
    public void updateCertification(String certificationImage) {
        this.certificationImage = certificationImage;
        this.likeCount = 0;
    }
}
