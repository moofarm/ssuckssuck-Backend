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
@RequestMapping("/api/certificationLike")
@RequiredArgsConstructor
public class CertificationLikeController {
    private final CertificationLikeService certificationLikeService;

    @Operation(summary = "미션 인증 좋아요 토글")
    @PostMapping("/{certificationId}")
    public CertificationLikeResponse createCertificationLike(@PathVariable Long certificationId, @RequestBody CreateCertificationLikeRequest createCertificationLikeRequest) {
        return certificationLikeService.createCertificationLike(certificationId, createCertificationLikeRequest);
    }

}
