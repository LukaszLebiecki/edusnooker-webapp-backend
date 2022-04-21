package pl.edusnooker.webapp.component.exercise.dto;

import lombok.Data;

@Data
public class ExerciseDto {
    private String name;
    private String description;
    private String gif;
    private int numberOfPointsToPassed;
}
