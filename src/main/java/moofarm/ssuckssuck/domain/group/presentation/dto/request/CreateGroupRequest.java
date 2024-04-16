package moofarm.ssuckssuck.domain.group.presentation.dto.request;
import jakarta.validation.constraints.Size;
import moofarm.ssuckssuck.global.common.MainCategory;
import moofarm.ssuckssuck.global.common.SubCategory;

public record CreateGroupRequest(
        @Size(min = 1,max = 25, message = "미션방 이름은 25자 이내로 입력해주세요.")
        String title,
        @Size(min = 1, max = 100, message = "미션방 소개는 100자 이내로 입력해주세요.")
        String description,
        MainCategory mainCategory,
        SubCategory subCategory
) {
}
