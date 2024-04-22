package moofarm.ssuckssuck.domain.group.presentation.dto.response;


import moofarm.ssuckssuck.domain.group.domain.vo.GroupInfoVO;
import moofarm.ssuckssuck.global.common.MainCategory;
import moofarm.ssuckssuck.global.common.SubCategory;

public record SearchGroupResponse(
        Long id,
        String title,
        String description,
        int participantsCount,
        MainCategory mainCategory,
        SubCategory subCategory,
        boolean isMine
){
    public SearchGroupResponse(GroupInfoVO groupInfoVO, boolean isMine) {
        this(groupInfoVO.id(), groupInfoVO.title(), groupInfoVO.description(), groupInfoVO.participantsCount(),
                groupInfoVO.mainCategory(), groupInfoVO.subCategory(), isMine);
    }
}
