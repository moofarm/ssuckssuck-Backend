package moofarm.ssuckssuck.domain.group.service;

import moofarm.ssuckssuck.domain.group.domain.Group;

public interface GroupServiceUtils {
    void deleteGroup(Long groupId);

    Group queryGroup(Long groupId);
}
