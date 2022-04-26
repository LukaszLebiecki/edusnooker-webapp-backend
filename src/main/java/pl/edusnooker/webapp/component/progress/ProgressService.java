package pl.edusnooker.webapp.component.progress;


import org.springframework.stereotype.Service;
import pl.edusnooker.webapp.component.progress.dto.ProgressLevelInfoDto;

import java.util.ArrayList;
import java.util.List;

@Service
class ProgressService {

    private final ProgressRepository progressRepository;

    public ProgressService(ProgressRepository progressRepository) {
        this.progressRepository = progressRepository;
    }


    List<ProgressLevelInfoDto> getAllProgressLevelInfoByUser (int userId) {
        List<ProgressLevelInfoDto> progressLevelInfoDtoList = new ArrayList<>();
        progressLevelInfoDtoList.add(getByLevel( 0, userId));
        progressLevelInfoDtoList.add(getByLevel( 1, userId));
        progressLevelInfoDtoList.add(getByLevel( 2, userId));
        progressLevelInfoDtoList.add(getByLevel( 3, userId));
        progressLevelInfoDtoList.add(getByLevel( 4, userId));
        progressLevelInfoDtoList.add(getByLevel( 5, userId));
        progressLevelInfoDtoList.add(getByLevel( 6, userId));
        progressLevelInfoDtoList.add(getByLevel( 7, userId));
        return progressLevelInfoDtoList;
    }

    ProgressLevelInfoDto getByLevel(int numberLevel, int userId) {
        List<Progress> allByUserAndNumberLevel = progressRepository.findAllByNumberLevelAndUserId(numberLevel, userId);
        ProgressLevelInfoDto dto = new ProgressLevelInfoDto();
        dto.setNumberLevel(numberLevel);
        dto.setNumberOfCompletedExercises(numberOfCompleteExercise(allByUserAndNumberLevel));
        return dto;
    }


    private static boolean isCompleteExercise(Progress progress) {
        return progress.getResultNumberOfPoint() >= progress.getNumberOfPointsToPassed();
    }

    private int numberOfCompleteExercise (List<Progress> progress) {
        long count = progress.stream()
                .filter(ProgressService::isCompleteExercise)
                .count();
        return (int) count;
    }
}

