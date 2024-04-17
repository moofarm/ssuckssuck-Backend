package moofarm.ssuckssuck.domain.certification.presentation;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import moofarm.ssuckssuck.domain.certification.presentation.dto.request.CreateCertificationRequest;
import moofarm.ssuckssuck.domain.certification.presentation.dto.request.UpdateCertificationRequest;
import moofarm.ssuckssuck.domain.certification.presentation.dto.response.CertificationResponse;
import moofarm.ssuckssuck.domain.certification.service.CertificationService;
import org.springframework.data.domain.Slice;
import org.springframework.web.bind.annotation.*;

@Tag(name = "미션 인증", description = "미션 인증 관련 API")
@RestController
@RequestMapping("/api/certification")
@RequiredArgsConstructor
public class CertificationController {
    private final CertificationService certificationService;
    @Operation(summary = "미션 인증")
    @PostMapping("/{groupId}/{missionId}")
    public CertificationResponse createCertification(@PathVariable("missionId") Long missionId, @PathVariable("groupId") Long groupId, @RequestBody CreateCertificationRequest CreateCertificationRequest) {
        return certificationService.createCertification(missionId,groupId, CreateCertificationRequest);
    }

    @Operation(summary = "미션 인증 수정")
    @PostMapping("/update/{id}")
    public CertificationResponse updateCertificaiton(@PathVariable("id") Long id,  @RequestBody UpdateCertificationRequest updateCertificationRequest) {
        return certificationService.updateCertification(id, updateCertificationRequest);
    }

    @Operation(summary = "미션방별 미션 인증 조회")
    @GetMapping("/{groupId}/{pageNumber}")
    public Slice<CertificationResponse> getCertificationsByGroup(@PathVariable("pageNumber") int pageNumber, @PathVariable("groupId") Long groupId) {
        return certificationService.getCertificationsByGroup(groupId, pageNumber);
    }
}
