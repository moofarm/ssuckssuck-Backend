package moofarm.ssuckssuck.domain.certificationLike.presentation;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import moofarm.ssuckssuck.domain.certificationLike.presentation.dto.request.CreateCertificationLikeRequest;
import moofarm.ssuckssuck.domain.certificationLike.presentation.dto.response.CertificationLikeResponse;
import moofarm.ssuckssuck.domain.certificationLike.service.CertificationLikeService;
import org.springframework.web.bind.annotation.*;

@Tag(name = "미션 좋아요", description = "미션 좋아요 관련 API")
@RestController
@RequestMapping("/api/certificationLIke")
@RequiredArgsConstructor
public class CertificationLikeController {
    private final CertificationLikeService certificationLikeService;

    @Operation(summary = "미션 인증 좋아요")
    @PostMapping("/{certificationId}")
    public CertificationLikeResponse createCertificationLike(@PathVariable Long certificationId) {
        return certificationLikeService.createCertificationLike(certificationId);
    }

    @Operation(summary = "미션 인증 좋아요 취소")
    @DeleteMapping("/{certificationId}")
    public void deleteCertificationLike(@PathVariable Long certificationId) { certificationLikeService.deleteCertificationLike(certificationId); }
}
