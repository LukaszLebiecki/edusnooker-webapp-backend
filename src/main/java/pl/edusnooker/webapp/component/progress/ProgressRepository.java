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

    @Query ("SELECT p FROM Progress p WHERE p.userId = :userId AND EXTRACT (month FROM p.dateTimeExercise) = :x")
    List<Progress> findAllProgressByUserIdAAndDateTimeExercise(String userId, int x);

    List<Progress> findAllByUserId(String userId);

}
