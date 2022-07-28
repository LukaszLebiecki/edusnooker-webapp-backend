package pl.edusnooker.webapp.component.progress;

import org.springframework.stereotype.Component;
import pl.edusnooker.webapp.component.exercise.Exercise;
import pl.edusnooker.webapp.component.exercise.ExerciseRepository;
import pl.edusnooker.webapp.component.progress.dto.ProgressExerciseDto;
import pl.edusnooker.webapp.component.progress.dto.ProgressLevelInfoDto;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.YearMonth;
import java.util.List;
import java.util.Optional;

@Component
class ProgressLogic {

    private final ProgressRepository progressRepository;
    private final ExerciseRepository exerciseRepository;

    public ProgressLogic(ProgressRepository progressRepository, ExerciseRepository exerciseRepository) {
        this.progressRepository = progressRepository;
        this.exerciseRepository = exerciseRepository;
    }


    public ProgressExerciseDto create(Progress progress, String userId) {
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

    public ProgressLevelInfoDto getByLevel(int numberLevel, String userId) {
        ProgressLevelInfoDto dto = new ProgressLevelInfoDto();
        dto.setNumberLevel(numberLevel);
        dto.setNumberOfCompletedExercises(numberOfCompleteExercise(getAllProgressByLevel(numberLevel, userId)));
        return dto;
    }

    public Exercise lastExercise(String userId) {
        List<Progress> allByUserIdOrderByDateTimeExerciseDesc = progressRepository.findAllByUserIdOrderByDateTimeExerciseDesc(userId);
        if (allByUserIdOrderByDateTimeExerciseDesc.isEmpty()) {
            return new Exercise("empty");
        }
        Progress progress = allByUserIdOrderByDateTimeExerciseDesc.get(0);
        Optional<Exercise> lastExercise = exerciseRepository.findByExerciseId(progress.getIdExercise());
        return lastExercise.orElse(new Exercise("empty"));
    }

    private int getTheBestResult(String idExercise, String userId) {
        List<Progress> result = progressRepository
                .findAllByIdExerciseAndUserIdOrderByResultNumberOfPointDesc(idExercise, userId);
        return result.get(0).getResultNumberOfPoint();
    }

    private int getNumberDaysOfLastExercise(String idExercise, String userId) {
        Progress lastProgress = getLastProgress(idExercise, userId);
        LocalDateTime lastProgressDateTimeExercise = lastProgress.getDateTimeExercise();
        LocalDateTime now = LocalDateTime.now();
        Duration between = Duration.between(lastProgressDateTimeExercise, now);
        long daysAgo = between.toDays();
        return (int) daysAgo;
    }

    private int getLastResult(String idExercise, String userId) {
        Progress progress = getLastProgress(idExercise, userId);
        return progress.getResultNumberOfPoint();
    }

    private Progress getLastProgress(String idExercise, String userId) {
        List<Progress> allByIdExerciseAndUserIdOrderByDateTimeExercise = progressRepository
                .findAllByIdExerciseAndUserIdOrderByDateTimeExerciseDesc(idExercise, userId);
        Progress progress = allByIdExerciseAndUserIdOrderByDateTimeExercise.get(0);
        return progress;
    }

    private int getAttempts(Progress progress, String userId) {
        String idExercise = progress.getIdExercise();
        return progressRepository.findAllByIdExerciseAndUserId(idExercise, userId)
                .size();
    }

    private String getNameExercise(Progress progress) {
        String id = progress.getIdExercise();
        return exerciseRepository.findByExerciseId(id)
                .map(Exercise::getName)
                .orElse("");
    }

    public List<Progress> getAllProgressByLevel(int numberLevel, String userId) {
        return progressRepository.findAllByNumberLevelAndUserId(numberLevel, userId);
    }

    private static boolean isCompleteExercise(Progress progress) {
        return progress.getResultNumberOfPoint() >= progress.getNumberOfPointsToPassed();
    }

    private int numberOfCompleteExercise(List<Progress> progress) {
        long count = progress.stream()
                .filter(ProgressLogic::isCompleteExercise)
                .distinct()
                .count();
        return (int) count;
    }

    public int getAllProgressExercisePerformed(String userId) {
        return (int) progressRepository.findAllByUserId(userId)
                .stream()
                .count();
    }

    public int getAllProgressPointScored(String userId) {
        int pointsScored = 0;
        List<Progress> allByUserId = progressRepository.findAllByUserId(userId);
        for (Progress progress : allByUserId) {
            pointsScored += progress.getResultNumberOfPoint();
        }
        return pointsScored;
    }

    public int getAllProgressCompletedExercises(String userId) {
        int completedExercises = 0;
        List<Progress> allByUserId = progressRepository.findAllByUserId(userId);
        for (Progress progress : allByUserId) {
            if (progress.getResultNumberOfPoint() >= progress.getNumberOfPointsToPassed()) {
                completedExercises += 1;
            }
        }
        return completedExercises;
    }

    public int geDayCount(int year, int month) {
        return YearMonth.of(year, month).lengthOfMonth();
    }

}
