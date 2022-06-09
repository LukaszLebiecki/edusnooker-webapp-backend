package pl.edusnooker.webapp.component.exercise.dto;

import lombok.Data;

@Data
public class ExerciseListDto {
    private String name;
    private String description;
    private String img;
    private int idExercise;
}
