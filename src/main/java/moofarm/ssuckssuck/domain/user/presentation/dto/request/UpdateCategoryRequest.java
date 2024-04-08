package moofarm.ssuckssuck.domain.user.presentation.dto.request;

import moofarm.ssuckssuck.global.common.MainCategory;
import moofarm.ssuckssuck.global.common.SubCategory;
import org.springframework.lang.Nullable;

public record UpdateCategoryRequest(
        MainCategory mainCategory,

        @Nullable
        SubCategory subCategory
) {
}
