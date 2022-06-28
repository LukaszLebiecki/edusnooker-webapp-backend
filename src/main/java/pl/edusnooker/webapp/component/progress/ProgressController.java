package pl.edusnooker.webapp.component.progress;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.edusnooker.webapp.component.progress.dto.ProgressExerciseDto;
import pl.edusnooker.webapp.component.progress.dto.ProgressLevelInfoDto;

import java.util.List;

@RestController
@RequestMapping("api")
class ProgressController {
    private final ProgressService progressService;

    public ProgressController(ProgressService progressService) {
        this.progressService = progressService;
    }

    @GetMapping("{userId}/progress")
    ResponseEntity<List<ProgressLevelInfoDto>> getAllProgressByLevel(@PathVariable String userId) {
        List<ProgressLevelInfoDto> allProgressLevelInfoByUser = progressService.getAllProgressLevelInfoByUser(userId);
        if (allProgressLevelInfoByUser.isEmpty()) {
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok(allProgressLevelInfoByUser);
        }
    }

    @GetMapping("{userId}/progress/{levelId}")
    ResponseEntity<List<ProgressExerciseDto>> getAllProgressExercise(@PathVariable String userId, @PathVariable int levelId) {
        List<ProgressExerciseDto> progressExerciseInfo = progressService.getProgressExerciseInfo(levelId, userId);
        if (progressExerciseInfo.isEmpty()) {
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok(progressExerciseInfo);
        }
    }

    @PostMapping("/progress/add")
    ResponseEntity<Progress> addProgress(@RequestParam("idExercise") String idExercise,
                                         @RequestParam("numberLevel") int numberLevel,
                                         @RequestParam("numberOfPointsToPassed") int numberOfPointsToPassed,
                                         @RequestParam("resultNumberOfPoint") int resultNumberOfPoint,
                                         @RequestParam("userId") String userId) {
        Progress newProgress = progressService.addProgress(idExercise, numberLevel, numberOfPointsToPassed,
                resultNumberOfPoint, userId);
        return new ResponseEntity<>(newProgress, HttpStatus.OK);
    }

}
