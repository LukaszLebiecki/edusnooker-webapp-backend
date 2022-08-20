package pl.edusnooker.webapp.component.progress;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import pl.edusnooker.webapp.component.exercise.dto.ExerciseDto;
import pl.edusnooker.webapp.component.progress.dto.*;

import java.util.List;

@RestController
@RequestMapping("api")
class ProgressController {
    private final ProgressService progressService;

    public ProgressController(ProgressService progressService) {
        this.progressService = progressService;
    }

    @GetMapping("{userId}/progress")
    @PreAuthorize("hasAnyAuthority('user:demo')")
    ResponseEntity<List<ProgressLevelInfoDto>> getAllProgressByLevel(@PathVariable String userId) {
        List<ProgressLevelInfoDto> allProgressLevelInfoByUser = progressService.getAllProgressLevelInfoByUser(userId);
        if (allProgressLevelInfoByUser.isEmpty()) {
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok(allProgressLevelInfoByUser);
        }
    }

    @GetMapping("{userId}/progress/{levelId}")
    @PreAuthorize("hasAnyAuthority('user:demo')")
    ResponseEntity<List<ProgressExerciseDto>> getAllProgressExercise(@PathVariable String userId, @PathVariable int levelId) {
        List<ProgressExerciseDto> progressExerciseInfo = progressService.getProgressExerciseInfo(levelId, userId);
        if (progressExerciseInfo.isEmpty()) {
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok(progressExerciseInfo);
        }
    }

    @GetMapping("{userId}/progress/lastexercise")
    @PreAuthorize("hasAnyAuthority('user:demo')")
    ResponseEntity<ExerciseDto> getLastExercise(@PathVariable String userId) {
        ExerciseDto exerciseLast = progressService.getLastExerciseInfo(userId);
        return ResponseEntity.ok(exerciseLast);
    }

    @PostMapping("/progress/add")
    @PreAuthorize("hasAnyAuthority('user:demo')")
    ResponseEntity<Progress> addProgress(@RequestParam("idExercise") String idExercise,
                                         @RequestParam("numberLevel") int numberLevel,
                                         @RequestParam("numberOfPointsToPassed") int numberOfPointsToPassed,
                                         @RequestParam("resultNumberOfPoint") int resultNumberOfPoint,
                                         @RequestParam("userId") String userId) {
        Progress newProgress = progressService.addProgress(idExercise, numberLevel, numberOfPointsToPassed,
                resultNumberOfPoint, userId);
        return new ResponseEntity<>(newProgress, HttpStatus.OK);
    }

    @GetMapping("{userId}/progress/chartshome")
    @PreAuthorize("hasAnyAuthority('user:demo')")
    ResponseEntity<ProgressChartsHomeDto> getChartsHome(@PathVariable String userId) {
        int[] chartsHome = progressService.getChartsHomeByUserId(userId);
        ProgressChartsHomeDto progressChartsHomeDto = new ProgressChartsHomeDto();
        progressChartsHomeDto.setChartsHome(chartsHome);
        return ResponseEntity.ok(progressChartsHomeDto);
    }

    @GetMapping("{userId}/statistic/years")
    @PreAuthorize("hasAnyAuthority('user:demo')")
    ResponseEntity<ProgressYearDto> getProgressYear(@PathVariable String userId) {
        int[] progressYear = progressService.getProgressYear(userId);
        ProgressYearDto progressYearDto = new ProgressYearDto();
        progressYearDto.setProgressYear(progressYear);
        return ResponseEntity.ok(progressYearDto);
    }

    @GetMapping("{userId}/progress/counterHome")
    @PreAuthorize("hasAnyAuthority('user:demo')")
    ResponseEntity<ProgressCounterHomeDto> getCounterHome(@PathVariable String userId) {
        ProgressCounterHomeDto counterHome = progressService.getCounterHome(userId);
        return ResponseEntity.ok(counterHome);
    }

    @GetMapping("{userId}/statistic/{month}/{year}")
    @PreAuthorize("hasAnyAuthority('user:demo')")
    ResponseEntity<ProgressStatisticsDto> getStatistic(@PathVariable String userId, @PathVariable int year, @PathVariable int month) {
        ProgressStatisticsDto progressStatisticsDto = new ProgressStatisticsDto();

        progressStatisticsDto.setPointsScoredToYear(progressService.getPointsScoredToYear(userId, year));
        progressStatisticsDto.setExercisesPerformedToYear(progressService.getExercisesPerformedToYear(userId, year));
        progressStatisticsDto.setExercisesCompletedToYear(progressService.getExercisesCompletedToYear(userId, year));

        progressStatisticsDto.setPointsScoredToMonth(progressService.getPointsScoredToMonth(userId, year, month));
        progressStatisticsDto.setExercisesPerformedToMonth(progressService.getExercisesPerformedToMonth(userId, year, month));
        progressStatisticsDto.setExercisesCompletedToMonth(progressService.getExercisesCompletedToMonth(userId, year, month));

        progressStatisticsDto.setPointsScoredToHour(progressService.getPointsScoredToHour(userId));
        progressStatisticsDto.setExercisesPerformedToHour(progressService.getExercisesPerformedToHour(userId));
        progressStatisticsDto.setExercisesCompletedToHour(progressService.getExercisesCompletedToHour(userId));

        return ResponseEntity.ok(progressStatisticsDto);
    }


}
