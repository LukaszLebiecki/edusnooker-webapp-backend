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

    public ExerciseService(ExerciseRepository exerciseRepository) {
        this.exerciseRepository = exerciseRepository;
    }


    Optional<ExerciseDto> getExerciseById(String id) {
        return exerciseRepository.findByExerciseId(id).map(ExerciseMapper::map);
    }

    List<ExerciseListDto> getAllExerciseByLevel(Level level) {
        List<ExerciseListDto> exerciseListDtoList = exerciseRepository.findAllByLevel(level)
                .stream()
                .map(ExerciseListMapper::map)
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
        return exerciseList;
    }

}
