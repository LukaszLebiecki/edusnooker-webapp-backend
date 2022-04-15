package pl.edusnooker.webapp.component.exercise;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.edusnooker.webapp.component.exercise.dto.ExerciseListDto;

import java.util.List;

@RestController
@RequestMapping("api/exercise")
class ExerciseController {
    private final ExerciseService exerciseService;

    public ExerciseController(ExerciseService exerciseService) {
        this.exerciseService = exerciseService;
    }

    @GetMapping("")
    ResponseEntity<List<ExerciseListDto>> getExercises() {
        if (exerciseService.getAllExercise().isEmpty()) {
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok(exerciseService.getAllExercise());
        }
    }
}
