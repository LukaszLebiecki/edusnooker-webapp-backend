package pl.edusnooker.webapp.component.exercise;

import org.springframework.stereotype.Service;
import pl.edusnooker.webapp.component.exercise.dto.ExerciseListDto;

@Service
class ExerciseDtoMapper {

    ExerciseListDto map(Exercise exercise) {
        ExerciseListDto dto = new ExerciseListDto();
        dto.setName(exercise.getName());
        dto.setLevel(exercise.getLevel());
        return dto;
    }
}
