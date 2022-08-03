package pl.edusnooker.webapp.component.progress;

import org.springframework.stereotype.Service;
import pl.edusnooker.webapp.component.exercise.Exercise;
import pl.edusnooker.webapp.component.exercise.ExerciseMapper;
import pl.edusnooker.webapp.component.exercise.dto.ExerciseDto;
import pl.edusnooker.webapp.component.progress.dto.ProgressCounterHomeDto;
import pl.edusnooker.webapp.component.progress.dto.ProgressExerciseDto;
import pl.edusnooker.webapp.component.progress.dto.ProgressLevelInfoDto;

import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;


@Service
class ProgressService {


    private final ProgressLogic progressLogic;
    private final ProgressRepository progressRepository;

    public ProgressService(ProgressLogic progressLogic, ProgressRepository progressRepository) {
        this.progressLogic = progressLogic;
        this.progressRepository = progressRepository;
    }

    List<ProgressLevelInfoDto> getAllProgressLevelInfoByUser(String userId) {
        List<ProgressLevelInfoDto> progressLevelInfoDtoList = new ArrayList<>();
        progressLevelInfoDtoList.add(progressLogic.getByLevel(0, userId));
        progressLevelInfoDtoList.add(progressLogic.getByLevel(1, userId));
        progressLevelInfoDtoList.add(progressLogic.getByLevel(2, userId));
        progressLevelInfoDtoList.add(progressLogic.getByLevel(3, userId));
        progressLevelInfoDtoList.add(progressLogic.getByLevel(4, userId));
        progressLevelInfoDtoList.add(progressLogic.getByLevel(5, userId));
        progressLevelInfoDtoList.add(progressLogic.getByLevel(6, userId));
        progressLevelInfoDtoList.add(progressLogic.getByLevel(7, userId));
        return progressLevelInfoDtoList;
    }

    List<ProgressExerciseDto> getProgressExerciseInfo(int numberLevel, String userId) {
        List<Progress> allProgressByLevel = progressLogic.getAllProgressByLevel(numberLevel, userId);
        Set<ProgressExerciseDto> collect = allProgressByLevel.stream().map(p -> progressLogic.create(p, userId))
                .peek(e -> {
                    int theBestResult = e.getTheBestResult();
                    int resultToPass = e.getResultToPass();
                    if (theBestResult >= resultToPass)
                        e.setPass(true);
                })
                .collect(Collectors.toSet());
        List<ProgressExerciseDto> progressExerciseDtoList = collect.stream().toList();
        return progressExerciseDtoList.stream().sorted().toList();
    }

    Progress addProgress(String idExercise, int numberLevel, int numberOfPointsToPassed,
                         int resultNumberOfPoint, String userId) {
        Progress progress = new Progress();
        progress.setIdExercise(idExercise);
        progress.setNumberLevel(numberLevel);
        progress.setNumberOfPointsToPassed(numberOfPointsToPassed);
        progress.setResultNumberOfPoint(resultNumberOfPoint);
        progress.setUserId(userId);
        progress.setDateTimeExercise(LocalDateTime.now());
        progressRepository.save(progress);
        return progress;
    }

    public ExerciseDto getLastExerciseInfo(String userId) {
        Exercise exercise = progressLogic.lastExercise(userId);
        ExerciseDto exerciseDto = ExerciseMapper.map(exercise);
        return exerciseDto;
    }

    public int[] getChartsHomeByUserId(String userId) {
        LocalDateTime now = LocalDateTime.now();
        int year = now.getYear();
        int[] getProgressChartsByUserId = new int[12];
        for (int i = 1; i <= 12; i++) {
            getProgressChartsByUserId[i - 1] = (int) progressRepository
                    .findAllProgressByUserIdAndDateTimeExerciseToYearAndMonth(userId, i, year)
                    .stream()
                    .count();
        }
        return getProgressChartsByUserId;
    }

    public ProgressCounterHomeDto getCounterHome(String userId) {
        ProgressCounterHomeDto progressCounterHomeDto = new ProgressCounterHomeDto();
        progressCounterHomeDto.setPointScored(progressLogic.getAllProgressPointScored(userId));
        progressCounterHomeDto.setExercisePerformed(progressLogic.getAllProgressExercisePerformed(userId));
        progressCounterHomeDto.setCompletedExercises(progressLogic.getAllProgressCompletedExercises(userId));
        return progressCounterHomeDto;
    }

    public int[] getPointsScoredToYear(String userId, int year) {
        int[] getPointsScoredToYear = new int[12];
        for (int i = 1; i <= 12; i++) {
            Integer points = progressRepository
                    .findAllProgressByUserIdAndDateTimeExerciseToYearAndMonth(userId, i, year)
                    .stream()
                    .map(progress -> progress.getResultNumberOfPoint())
                    .reduce(0, Integer::sum);
            getPointsScoredToYear[i - 1] = points;
        }
        return getPointsScoredToYear;
    }

