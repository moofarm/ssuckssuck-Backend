package moofarm.ssuckssuck.domain.certificationLike.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import moofarm.ssuckssuck.domain.certification.domain.Certification;

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

    private boolean Like_status;
}
