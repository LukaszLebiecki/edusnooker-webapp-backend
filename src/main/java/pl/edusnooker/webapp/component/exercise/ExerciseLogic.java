package pl.edusnooker.webapp.component.exercise;

import org.springframework.stereotype.Component;
import pl.edusnooker.webapp.enumeration.Level;

import java.util.List;

@Component
class ExerciseLogic {

    private final ExerciseRepository exerciseRepository;

    ExerciseLogic(ExerciseRepository exerciseRepository) {
        this.exerciseRepository = exerciseRepository;
    }

    String generateExerciseId(String level) {
        Level levelValue = Level.valueOf(level);
        int numberLevel = levelValue.getNumberLevel();
        String nextString;

        List<Exercise> allByLevelOrderByExerciseIdDesc = exerciseRepository.findAllByLevelOrderByExerciseIdDesc(levelValue.name());
        Exercise exercise = allByLevelOrderByExerciseIdDesc.stream()
                .findFirst()
                .orElse(new Exercise("empty"));

        if (exercise.getExerciseId() != null) {
            String exerciseId = exercise.getExerciseId();
            char[] chars = exerciseId.toCharArray();
            String chars2 = String.valueOf(chars[2]);
            String chars3 = String.valueOf(chars[3]);
            String charsAll = chars2 + chars3;
            int exerciseLast = Integer.valueOf(charsAll);
            int next = exerciseLast + 1;
            if (next > 9) {
                nextString = String.valueOf(next);
            } else {
                nextString = "0" + next;
            }
            StringBuilder result = new StringBuilder();
            result.append("e")
                    .append(numberLevel)
                    .append(nextString);
            return result.toString();
        } else {
            StringBuilder result = new StringBuilder();
            result.append("e")
                    .append(numberLevel)
                    .append("01");
            return result.toString();
        }
    }

}
