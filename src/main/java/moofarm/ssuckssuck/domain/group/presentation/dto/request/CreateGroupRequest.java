package moofarm.ssuckssuck.domain.group.presentation.dto.request;

import moofarm.ssuckssuck.global.common.MainCategory;
import moofarm.ssuckssuck.global.common.SubCategory;

public record CreateGroupRequest(
        String title,
        String description,
        MainCategory mainCategory,
        SubCategory subCategory
) {
}
