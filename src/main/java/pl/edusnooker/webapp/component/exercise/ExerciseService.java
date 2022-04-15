package pl.edusnooker.webapp.component.exercise;

import org.springframework.stereotype.Service;
import pl.edusnooker.webapp.component.exercise.dto.ExerciseListDto;

import java.util.ArrayList;
import java.util.List;

@Service
class ExerciseService {
    private final ExerciseRepository exerciseRepository;
    private final ExerciseDtoMapper exerciseDtoMapper;

    public ExerciseService(ExerciseRepository exerciseRepository, ExerciseDtoMapper exerciseDtoMapper) {
        this.exerciseRepository = exerciseRepository;
        this.exerciseDtoMapper = exerciseDtoMapper;
    }

    List<ExerciseListDto> getAllExercise() {
        List<ExerciseListDto> exerciseListDto = new ArrayList<>();
        for (Exercise exercise : exerciseRepository.findAll()) {
            exerciseListDto.add(exerciseDtoMapper.map(exercise));
        }
        return exerciseListDto;
    }
}
