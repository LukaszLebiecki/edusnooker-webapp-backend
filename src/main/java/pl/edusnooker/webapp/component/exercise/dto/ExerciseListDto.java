package pl.edusnooker.webapp.component.exercise.dto;

import lombok.Data;
import pl.edusnooker.webapp.component.user.Level;

@Data
 public class ExerciseListDto {
    private String name;
    private Level level;
}
