package pl.edusnooker.webapp.component.exercise;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import pl.edusnooker.webapp.component.exercise.dto.ExerciseDto;
import pl.edusnooker.webapp.component.exercise.dto.ExerciseLevelInfoDto;
import pl.edusnooker.webapp.component.exercise.dto.ExerciseListDto;
import pl.edusnooker.webapp.enumeration.Level;
import pl.edusnooker.webapp.http.HttpResponse;

import java.io.IOException;
import java.util.List;

import static pl.edusnooker.webapp.component.user.UserController.USER_DELETE_SUCCESSFULLY;


@RestController
@RequestMapping("api")
class ExerciseController {
    public static final String EXERCISE_DELETE_SUCCESSFULLY = "Exercise delete successfully";
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
    ResponseEntity<List<ExerciseDto>> getAllExerciseByLevel(@PathVariable int idLevel) {
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
    ResponseEntity<ExerciseDto> getExerciseById(@PathVariable String id) {
        if (exerciseService.getExerciseById(id).isEmpty()) {
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok(exerciseService.getExerciseById(id).get());
        }
    }

    @GetMapping("/exercise")
    ResponseEntity<List<ExerciseDto>> getExercises() {
        if (exerciseService.getAllExercises().isEmpty()) {
            return ResponseEntity.notFound().build();
        } else {
            List<Exercise> allExercises = exerciseService.getAllExercises();
            List<ExerciseDto> exerciseDtoList = allExercises.stream()
                    .map(ExerciseMapper::map)
                    .toList();
            return ResponseEntity.ok(exerciseDtoList);
        }
    }

    @PostMapping("/exercise/add")
    ResponseEntity<Exercise> addNewExercise(@RequestParam("name") String name,
                                            @RequestParam("description") String description,
                                            @RequestParam("videoUrl") String videoUrl,
                                            @RequestParam("img") String img,
                                            @RequestParam("numberOfPointsToPassed") int numberOfPointsToPassed,
                                            @RequestParam("maxPoints") int maxPoints,
                                            @RequestParam("numberOfAttempts") int numberOfAttempts,
                                            @RequestParam("numberOfStrokesInOneAttempt") int numberOfStrokesInOneAttempt,
                                            @RequestParam("level") Level level,
                                            @RequestParam("isWhite") String isWhite,
                                            @RequestParam("isRed") String isRed,
                                            @RequestParam("isYellow") String isYellow,
                                            @RequestParam("isGreen") String isGreen,
                                            @RequestParam("isBrown") String isBrown,
                                            @RequestParam("isBlue") String isBlue,
                                            @RequestParam("isPink") String isPink,
                                            @RequestParam("isBlack") String isBlack,
                                            @RequestParam("isButtonPass") String isButtonPass,
                                            @RequestParam("isBonusPoint") String isBonusPoint,
                                            @RequestParam("bonusInfo") String bonusInfo,
                                            @RequestParam("bonusNumberOfPoints") int bonusNumberOfPoints) {
        Exercise newExercise = exerciseService.addNewExercise(name, description, videoUrl, img, numberOfPointsToPassed,
                maxPoints, numberOfAttempts, numberOfStrokesInOneAttempt,level, Boolean.parseBoolean(isWhite), Boolean.parseBoolean(isRed),
                Boolean.parseBoolean(isYellow), Boolean.parseBoolean(isGreen), Boolean.parseBoolean(isBrown),
                Boolean.parseBoolean(isBlue), Boolean.parseBoolean(isPink), Boolean.parseBoolean(isBlack),
                Boolean.parseBoolean(isButtonPass), Boolean.parseBoolean(isBonusPoint), bonusInfo, bonusNumberOfPoints);
        return new ResponseEntity<>(newExercise, HttpStatus.OK);
    }

    @PostMapping("/exercise/update")
    ResponseEntity<Exercise> updateExercise(@RequestParam("currentExerciseId") String currentExerciseId,
                                            @RequestParam("name") String name,
                                            @RequestParam("description") String description,
                                            @RequestParam("videoUrl") String videoUrl,
                                            @RequestParam("img") String img,
                                            @RequestParam("numberOfPointsToPassed") int numberOfPointsToPassed,
                                            @RequestParam("maxPoints") int maxPoints,
                                            @RequestParam("numberOfAttempts") int numberOfAttempts,
                                            @RequestParam("numberOfStrokesInOneAttempt") int numberOfStrokesInOneAttempt,
                                            @RequestParam("level") Level level,
                                            @RequestParam("isWhite") boolean isWhite,
                                            @RequestParam("isRed") boolean isRed,
                                            @RequestParam("isYellow") boolean isYellow,
                                            @RequestParam("isGreen") boolean isGreen,
                                            @RequestParam("isBrown") boolean isBrown,
                                            @RequestParam("isBlue") boolean isBlue,
                                            @RequestParam("isPink") boolean isPink,
                                            @RequestParam("isBlack") boolean isBlack,
                                            @RequestParam("isButtonPass") boolean isButtonPass,
                                            @RequestParam("isBonusPoint") boolean isBonusPoint,
                                            @RequestParam("bonusInfo") String bonusInfo,
                                            @RequestParam("bonusNumberOfPoints") int bonusNumberOfPoints) {
        Exercise updatedExercise = exerciseService.updateExercise(currentExerciseId, name, description, videoUrl, img, numberOfPointsToPassed,
                maxPoints, numberOfAttempts, numberOfStrokesInOneAttempt, level, isWhite, isRed, isYellow, isGreen, isBrown, isBlue, isPink, isBlack,
                isButtonPass, isBonusPoint, bonusInfo, bonusNumberOfPoints);
        return new ResponseEntity<>(updatedExercise, HttpStatus.OK);
    }

    @DeleteMapping("/exercise/delete/{exerciseId}")
    @PreAuthorize("hasAnyAuthority('user:delete')")
    public ResponseEntity<HttpResponse> deleteUser(@PathVariable("exerciseId") String exerciseId) {
        exerciseService.deleteExercise(exerciseId);
        return response(HttpStatus.OK, EXERCISE_DELETE_SUCCESSFULLY);
    }

    private ResponseEntity<HttpResponse> response(HttpStatus httpStatus, String message) {
        return new ResponseEntity<>(new HttpResponse(httpStatus.value(), httpStatus,
                httpStatus.getReasonPhrase().toUpperCase(), message), httpStatus);
    }
}
