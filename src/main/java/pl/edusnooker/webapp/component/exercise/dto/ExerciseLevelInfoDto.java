package pl.edusnooker.webapp.component.exercise.dto;


import lombok.Data;

@Data
public class ExerciseLevelInfoDto {
    private String name;
    private int numberOfExercise;
    private int numberOfPointToTarget;

}
