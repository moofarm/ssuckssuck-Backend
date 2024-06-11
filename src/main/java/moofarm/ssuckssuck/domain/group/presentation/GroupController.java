package moofarm.ssuckssuck.domain.group.presentation;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import moofarm.ssuckssuck.domain.group.presentation.dto.request.CreateGroupRequest;
import moofarm.ssuckssuck.domain.group.presentation.dto.response.GroupResponse;
import moofarm.ssuckssuck.domain.group.presentation.dto.response.SearchGroupResponse;
import moofarm.ssuckssuck.domain.group.service.GroupService;
import moofarm.ssuckssuck.global.common.SubCategory;
import org.springframework.data.domain.Slice;
import org.springframework.web.bind.annotation.*;


@Tag(name = "미션방", description = "미션방 관련 API")
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/group")
public class GroupController {
    private final GroupService groupService;

    @Operation(summary = "미션방 생성")
    @PostMapping("/generation")
    public GroupResponse createGroup (@RequestBody @Valid CreateGroupRequest createGroupRequest) {
        return groupService.createGroup(createGroupRequest);
    }

    @Operation(summary = "그룹 정보 조회")
    @GetMapping("/{groupId}")
    public GroupResponse getGroupsBySubCategory(@PathVariable(value = "groupId") Long groupId) {
        return groupService.getGroupProfile(groupId);
    }

    @Operation(summary = "미션방 하위 카테고리 정보 조회")
    @GetMapping("/search/subCategory/{subCategory}/{pageNumber}")
    public Slice<SearchGroupResponse> getGroupsBySubCategory(@PathVariable(value = "subCategory") SubCategory subCategory, @PathVariable(value = "pageNumber") int pageNumber, @RequestParam(name = "sortBy", defaultValue = "createDate") String sortBy) {
        return groupService.getGroupsBySubCategory(subCategory, pageNumber, sortBy);
    }

    @Operation(summary = "미션방 키워드 검색")
    @GetMapping("/search/{keyword}/{pageNumber}")
    public Slice<SearchGroupResponse> getGroupsByKeyword(@PathVariable(value = "pageNumber") int pageNumber, @PathVariable(value = "keyword") String keyword) {
        return groupService.getGroupsByKeyword(pageNumber,keyword);
    }
}
