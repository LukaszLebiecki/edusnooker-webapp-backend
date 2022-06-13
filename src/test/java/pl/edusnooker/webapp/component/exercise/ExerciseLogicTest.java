package pl.edusnooker.webapp.component.exercise;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import pl.edusnooker.webapp.enumeration.Level;

import static org.junit.jupiter.api.Assertions.*;

class ExerciseLogicTest {

    @Test
    void generateExerciseId() {
        // given
        ExerciseLogic exerciseLogic = new ExerciseLogic();

        // when
        String result = exerciseLogic.generateExerciseId(Level.GREEN);

        // then
        Assertions.assertEquals("e300", result);
    }
}