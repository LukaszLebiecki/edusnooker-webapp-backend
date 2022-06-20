package pl.edusnooker.webapp.component.exercise;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import pl.edusnooker.webapp.enumeration.Level;

class ExerciseLogicTest {

    ExerciseRepository mockedExerciseRepository = Mockito.mock(ExerciseRepository.class);


    @Test
    void shouldGenerateExerciseIdForLevelGreenResultE300() {
        // given
        ExerciseLogic exerciseLogic = new ExerciseLogic(mockedExerciseRepository);

        // when
        String result = exerciseLogic.generateExerciseId(Level.GREEN);

        // then
        Assertions.assertEquals("e301", result);
    }

    @Test
    void shouldGenerateExerciseIdForLevelWhiteResultE000() {
        // given
        ExerciseLogic exerciseLogic = new ExerciseLogic(mockedExerciseRepository);

        // when
        String result = exerciseLogic.generateExerciseId(Level.WHITE);

        // then
        Assertions.assertEquals("e001", result);
    }

    @Test
    void shouldGenerateExerciseIdForLevelBlackResultE700() {
        // given
        ExerciseLogic exerciseLogic = new ExerciseLogic(mockedExerciseRepository);

        // when
        String result = exerciseLogic.generateExerciseId(Level.BLACK);

        // then
        Assertions.assertEquals("e701", result);
    }
}