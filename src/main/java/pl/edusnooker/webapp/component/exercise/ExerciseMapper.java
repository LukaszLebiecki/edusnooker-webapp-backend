package pl.edusnooker.webapp.component.exercise;

import pl.edusnooker.webapp.component.exercise.dto.ExerciseDto;

class ExerciseMapper {
    static ExerciseDto map(Exercise exercise) {
        ExerciseDto dto = new ExerciseDto();
        dto.setName(exercise.getName());
        dto.setDescription(exercise.getDescription());
        dto.setGif(exercise.getGif());
        dto.setNumberOfPointsToPassed(exercise.getNumberOfPointsToPassed());
        return dto;
    }
}
