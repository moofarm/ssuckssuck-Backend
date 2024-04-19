package moofarm.ssuckssuck.domain.misson.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import moofarm.ssuckssuck.domain.avatar.domain.Avatar;
import moofarm.ssuckssuck.domain.group.domain.Group;
import moofarm.ssuckssuck.domain.group.service.GroupServiceUtils;
import moofarm.ssuckssuck.domain.misson.domain.Mission;
import moofarm.ssuckssuck.domain.misson.domain.repository.MissionRepository;
import moofarm.ssuckssuck.domain.misson.exception.MissionNotFoundException;
import moofarm.ssuckssuck.domain.misson.presentation.dto.request.CreateMissionRequest;
import moofarm.ssuckssuck.domain.misson.presentation.dto.response.MissionProfileResponse;
import moofarm.ssuckssuck.domain.misson.presentation.dto.response.MyMissionListResponse;
import moofarm.ssuckssuck.domain.user.domain.User;
import moofarm.ssuckssuck.global.utils.user.UserUtils;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MissionService implements MissionServiceUtils{

    private final MissionRepository missionRepository;
    private final UserUtils userUtils;
    private final GroupServiceUtils groupServiceUtils;

    // 미션방 생성하기
    @Transactional
    public MissionProfileResponse createMission(Long groupId, CreateMissionRequest createMissionRequest) {
        User user = userUtils.getUserFromSecurityContext();
        Group group = groupServiceUtils.queryGroup(groupId);

        Mission mission = Mission.createMission(
                user,
                group,
                createMissionRequest.dueDate(),
                createMissionRequest.targetCount()
        );

        missionRepository.save(mission);

        group.addParticipant();

        return new MissionProfileResponse(groupId, mission.getMissionInfoVO());
    }

    // 미션 정보 조회
    public MissionProfileResponse getMissionProfile(Long id) {
        Mission mission = getMission(id);

        return new MissionProfileResponse(mission.getGroup().getId(), mission.getMissionInfoVO());
    }

    // 나의 미션 리스트 조회
    public MyMissionListResponse getMyMissionList() {
        User user = userUtils.getUserFromSecurityContext();
        List<Mission> bookmarkList = missionRepository.findAllByUserAndBookmark(user, true);
        List<Mission> unbookmarkList = missionRepository.findAllByUserAndBookmark(user, false);


        List<MissionProfileResponse> bookmarkedList = bookmarkList.stream().map(l -> new MissionProfileResponse(l.getGroup().getId(), l.getMissionInfoVO())).collect(Collectors.toList());
        List<MissionProfileResponse> unbookmarkedList = unbookmarkList.stream().map(l -> new MissionProfileResponse(l.getGroup().getId(), l.getMissionInfoVO())).collect(Collectors.toList());

        return new MyMissionListResponse(bookmarkedList, unbookmarkedList);
    }


    // 미션 탈퇴
    @Transactional
    public void deleteMission(Long groupId) {
        User user = userUtils.getUserFromSecurityContext();
        Group group = groupServiceUtils.queryGroup(groupId);
        Mission mission = missionRepository.findByGroup(group).orElseThrow(() -> MissionNotFoundException.EXCEPTION);

        mission.validUserIsHost(user);

        missionRepository.delete(mission);

        group.subtractParticipant();

        if (group.getParticipantsCount() == 0) {
            groupServiceUtils.deleteGroup(groupId);
        }
    }

    // 매일 미션 인증 상태 초기화 및 완료일 지나면 자동 종료
    @Transactional
    @Async
    @Scheduled(cron = "0 0 0 * * *", zone = "Asia/Seoul")
    public void resetDailyMissionStatus() {
        List<Mission> missionList = missionRepository.findAll();

        for (Mission mission : missionList) {
            if (mission.isMissionStatus()) {
                mission.addMissionFrequency();
                mission.resetMissionStatus();
            }

            if (mission.getDayOfWeek() == LocalDate.now().getDayOfWeek()) {
                if (mission.getMissionFrequency() < mission.getTargetCount().getMinNum()) {
                    deleteMissionAsync(mission);
                    continue;
                } else {
                    addBonusExp(mission);
                    mission.resetMissionFrequency();
                }
            }

            if (mission.getDueDate().isBefore(LocalDate.now())) {
                deleteMissionAsync(mission);
            }
        }
    }

    // 미션 즐겨 찾기
    @Transactional
    public MissionProfileResponse bookmarkMission(Long missionId) {
        Mission mission = getMission(missionId);

        mission.bookmarkMission();

        return new MissionProfileResponse(mission.getGroup().getId(), mission.getMissionInfoVO());
    }

    private Mission getMission(Long missionId) {
        User user = userUtils.getUserFromSecurityContext();
        Mission mission = queryMission(missionId);
        mission.validUserIsHost(user);
        return mission;
    }

    // 목표 인증 횟수 달성 시 보너스 경험치
    private void addBonusExp( Mission mission) {
        Avatar avatar = mission.getUser().getAvatar();

        avatar.addExperience(mission.getTargetCount().getMinNum());
    }

    @Async
    public void deleteMissionAsync(Mission mission) {
        missionRepository.delete(mission);
    }

    @Override
    public Mission queryMission(Long missionId) {
        return missionRepository.findById(missionId).orElseThrow(() -> MissionNotFoundException.EXCEPTION);
    }
}