    public int[] getExercisesPerformedToYear(String userId, int year) {
        int[] getExercisesPerformedToYear = new int[12];
        for (int i = 1; i <= 12; i++) {
            getExercisesPerformedToYear[i - 1] = (int) progressRepository
                    .findAllProgressByUserIdAndDateTimeExerciseToYearAndMonth(userId, i, year)
                    .stream()
                    .count();
        }
        return getExercisesPerformedToYear;
    }

    public int[] getExercisesCompletedToYear(String userId, int year) {
        int[] getExercisesCompletedToYear = new int[12];
        for (int i = 1; i <= 12; i++) {
            getExercisesCompletedToYear[i - 1] = (int) progressRepository
                    .findAllProgressByUserIdAndDateTimeExerciseToYearAndMonth(userId, i, year)
                    .stream()
                    .filter(progress -> progress.getResultNumberOfPoint() >= progress.getNumberOfPointsToPassed())
                    .count();
        }
        return getExercisesCompletedToYear;
    }

    public int[] getPointsScoredToMonth(String userId, int year, int month) {
        int daysInMonth = progressLogic.geDayCount(year, month);
        int[] getPointsScoredToMonth = new int[daysInMonth];
        for (int i = 1; i <= daysInMonth; i++) {
            Integer points = progressRepository
                    .findAllProgressByUserIdAndDateTimeExerciseToYearAndMonthAndDay(userId, month, year, i)
                    .stream()
                    .map(progress -> progress.getResultNumberOfPoint())
                    .reduce(0, Integer::sum);
            getPointsScoredToMonth[i - 1] = points;
        }
        return getPointsScoredToMonth;
    }

    public int[] getExercisesPerformedToMonth(String userId, int year, int month) {
        int daysInMonth = progressLogic.geDayCount(year, month);
        int[] getExercisesPerformedToMonth = new int[daysInMonth];
        for (int i = 1; i <= daysInMonth; i++) {
            getExercisesPerformedToMonth[i - 1] = (int) progressRepository
                    .findAllProgressByUserIdAndDateTimeExerciseToYearAndMonthAndDay(userId, month, year, i)
                    .stream()
                    .count();
        }
        return getExercisesPerformedToMonth;
    }

    public int[] getExercisesCompletedToMonth(String userId, int year, int month) {
        int daysInMonth = progressLogic.geDayCount(year, month);
        int[] getExercisesCompletedToMonth = new int[daysInMonth];
        for (int i = 1; i <= daysInMonth; i++) {
            getExercisesCompletedToMonth[i - 1] = (int) progressRepository
                    .findAllProgressByUserIdAndDateTimeExerciseToYearAndMonthAndDay(userId, month, year, i)
                    .stream()
                    .filter(progress -> progress.getResultNumberOfPoint() >= progress.getNumberOfPointsToPassed())
                    .count();
        }
        return getExercisesCompletedToMonth;
    }

    public int[] getPointsScoredToHour(String userId) {
        int[] getPointsScoredToHour = new int[24];
        for (int i = 1; i <= 24; i++) {
            Integer points = progressRepository
                    .findAllProgressByUserIdAndDateTimeExerciseToHour(userId, i)
                    .stream()
                    .map(progress -> progress.getResultNumberOfPoint())
                    .reduce(0, Integer::sum);
            getPointsScoredToHour[i - 1] = points;
        }
        return getPointsScoredToHour;
    }

    public int[] getExercisesPerformedToHour(String userId) {
        int[] getExercisesPerformedToHour = new int[24];
        for (int i = 1; i <= 24; i++) {
            getExercisesPerformedToHour[i - 1] = (int) progressRepository
                    .findAllProgressByUserIdAndDateTimeExerciseToHour(userId, i)
                    .stream()
                    .count();
        }
        return getExercisesPerformedToHour;
    }

    public int[] getExercisesCompletedToHour(String userId) {
        int[] getExercisesCompletedToHour = new int[24];
        for (int i = 1; i <= 24; i++) {
            getExercisesCompletedToHour[i - 1] = (int) progressRepository
                    .findAllProgressByUserIdAndDateTimeExerciseToHour(userId, i)
                    .stream()
                    .filter(progress -> progress.getResultNumberOfPoint() >= progress.getNumberOfPointsToPassed())
                    .count();
        }
        return getExercisesCompletedToHour;
    }

    public int[] getProgressYear(String userId) {
        List<Integer> integerList = progressRepository.findAllByUserIdOrderByDateTimeExerciseDesc(userId)
                .stream()
                .map(Progress::getDateTimeExercise)
                .map(LocalDateTime::getYear)
                .distinct()
                .toList();
        int[] progressYear = new int[integerList.size()];
        for (int i = 0; i < integerList.size(); i++) {
            progressYear[i] = integerList.get(i);
        }
        return progressYear;
    }
}
