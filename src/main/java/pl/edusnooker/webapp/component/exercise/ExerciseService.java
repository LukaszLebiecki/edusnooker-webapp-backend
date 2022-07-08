package pl.edusnooker.webapp.component.exercise;

import org.springframework.stereotype.Service;
import pl.edusnooker.webapp.component.exercise.dto.ExerciseDto;
import pl.edusnooker.webapp.component.exercise.dto.ExerciseLevelInfoDto;
import pl.edusnooker.webapp.component.exercise.dto.ExerciseListDto;
import pl.edusnooker.webapp.enumeration.Level;


import java.util.*;


@Service
class ExerciseService {
    private final ExerciseRepository exerciseRepository;
    private final ExerciseLogic exerciseLogic;

    public ExerciseService(ExerciseRepository exerciseRepository, ExerciseLogic exerciseLogic) {
        this.exerciseRepository = exerciseRepository;
        this.exerciseLogic = exerciseLogic;
    }


    Optional<ExerciseDto> getExerciseById(String id) {
        return exerciseRepository.findByExerciseId(id).map(ExerciseMapper::map);
    }

    List<ExerciseDto> getAllExerciseByLevel(Level level) {
        List<ExerciseDto> exerciseListDtoList = exerciseRepository.findAllByLevel(level)
                .stream()
                .map(ExerciseMapper::map)
                .toList();
        return exerciseListDtoList;
    }

    ExerciseLevelInfoDto getLevelInfo(Level level) {
        long count = exerciseRepository.findAllByLevel(level).stream().count();
        ExerciseLevelInfoDto exerciseLevelInfoDto = new ExerciseLevelInfoDto();
        exerciseLevelInfoDto.setName(level.name());
        exerciseLevelInfoDto.setNumberOfExercise((int) count);
        exerciseLevelInfoDto.setNumberOfPointToTarget(level.getNumberOfPointToTarget());
        return exerciseLevelInfoDto;
    }

    List<ExerciseLevelInfoDto> getAllLevelInfo() {
        List<ExerciseLevelInfoDto> levelInfoDtoList = new ArrayList<>();
        levelInfoDtoList.add(getLevelInfo(Level.WHITE));
        levelInfoDtoList.add(getLevelInfo(Level.RED));
        levelInfoDtoList.add(getLevelInfo(Level.YELLOW));
        levelInfoDtoList.add(getLevelInfo(Level.GREEN));
        levelInfoDtoList.add(getLevelInfo(Level.BROWN));
        levelInfoDtoList.add(getLevelInfo(Level.BLUE));
        levelInfoDtoList.add(getLevelInfo(Level.PINK));
        levelInfoDtoList.add(getLevelInfo(Level.BLACK));
        return levelInfoDtoList;
    }

    List<Exercise> getAllExercises() {
        List<Exercise> exerciseList = exerciseRepository.findAll();
        exerciseList.sort(Comparator.comparing(Exercise::getExerciseId));
        return exerciseList;
    }

    Exercise addNewExercise(String name, String description, String videoUrl, String img, int numberOfPointsToPassed,
                            int maxPoints, int numberOfAttempts,int numberOfStrokesInOneAttempt ,Level level, boolean isWhite, boolean isRed,
                            boolean isYellow, boolean isGreen, boolean isBrown, boolean isBlue, boolean isPink,
                            boolean isBlack, boolean isButtonPass, boolean isBonusPoint, String bonusInfo,
                            int bonusNumberOfPoints) {
        Exercise exercise = new Exercise();
        exercise.setExerciseId(exerciseLogic.generateExerciseId(level));
        exercise.setName(name);
        exercise.setDescription(description);
        exercise.setVideoUrl(videoUrl);
        exercise.setImg(img);
        exercise.setNumberOfPointsToPassed(numberOfPointsToPassed);
        exercise.setMaxPoints(maxPoints);
        exercise.setNumberOfAttempts(numberOfAttempts);
        exercise.setNumberOfStrokesInOneAttempt(numberOfStrokesInOneAttempt);
        exercise.setLevel(level);
        exercise.setWhite(isWhite);
        exercise.setRed(isRed);
        exercise.setYellow(isYellow);
        exercise.setGreen(isGreen);
        exercise.setBrown(isBrown);
        exercise.setBlue(isBlue);
        exercise.setPink(isPink);
        exercise.setBlack(isBlack);
        exercise.setButtonPass(isButtonPass);
        exercise.setBonusPoint(isBonusPoint);
        exercise.setBonusInfo(bonusInfo);
        exercise.setBonusNumberOfPoints(bonusNumberOfPoints);
        exerciseRepository.save(exercise);
        return exercise;
    }

    Exercise updateExercise(String currentExerciseId, String name, String description, String videoUrl,
                                   String img, int numberOfPointsToPassed, int maxPoints, int numberOfAttempts, int numberOfStrokesInOneAttempt,
                                   Level level, boolean isWhite, boolean isRed, boolean isYellow, boolean isGreen,
                                   boolean isBrown, boolean isBlue, boolean isPink, boolean isBlack,
                                   boolean isButtonPass, boolean isBonusPoint, String bonusInfo,
                                   int bonusNumberOfPoints) {
        Exercise currentExercise = exerciseRepository.findByExerciseId(currentExerciseId).get(); // TODO obsłużyć jak nie znajdzie ćwiczenia w bazie!
        currentExercise.setName(name);
        currentExercise.setDescription(description);
        currentExercise.setVideoUrl(videoUrl);
        currentExercise.setImg(img);
        currentExercise.setNumberOfPointsToPassed(numberOfPointsToPassed);
        currentExercise.setMaxPoints(maxPoints);
        currentExercise.setNumberOfAttempts(numberOfAttempts);
        currentExercise.setNumberOfStrokesInOneAttempt(numberOfStrokesInOneAttempt);
        currentExercise.setLevel(level);
        currentExercise.setWhite(isWhite);
        currentExercise.setRed(isRed);
        currentExercise.setYellow(isYellow);
        currentExercise.setGreen(isGreen);
        currentExercise.setBrown(isBrown);
        currentExercise.setBlue(isBlue);
        currentExercise.setPink(isPink);
        currentExercise.setBlack(isBlack);
        currentExercise.setButtonPass(isButtonPass);
        currentExercise.setBonusPoint(isBonusPoint);
        currentExercise.setBonusInfo(bonusInfo);
        currentExercise.setBonusNumberOfPoints(bonusNumberOfPoints);
        exerciseRepository.save(currentExercise);
        return currentExercise;
    }

    void deleteExercise(String exerciseId) {
        Exercise exercise = exerciseRepository.findByExerciseId(exerciseId).get();
        exerciseRepository.delete(exercise);
    }
}
