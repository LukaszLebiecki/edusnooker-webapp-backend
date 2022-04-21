package pl.edusnooker.webapp.component.exercise;

import org.springframework.stereotype.Service;
import pl.edusnooker.webapp.component.exercise.dto.ExerciseDto;
import pl.edusnooker.webapp.component.exercise.dto.ExerciseLevelInfoDto;
import pl.edusnooker.webapp.component.exercise.dto.ExerciseListDto;
import pl.edusnooker.webapp.component.exercise.dto.ExerciseListLevelDto;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Service
class ExerciseService {
    private final ExerciseRepository exerciseRepository;

    public ExerciseService(ExerciseRepository exerciseRepository) {
        this.exerciseRepository = exerciseRepository;
    }


    Optional<ExerciseDto> getExerciseById(Long id) {
        return exerciseRepository.findById(id).map(ExerciseMapper::map);
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

    List<ExerciseListLevelDto> getAllLevel() {
        List<ExerciseListLevelDto> listLevelDto = new ArrayList<>();
        listLevelDto.add(new ExerciseListLevelDto(Level.WHITE.name(), Level.WHITE.getNumberLevel()));
        listLevelDto.add(new ExerciseListLevelDto(Level.RED.name(), Level.RED.getNumberLevel()));
        listLevelDto.add(new ExerciseListLevelDto(Level.YELLOW.name(), Level.YELLOW.getNumberLevel()));
        listLevelDto.add(new ExerciseListLevelDto(Level.GREEN.name(), Level.GREEN.getNumberLevel()));
        listLevelDto.add(new ExerciseListLevelDto(Level.BROWN.name(), Level.BROWN.getNumberLevel()));
        listLevelDto.add(new ExerciseListLevelDto(Level.BLUE.name(), Level.BLUE.getNumberLevel()));
        listLevelDto.add(new ExerciseListLevelDto(Level.PINK.name(), Level.PINK.getNumberLevel()));
        listLevelDto.add(new ExerciseListLevelDto(Level.BLACK.name(), Level.BLACK.getNumberLevel()));
        return listLevelDto;
    }
}
