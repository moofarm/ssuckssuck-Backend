package moofarm.ssuckssuck.domain.group.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import moofarm.ssuckssuck.domain.group.domain.Group;
import moofarm.ssuckssuck.domain.group.domain.repository.GroupRepository;
import moofarm.ssuckssuck.domain.group.exception.GroupNotFoundException;
import moofarm.ssuckssuck.domain.group.presentation.dto.request.CreateGroupRequest;
import moofarm.ssuckssuck.domain.group.presentation.dto.response.GroupResponse;
import moofarm.ssuckssuck.domain.group.presentation.dto.response.SearchGroupResponse;
import moofarm.ssuckssuck.domain.misson.domain.repository.MissionRepository;
import moofarm.ssuckssuck.domain.user.domain.User;
import moofarm.ssuckssuck.global.common.SubCategory;
import moofarm.ssuckssuck.global.utils.user.UserUtils;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Base64;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
@Slf4j
public class GroupService implements GroupServiceUtils{
    private final GroupRepository groupRepository;
    private final MissionRepository missionRepository;
    private final UserUtils userUtils;

    //방생성
    @Transactional
    public GroupResponse createGroup(CreateGroupRequest createGroupRequest) {
        Group group = Group.createGroup(
                createGroupRequest.title(),
                createGroupRequest.description(),
                createGroupRequest.mainCategory(),
                createGroupRequest.subCategory()
        );

        groupRepository.save(group);

        return new GroupResponse(group.getGroupInfo());
    }

    // 그룹 정보 조회
    public GroupResponse getGroupProfile(Long groupId) {
        Group group = queryGroup(groupId);

        return new GroupResponse(group.getGroupInfo());
    }

    //하위 카테고리 정보 조회
    public Slice<SearchGroupResponse> getGroupsBySubCategory(SubCategory subCategory, int pageNumber, String sortBy) {
        Pageable pageable = PageRequest.of(pageNumber, 10, Sort.by(Sort.Direction.DESC, sortBy));
        User user = userUtils.getUserFromSecurityContext();

        return groupRepository.findAllBySubCategory(subCategory, pageable).map(
                    group -> {
                        boolean isMine = checkIfUserIsMemberOfGroup(user, group);
                        return new SearchGroupResponse(group.getGroupInfo(), isMine);
                    });
    }

    //키워드 검색
    public Slice<SearchGroupResponse> getGroupsByKeyword(int pageNumber, String keyword) {
        String decode = new String(Base64.getDecoder().decode(keyword));
        Pageable pageable = PageRequest.of(pageNumber, 10);
        User user = userUtils.getUserFromSecurityContext();

        return groupRepository.findALlByKeyword(decode, pageable).map(
                group -> {
                    boolean isMine = checkIfUserIsMemberOfGroup(user, group);
                    return new SearchGroupResponse(group.getGroupInfo(), isMine);
                });
    }

    //미션방 삭제
    @Override
    @Transactional
    public void deleteGroup(Long groupId) {
        Group group = queryGroup(groupId);

        groupRepository.delete(group);
    }

    private boolean checkIfUserIsMemberOfGroup(User user, Group group) {
        return missionRepository.existsByUserAndGroup(user, group);
    }

    @Override
    public Group queryGroup(Long groupId) {
        return groupRepository.findById(groupId).orElseThrow(() -> GroupNotFoundException.EXCEPTION);
    }
}
