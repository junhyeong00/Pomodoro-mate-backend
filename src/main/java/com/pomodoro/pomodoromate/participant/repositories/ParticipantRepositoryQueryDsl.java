package com.pomodoro.pomodoromate.participant.repositories;

import com.pomodoro.pomodoromate.common.models.SessionId;
import com.pomodoro.pomodoromate.participant.models.Participant;
import com.pomodoro.pomodoromate.studyRoom.models.StudyRoomId;
import com.pomodoro.pomodoromate.user.models.UserId;

import java.util.List;
import java.util.Optional;

public interface ParticipantRepositoryQueryDsl {
    Long countActiveBy(StudyRoomId studyRoomId);

    List<Participant> findAllActiveBy(StudyRoomId id);

    Optional<Participant> findBy(UserId userId, StudyRoomId studyRoomId);

    Optional<Participant> findBy(SessionId sessionId);
}
