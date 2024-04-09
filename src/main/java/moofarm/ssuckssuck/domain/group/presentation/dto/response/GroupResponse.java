package moofarm.ssuckssuck.domain.group.presentation.dto.response;


import moofarm.ssuckssuck.domain.group.domain.vo.GroupInfoVO;
import moofarm.ssuckssuck.global.common.MainCategory;
import moofarm.ssuckssuck.global.common.SubCategory;

public record GroupResponse(
        String title,
        String description,
        int participantsCount,
        MainCategory mainCategory,
        SubCategory subCategory
){
    public GroupResponse(GroupInfoVO groupInfoVO) {
        this(groupInfoVO.title(), groupInfoVO.description(), groupInfoVO.participantsCount(), groupInfoVO.mainCategory(), groupInfoVO.subCategory());
    }
}
