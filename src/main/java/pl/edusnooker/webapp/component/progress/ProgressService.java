package pl.edusnooker.webapp.component.progress;


import org.springframework.stereotype.Service;
import pl.edusnooker.webapp.component.progress.dto.ProgressExerciseDto;
import pl.edusnooker.webapp.component.progress.dto.ProgressLevelInfoDto;

import java.util.*;
import java.util.stream.Collectors;


@Service
class ProgressService {


    private final ProgressLogic progressLogic;

    public ProgressService(ProgressLogic progressLogic) {
        this.progressLogic = progressLogic;
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

}

