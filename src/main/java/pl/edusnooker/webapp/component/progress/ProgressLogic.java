package pl.edusnooker.webapp.component.progress;

import org.springframework.stereotype.Component;
import pl.edusnooker.webapp.component.exercise.Exercise;
import pl.edusnooker.webapp.component.exercise.ExerciseRepository;
import pl.edusnooker.webapp.component.progress.dto.ProgressExerciseDto;
import pl.edusnooker.webapp.component.progress.dto.ProgressLevelInfoDto;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;

@Component
class ProgressLogic {

    private final ProgressRepository progressRepository;
    private final ExerciseRepository exerciseRepository;

    public ProgressLogic(ProgressRepository progressRepository, ExerciseRepository exerciseRepository) {
        this.progressRepository = progressRepository;
        this.exerciseRepository = exerciseRepository;
    }


    public ProgressExerciseDto create(Progress progress, int userId) {
        ProgressExerciseDto dto = new ProgressExerciseDto();
        dto.setIdExercise(progress.getIdExercise());
        dto.setNameExercise(getNameExercise(progress));
        dto.setNumberOfAttempts(getAttempts(progress, userId));
        dto.setRecentActivity(getNumberDaysOfLastExercise(progress.getIdExercise(), userId));
        dto.setLastResult(getLastResult(progress.getIdExercise(), userId));
        dto.setTheBestResult(getTheBestResult(progress.getIdExercise(), userId));
        dto.setResultToPass(progress.getNumberOfPointsToPassed());
        return dto;
    }



    public ProgressLevelInfoDto getByLevel(int numberLevel, int userId) {
        ProgressLevelInfoDto dto = new ProgressLevelInfoDto();
        dto.setNumberLevel(numberLevel);
        dto.setNumberOfCompletedExercises(numberOfCompleteExercise(getAllProgressByLevel(numberLevel, userId)));
        return dto;
    }

    private int getTheBestResult(int idExercise, int userId) {
        List<Progress> result = progressRepository
                .findAllByIdExerciseAndUserIdOrderByResultNumberOfPointDesc(idExercise, userId);
        return result.get(0).getResultNumberOfPoint();
    }

    private int getNumberDaysOfLastExercise(int idExercise, int userId) {
        Progress lastProgress = getLastProgress(idExercise, userId);
        LocalDateTime lastProgressDateTimeExercise = lastProgress.getDateTimeExercise();
        LocalDateTime now = LocalDateTime.now();
        Duration between = Duration.between(lastProgressDateTimeExercise, now);
        long daysAgo = between.toDays();
        return (int) daysAgo;
    }

    private int getLastResult(int idExercise, int userId) {
        Progress progress = getLastProgress(idExercise, userId);
        return progress.getResultNumberOfPoint();
    }

    private Progress getLastProgress(int idExercise, int userId) {
        List<Progress> allByIdExerciseAndUserIdOrderByDateTimeExercise = progressRepository
                .findAllByIdExerciseAndUserIdOrderByDateTimeExerciseDesc(idExercise, userId);
        Progress progress = allByIdExerciseAndUserIdOrderByDateTimeExercise.get(0);
        return progress;
    }

    private int getAttempts(Progress progress, int userId) {
        int idExercise = progress.getIdExercise();
        return progressRepository.findAllByIdExerciseAndUserId(idExercise, userId)
                .size();
    }

    private String getNameExercise(Progress progress) {
        int id = progress.getIdExercise();
        return exerciseRepository.findById((long) id)
                .map(Exercise::getName)
                .orElse("");
    }

    public List<Progress> getAllProgressByLevel(int numberLevel, int userId) {
        return progressRepository.findAllByNumberLevelAndUserId(numberLevel, userId);
    }

    private static boolean isCompleteExercise(Progress progress) {
        return progress.getResultNumberOfPoint() >= progress.getNumberOfPointsToPassed();
    }

    private int numberOfCompleteExercise(List<Progress> progress) {
        long count = progress.stream()
                .filter(ProgressLogic::isCompleteExercise)
                .count();
        return (int) count;
    }

}
