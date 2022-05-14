package pl.edusnooker.webapp.component.exercise;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.edusnooker.webapp.component.exercise.dto.ExerciseDto;
import pl.edusnooker.webapp.component.exercise.dto.ExerciseLevelInfoDto;
import pl.edusnooker.webapp.component.exercise.dto.ExerciseListDto;
import pl.edusnooker.webapp.enumeration.Level;

import java.util.List;


@RestController
@RequestMapping("api")
class ExerciseController {
    private final ExerciseService exerciseService;

    public ExerciseController(ExerciseService exerciseService) {
        this.exerciseService = exerciseService;
    }


    @GetMapping("/level")
    ResponseEntity<List<ExerciseLevelInfoDto>> getLevelAll() {

        if (exerciseService.getAllLevelInfo().isEmpty()) {
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok(exerciseService.getAllLevelInfo());
        }
    }

    @GetMapping("/level/{idLevel}")
    ResponseEntity<ExerciseLevelInfoDto> getLevel(@PathVariable int idLevel) {
        Level[] values = Level.values();
        String name = values[idLevel].name();
        Level level = Level.valueOf(name);
        if (exerciseService.getLevelInfo(level) == null) {
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok(exerciseService.getLevelInfo(level));
        }
    }

    @GetMapping("/level/{idLevel}/exercise")
    ResponseEntity<List<ExerciseListDto>> getAllExerciseByLevel(@PathVariable int idLevel) {
        Level[] values = Level.values();
        String name = values[idLevel].name();
        Level level = Level.valueOf(name);
        if (exerciseService.getAllExerciseByLevel(level).isEmpty()) {
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok(exerciseService.getAllExerciseByLevel(level));
        }
    }

    @GetMapping("/exercise/{id}")
    ResponseEntity<ExerciseDto> getExerciseById(@PathVariable Long id) {
        if (exerciseService.getExerciseById(id).isEmpty()) {
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok(exerciseService.getExerciseById(id).get());
        }
    }
}
