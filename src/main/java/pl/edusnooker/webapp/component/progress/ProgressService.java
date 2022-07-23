package pl.edusnooker.webapp.component.progress;


import org.springframework.stereotype.Service;
import pl.edusnooker.webapp.component.exercise.Exercise;
import pl.edusnooker.webapp.component.exercise.ExerciseMapper;
import pl.edusnooker.webapp.component.exercise.dto.ExerciseDto;
import pl.edusnooker.webapp.component.progress.dto.ProgressCounterHomeDto;
import pl.edusnooker.webapp.component.progress.dto.ProgressExerciseDto;
import pl.edusnooker.webapp.component.progress.dto.ProgressLevelInfoDto;

import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;


@Service
class ProgressService {


    private final ProgressLogic progressLogic;
    private final ProgressRepository progressRepository;

    public ProgressService(ProgressLogic progressLogic, ProgressRepository progressRepository) {
        this.progressLogic = progressLogic;
        this.progressRepository = progressRepository;
    }


    List<ProgressLevelInfoDto> getAllProgressLevelInfoByUser(String userId) {
        List<ProgressLevelInfoDto> progressLevelInfoDtoList = new ArrayList<>();
        progressLevelInfoDtoList.add(progressLogic.getByLevel(0, userId));
        progressLevelInfoDtoList.add(progressLogic.getByLevel(1, userId));
        progressLevelInfoDtoList.add(progressLogic.getByLevel(2, userId));
        progressLevelInfoDtoList.add(progressLogic.getByLevel(3, userId));
        progressLevelInfoDtoList.add(progressLogic.getByLevel(4, userId));
        progressLevelInfoDtoList.add(progressLogic.getByLevel(5, userId));
        progressLevelInfoDtoList.add(progressLogic.getByLevel(6, userId));
        progressLevelInfoDtoList.add(progressLogic.getByLevel(7, userId));
        return progressLevelInfoDtoList;
    }

    List<ProgressExerciseDto> getProgressExerciseInfo(int numberLevel, String userId) {
        List<Progress> allProgressByLevel = progressLogic.getAllProgressByLevel(numberLevel, userId);
        Set<ProgressExerciseDto> collect = allProgressByLevel.stream().map(p -> progressLogic.create(p, userId))
                .peek(e -> {
                    int theBestResult = e.getTheBestResult();
                    int resultToPass = e.getResultToPass();
                    if (theBestResult >= resultToPass)
                        e.setPass(true);
                })
                .collect(Collectors.toSet());
        List<ProgressExerciseDto> progressExerciseDtoList = collect.stream().toList();
        return progressExerciseDtoList.stream().sorted().toList();
    }

    Progress addProgress(String idExercise, int numberLevel, int numberOfPointsToPassed,
                         int resultNumberOfPoint, String userId) {
        Progress progress = new Progress();
        progress.setIdExercise(idExercise);
        progress.setNumberLevel(numberLevel);
        progress.setNumberOfPointsToPassed(numberOfPointsToPassed);
        progress.setResultNumberOfPoint(resultNumberOfPoint);
        progress.setUserId(userId);
        progress.setDateTimeExercise(LocalDateTime.now());
        progressRepository.save(progress);
        return progress;
    }

    public ExerciseDto getLastExerciseInfo(String userId) {
        Exercise exercise = progressLogic.lastExercise(userId);
        ExerciseDto exerciseDto = ExerciseMapper.map(exercise);
        return exerciseDto;
    }

    public int[] getChartsHomeByUserId(String userId) {
        int[] getProgressChartsByUserId = new int[12];
        for (int i = 1; i < 12; i++) {
            getProgressChartsByUserId[i-1] = (int) progressRepository.findAllProgressByUserIdAAndDateTimeExercise(userId, i).stream().count();
        }
        return getProgressChartsByUserId;
    }

    public ProgressCounterHomeDto getCounterHome(String userId) {
        ProgressCounterHomeDto progressCounterHomeDto = new ProgressCounterHomeDto();
        progressCounterHomeDto.setPointScored(progressLogic.getAllProgressPointScored(userId));
        progressCounterHomeDto.setExercisePerformed(progressLogic.getAllProgressExercisePerformed(userId));
        progressCounterHomeDto.setCompletedExercises(progressLogic.getAllProgressCompletedExercises(userId));
        return progressCounterHomeDto;
    }
}

