package pl.edusnooker.webapp.component.exercise;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.edusnooker.webapp.component.exercise.dto.ExerciseListLevelDto;

import java.util.List;

@RestController
@RequestMapping("api/level")
class ExerciseController {
    private final ExerciseService exerciseService;

    public ExerciseController(ExerciseService exerciseService) {
        this.exerciseService = exerciseService;
    }

    @GetMapping("")
    ResponseEntity<List<ExerciseListLevelDto>> getLevelAll() {
        if (exerciseService.getAllLevel().isEmpty()) {
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok(exerciseService.getAllLevel());
        }
    }

    @GetMapping("/{level}")
    ResponseEntity<List<ExerciseListLevelDto>> getLevel(@PathVariable Level level) {
        if (exerciseService.getAllExerciseByLevel(level).isEmpty()) {
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok(exerciseService.getAllExerciseByLevel(level));
        }
    }
}
