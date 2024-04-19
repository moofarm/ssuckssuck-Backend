package moofarm.ssuckssuck.domain.misson.presentation.dto.response;

import java.util.List;

public record MyMissionListResponse(
        List<MissionProfileResponse> bookmarkedList,
        List<MissionProfileResponse> unbookmarkedList
) {
}
