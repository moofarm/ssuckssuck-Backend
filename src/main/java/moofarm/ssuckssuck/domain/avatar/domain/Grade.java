package moofarm.ssuckssuck.domain.avatar.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Arrays;
import java.util.Comparator;

@Getter
@AllArgsConstructor
public enum Grade {
    SEED("씨앗", 0, "1"),
    SPROUT("새싹", 30, "2"),
    BABY("아기", 80, "3"),
    TEENAGER("청소년", 150, "4"),
    ADULT("어른", 300, "5"),
    RADISH("최종 무", 500, "6");

    private final String message;
    private final int requiredExperience;
    private final String avatarImage;

    // 경험치에 따른 등급 찾기
    public static Grade findByExperience(int experience) {
        return Arrays.stream(Grade.values())
                .filter(grade -> experience >= grade.requiredExperience)
                .max(Comparator.comparingInt(grade -> grade.requiredExperience))
                .orElse(SEED);
    }
}
