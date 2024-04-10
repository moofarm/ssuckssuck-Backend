package moofarm.ssuckssuck.domain.group.domain.vo;

import moofarm.ssuckssuck.global.common.MainCategory;
import moofarm.ssuckssuck.global.common.SubCategory;

public record GroupInfoVO (
        Long id,
        String title,
        String description,
        int participantsCount,
        MainCategory mainCategory,
        SubCategory subCategory
) {
}
