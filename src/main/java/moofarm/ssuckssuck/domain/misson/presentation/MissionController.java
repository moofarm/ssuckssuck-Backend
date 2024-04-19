package moofarm.ssuckssuck.domain.misson.presentation;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import moofarm.ssuckssuck.domain.misson.presentation.dto.request.CreateMissionRequest;
import moofarm.ssuckssuck.domain.misson.presentation.dto.response.MissionProfileResponse;
import moofarm.ssuckssuck.domain.misson.presentation.dto.response.MyMissionListResponse;
import moofarm.ssuckssuck.domain.misson.service.MissionService;
import org.springframework.web.bind.annotation.*;

@Tag(name = "미션", description = "미션 관련 API")
@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class MissionController {

    private final MissionService missionService;

    @Operation(summary = "미션 생성")
    @PostMapping("/mission/{groupId}")
    public MissionProfileResponse createMission(@PathVariable("groupId") Long groupId, @Valid @RequestBody CreateMissionRequest createMissionRequest) {
        return missionService.createMission(groupId, createMissionRequest);
    }

    @Operation(summary = "미션 정보 조회")
    @GetMapping("/mission/{missionId}")
    public MissionProfileResponse getMissionProfile(@PathVariable("missionId") Long missionId) {
        return missionService.getMissionProfile(missionId);
    }

    @Operation(summary = "나의 미션 리스트 조회")
    @GetMapping("/mission/list")
    public MyMissionListResponse getMissionProfile() {
        return missionService.getMyMissionList();
    }

    @Operation(summary = "미션 삭제")
    @DeleteMapping("/mission/{groupId}")
    public void deleteMissionProfile(@PathVariable("groupId") Long groupId) {
        missionService.deleteMission(groupId);
    }

    @Operation(summary = "미션 즐겨찾기")
    @PostMapping("/bookmark/{missionId}")
    public MissionProfileResponse bookmarkMission(@PathVariable("missionId") Long missionId) {
        return missionService.bookmarkMission(missionId);
    }
}
