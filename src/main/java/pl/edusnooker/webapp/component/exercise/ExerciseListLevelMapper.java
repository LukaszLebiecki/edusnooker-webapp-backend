package pl.edusnooker.webapp.component.exercise;

import org.springframework.stereotype.Service;
import pl.edusnooker.webapp.component.exercise.dto.ExerciseListLevelDto;

@Service
class ExerciseListLevelMapper {

    static ExerciseListLevelDto map(Exercise exercise) {
        ExerciseListLevelDto dto = new ExerciseListLevelDto();
        dto.setNameLevel(exercise.getLevel().toString());
        dto.setNumberPointToPass(exercise.getNumberPointsToPassed());
        return dto;
    }
}
