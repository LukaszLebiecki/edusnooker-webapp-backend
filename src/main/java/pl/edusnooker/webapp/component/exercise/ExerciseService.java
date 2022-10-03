package pl.edusnooker.webapp.component.exercise;

import org.springframework.stereotype.Service;
import pl.edusnooker.webapp.component.exercise.dto.ExerciseDto;
import pl.edusnooker.webapp.component.exercise.dto.ExerciseLevelInfoDto;
import pl.edusnooker.webapp.component.user.User;
import pl.edusnooker.webapp.component.user.UserRepository;
import pl.edusnooker.webapp.enumeration.Level;


import java.util.*;


@Service
class ExerciseService {
    private final ExerciseRepository exerciseRepository;
    private final ExerciseLogic exerciseLogic;
    private final UserRepository userRepository;

    public ExerciseService(ExerciseRepository exerciseRepository, ExerciseLogic exerciseLogic, UserRepository userRepository) {
        this.exerciseRepository = exerciseRepository;
        this.exerciseLogic = exerciseLogic;
        this.userRepository = userRepository;
    }


    Optional<ExerciseDto> getExerciseById(String id) {
        return exerciseRepository.findByExerciseId(id).map(ExerciseMapper::map);
    }

    Optional<ExerciseDto> getExerciseSlotOne(String userId) {
        User user = userRepository.findAllByUserId(userId);
        return exerciseRepository.findByExerciseId(user.getFavoriteSlotOne()).map(ExerciseMapper::map);
    }

    Optional<ExerciseDto> getExerciseSlotTwo(String userId) {
        User user = userRepository.findAllByUserId(userId);
        return exerciseRepository.findByExerciseId(user.getFavoriteSlotTwo()).map(ExerciseMapper::map);
    }

    Optional<ExerciseDto> getExerciseSlotThree(String userId) {
        User user = userRepository.findAllByUserId(userId);
        return exerciseRepository.findByExerciseId(user.getFavoriteSlotThree()).map(ExerciseMapper::map);
    }

    List<ExerciseDto> getAllExerciseByLevel(String level) {
        List<ExerciseDto> exerciseListDtoList = exerciseRepository.findAllByLevel(level)
                .stream()
                .map(ExerciseMapper::map)
                .toList();
        return exerciseListDtoList;
    }

    ExerciseLevelInfoDto getLevelInfo(String level) {
        long count = exerciseRepository.findAllByLevel(level).stream().count();
        ExerciseLevelInfoDto exerciseLevelInfoDto = new ExerciseLevelInfoDto();
        exerciseLevelInfoDto.setName(level);
        exerciseLevelInfoDto.setNumberOfExercise((int) count);
        exerciseLevelInfoDto.setNumberOfPointToTarget(exerciseLogic.pointToPass(level));
        return exerciseLevelInfoDto;
    }

    List<ExerciseLevelInfoDto> getAllLevelInfo() {
        List<ExerciseLevelInfoDto> levelInfoDtoList = new ArrayList<>();
        levelInfoDtoList.add(getLevelInfo(Level.WHITE.name()));
        levelInfoDtoList.add(getLevelInfo(Level.RED.name()));
        levelInfoDtoList.add(getLevelInfo(Level.YELLOW.name()));
        levelInfoDtoList.add(getLevelInfo(Level.GREEN.name()));
        levelInfoDtoList.add(getLevelInfo(Level.BROWN.name()));
        levelInfoDtoList.add(getLevelInfo(Level.BLUE.name()));
        levelInfoDtoList.add(getLevelInfo(Level.PINK.name()));
        levelInfoDtoList.add(getLevelInfo(Level.BLACK.name()));
        return levelInfoDtoList;
    }

    List<ExerciseLevelInfoDto> getAllLevelInfoDemo() {
        List<ExerciseLevelInfoDto> levelInfoDtoList = new ArrayList<>();
        levelInfoDtoList.add(getLevelInfo(Level.WHITE.name()));
        return levelInfoDtoList;
    }

    List<Exercise> getAllExercises() {
        List<Exercise> exerciseList = exerciseRepository.findAll();
        exerciseList.sort(Comparator.comparing(Exercise::getExerciseId));
        return exerciseList;
    }

    Exercise addNewExercise(String name, String description, String videoUrl, String img, int numberOfPointsToPassed,
                            int maxPoints, int numberOfAttempts,int numberOfStrokesInOneAttempt ,String level, boolean isWhite, boolean isRed,
                            boolean isYellow, boolean isGreen, boolean isBrown, boolean isBlue, boolean isPink,
                            boolean isBlack, boolean isButtonPass, boolean isBonusPoint, String bonusInfo,
                            int bonusNumberOfPoints, int length) {
        Exercise exercise = new Exercise("empty");
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
        exercise.setLength(length);
        exerciseRepository.save(exercise);
        return exercise;
    }

    Exercise updateExercise(String currentExerciseId, String name, String description, String videoUrl,
                                   String img, int numberOfPointsToPassed, int maxPoints, int numberOfAttempts, int numberOfStrokesInOneAttempt,
                                   String level, boolean isWhite, boolean isRed, boolean isYellow, boolean isGreen,
                                   boolean isBrown, boolean isBlue, boolean isPink, boolean isBlack,
                                   boolean isButtonPass, boolean isBonusPoint, String bonusInfo,
                                   int bonusNumberOfPoints, int length) {
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
        currentExercise.setLength(length);
        exerciseRepository.save(currentExercise);
        return currentExercise;
    }

    void deleteExercise(String exerciseId) {
        Exercise exercise = exerciseRepository.findByExerciseId(exerciseId).get();
        exerciseRepository.delete(exercise);
    }
}
