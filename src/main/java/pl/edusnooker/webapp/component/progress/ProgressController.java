package pl.edusnooker.webapp.component.progress;


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
    @CrossOrigin(origins = "*", maxAge = 3600) //todo tymczasowe rozwiązanie do testów
    ResponseEntity<List<ProgressLevelInfoDto>> getAllProgressByLevel(@PathVariable int userId) {
        List<ProgressLevelInfoDto> allProgressLevelInfoByUser = progressService.getAllProgressLevelInfoByUser(userId);
        if (allProgressLevelInfoByUser.isEmpty()) {
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok(allProgressLevelInfoByUser);
        }
    }

    @GetMapping("{userId}/progress/{levelId}")
    @CrossOrigin(origins = "*", maxAge = 3600) //todo tymczasowe rozwiązanie do testów
    ResponseEntity<List<ProgressExerciseDto>> getAllProgressExercise(@PathVariable int userId, @PathVariable int levelId) {
        List<ProgressExerciseDto> progressExerciseInfo = progressService.getProgressExerciseInfo(levelId, userId);
        if (progressExerciseInfo.isEmpty()) {
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok(progressExerciseInfo);
        }
    }

}
