package pl.edusnooker.webapp.component.exercise;

import pl.edusnooker.webapp.component.exercise.dto.ExerciseListDto;

class ExerciseListMapper {

    static ExerciseListDto map(Exercise exercise) {
        ExerciseListDto dto = new ExerciseListDto();
        dto.setName(exercise.getName());
        dto.setShortDescription(exercise.getShortDescription());
        dto.setImg(exercise.getImg());
        dto.setIdExercise(Math.toIntExact(exercise.getId()));
        return dto;
    }
}
