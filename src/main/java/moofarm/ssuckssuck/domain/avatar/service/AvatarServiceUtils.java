package moofarm.ssuckssuck.domain.avatar.service;

import moofarm.ssuckssuck.domain.avatar.domain.Avatar;

public interface AvatarServiceUtils {
    Avatar createAvatar();
    void addExperience(Avatar avatar);
    void updateGrade(Avatar avatar);
    void calculateExperienceNeededForNextGrade(Avatar avatar);
}
