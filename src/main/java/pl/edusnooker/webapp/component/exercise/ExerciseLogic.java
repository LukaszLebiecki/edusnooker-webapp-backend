package pl.edusnooker.webapp.component.exercise;

import org.springframework.stereotype.Component;
import pl.edusnooker.webapp.enumeration.Level;

@Component
class ExerciseLogic {

    String generateExerciseId(Level level) {
        return "e300";
    }

}
