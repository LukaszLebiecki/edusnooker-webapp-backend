package pl.edusnooker.webapp.component.exercise;

import org.springframework.stereotype.Service;
import pl.edusnooker.webapp.component.exercise.dto.ExerciseListLevelDto;

import java.util.List;

@Service
class ExerciseService {
    private final ExerciseRepository exerciseRepository;


    public ExerciseService(ExerciseRepository exerciseRepository) {
        this.exerciseRepository = exerciseRepository;
    }


    List<ExerciseListLevelDto> getAllExerciseByLevel(Level level) {
        List<ExerciseListLevelDto> exerciseListLevelDtoList = exerciseRepository.findAllByLevel(level)
                .stream()
                .map(ExerciseListLevelMapper::map)
                .toList();
        return exerciseListLevelDtoList;
    }

    List<ExerciseListLevelDto> getAllLevel() {
        List<ExerciseListLevelDto> exerciseListLevelDtoList = exerciseRepository.findAll()
                .stream()
                .map(ExerciseListLevelMapper::map)
                .toList();
        return exerciseListLevelDtoList;
    }
}
