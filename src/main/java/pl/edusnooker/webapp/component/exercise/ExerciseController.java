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
    @PreAuthorize("hasAnyAuthority('user:basic')")
    ResponseEntity<List<ExerciseLevelInfoDto>> getLevelAll() {
        if (exerciseService.getAllLevelInfo().isEmpty()) {
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok(exerciseService.getAllLevelInfo());
        }
    }

    @GetMapping("/level/demo")
    @PreAuthorize("hasAnyAuthority('user:demo')")
    ResponseEntity<List<ExerciseLevelInfoDto>> getLevelAllDemo() {
        if (exerciseService.getAllLevelInfo().isEmpty()) {
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok(exerciseService.getAllLevelInfoDemo());
        }
    }


    @GetMapping("/level/{idLevel}")
    @PreAuthorize("hasAnyAuthority('user:demo')")
    ResponseEntity<ExerciseLevelInfoDto> getLevel(@PathVariable int idLevel) {
        Level[] values = Level.values();
        String name = values[idLevel].name();
        Level level = Level.valueOf(name);
        if (exerciseService.getLevelInfo(name) == null) {
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok(exerciseService.getLevelInfo(name));
        }
    }

    @GetMapping("/level/{idLevel}/exercise")
    @PreAuthorize("hasAnyAuthority('user:basic')")
    ResponseEntity<List<ExerciseDto>> getAllExerciseByLevel(@PathVariable int idLevel) {
        Level[] values = Level.values();
        String name = values[idLevel].name();
        Level level = Level.valueOf(name);
        if (exerciseService.getAllExerciseByLevel(name).isEmpty()) {
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok(exerciseService.getAllExerciseByLevel(name));
        }
    }

    @GetMapping("/level/0/exercise")
    @PreAuthorize("hasAnyAuthority('user:demo')")
    ResponseEntity<List<ExerciseDto>> getAllExerciseByLevel() {
        Level[] values = Level.values();
        String name = values[0].name();
        Level level = Level.valueOf(name);
        if (exerciseService.getAllExerciseByLevel(name).isEmpty()) {
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok(exerciseService.getAllExerciseByLevel(name));
        }
    }

    @GetMapping("/exercise/{id}")
    @PreAuthorize("hasAnyAuthority('user:basic')")
    ResponseEntity<ExerciseDto> getExerciseById(@PathVariable String id) {
        if (exerciseService.getExerciseById(id).isEmpty()) {
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok(exerciseService.getExerciseById(id).get());
        }
    }

    @GetMapping("{user}/exercise/slot/one")
    @PreAuthorize("hasAnyAuthority('user:basic')")
    ResponseEntity<ExerciseDto> getExerciseSlotOne(@PathVariable String user) {
        if (exerciseService.getExerciseSlotOne(user).isEmpty()) {
            ExerciseDto emptyExercie = new ExerciseDto();
            emptyExercie.setExerciseId("e000");
            return ResponseEntity.ok(emptyExercie);
        } else {
            return ResponseEntity.ok(exerciseService.getExerciseSlotOne(user).get());
        }
    }

    @GetMapping("{user}/exercise/slot/two")
    @PreAuthorize("hasAnyAuthority('user:basic')")
    ResponseEntity<ExerciseDto> getExerciseSlotTwo(@PathVariable String user) {
        if (exerciseService.getExerciseSlotTwo(user).isEmpty()) {
            ExerciseDto emptyExercie = new ExerciseDto();
            emptyExercie.setExerciseId("e000");
            return ResponseEntity.ok(emptyExercie);
        } else {
            return ResponseEntity.ok(exerciseService.getExerciseSlotTwo(user).get());
        }
    }

    @GetMapping("{user}/exercise/slot/three")
    @PreAuthorize("hasAnyAuthority('user:basic')")
    ResponseEntity<ExerciseDto> getExerciseSlotThree(@PathVariable String user) {
        if (exerciseService.getExerciseSlotThree(user).isEmpty()) {
            ExerciseDto emptyExercie = new ExerciseDto();
            emptyExercie.setExerciseId("e000");
            return ResponseEntity.ok(emptyExercie);
        } else {
            return ResponseEntity.ok(exerciseService.getExerciseSlotThree(user).get());
        }
    }



    @GetMapping("/exercise")
    @PreAuthorize("hasAnyAuthority('user:basic')")
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
    @PreAuthorize("hasAnyAuthority('user:delete')")
    ResponseEntity<Exercise> addNewExercise(@RequestParam("name") String name,
                                            @RequestParam("description") String description,
                                            @RequestParam("videoUrl") String videoUrl,
                                            @RequestParam("img") String img,
                                            @RequestParam("numberOfPointsToPassed") int numberOfPointsToPassed,
                                            @RequestParam("maxPoints") int maxPoints,
                                            @RequestParam("numberOfAttempts") int numberOfAttempts,
                                            @RequestParam("numberOfStrokesInOneAttempt") int numberOfStrokesInOneAttempt,
                                            @RequestParam("level") String level,
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
                                            @RequestParam("bonusNumberOfPoints") int bonusNumberOfPoints,
                                            @RequestParam("length") int length) {
        Exercise newExercise = exerciseService.addNewExercise(name, description, videoUrl, img, numberOfPointsToPassed,
                maxPoints, numberOfAttempts, numberOfStrokesInOneAttempt, level, Boolean.parseBoolean(isWhite), Boolean.parseBoolean(isRed),
                Boolean.parseBoolean(isYellow), Boolean.parseBoolean(isGreen), Boolean.parseBoolean(isBrown),
                Boolean.parseBoolean(isBlue), Boolean.parseBoolean(isPink), Boolean.parseBoolean(isBlack),
                Boolean.parseBoolean(isButtonPass), Boolean.parseBoolean(isBonusPoint), bonusInfo, bonusNumberOfPoints,
                length);
        return new ResponseEntity<>(newExercise, HttpStatus.OK);
    }

    @PostMapping("/exercise/update")
    @PreAuthorize("hasAnyAuthority('user:delete')")
    ResponseEntity<Exercise> updateExercise(@RequestParam("currentExerciseId") String currentExerciseId,
                                            @RequestParam("name") String name,
                                            @RequestParam("description") String description,
                                            @RequestParam("videoUrl") String videoUrl,
                                            @RequestParam("img") String img,
                                            @RequestParam("numberOfPointsToPassed") int numberOfPointsToPassed,
                                            @RequestParam("maxPoints") int maxPoints,
                                            @RequestParam("numberOfAttempts") int numberOfAttempts,
                                            @RequestParam("numberOfStrokesInOneAttempt") int numberOfStrokesInOneAttempt,
                                            @RequestParam("level") String level,
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
                                            @RequestParam("bonusNumberOfPoints") int bonusNumberOfPoints,
                                            @RequestParam("length") int length) {
        Exercise updatedExercise = exerciseService.updateExercise(currentExerciseId, name, description, videoUrl, img, numberOfPointsToPassed,
                maxPoints, numberOfAttempts, numberOfStrokesInOneAttempt, level, isWhite, isRed, isYellow, isGreen, isBrown, isBlue, isPink, isBlack,
                isButtonPass, isBonusPoint, bonusInfo, bonusNumberOfPoints, length);
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
