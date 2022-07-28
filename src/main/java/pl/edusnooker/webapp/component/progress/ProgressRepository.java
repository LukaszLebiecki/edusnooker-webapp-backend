package pl.edusnooker.webapp.component.progress;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface ProgressRepository extends CrudRepository<Progress, Long> {

    List<Progress> findAllByNumberLevelAndUserId(int numberLevel, String userId);

    List<Progress> findAllByIdExerciseAndUserId(String idExercise, String userId);

    List<Progress> findAllByIdExerciseAndUserIdOrderByDateTimeExerciseDesc(String idExercise, String userId);

    List<Progress> findAllByIdExerciseAndUserIdOrderByResultNumberOfPointDesc(String idExercise, String userId);

    List<Progress> findAllByUserIdOrderByDateTimeExerciseDesc(String userId);

    @Query ("SELECT p FROM Progress p WHERE p.userId = :userId AND EXTRACT (month FROM p.dateTimeExercise) = :m AND EXTRACT (year FROM p.dateTimeExercise) = :y ")
    List<Progress> findAllProgressByUserIdAndDateTimeExerciseToYearAndMonth(String userId, int m, int y);

    @Query ("SELECT p FROM Progress p WHERE p.userId = :userId AND EXTRACT (month FROM p.dateTimeExercise) = :m AND EXTRACT (year FROM p.dateTimeExercise) = :y AND EXTRACT (day FROM p.dateTimeExercise) = :d")
    List<Progress> findAllProgressByUserIdAndDateTimeExerciseToYearAndMonthAndDay(String userId, int m, int y, int d);

    @Query ("SELECT p FROM Progress p WHERE p.userId = :userId AND EXTRACT (hour FROM p.dateTimeExercise) = :x")
    List<Progress> findAllProgressByUserIdAndDateTimeExerciseToHour(String userId, int x);

    List<Progress> findAllByUserId(String userId);

}
