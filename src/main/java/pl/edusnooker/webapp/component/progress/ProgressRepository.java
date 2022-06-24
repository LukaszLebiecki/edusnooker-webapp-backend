package pl.edusnooker.webapp.component.progress;

import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ProgressRepository extends CrudRepository<Progress, Long> {

    List<Progress> findAllByNumberLevelAndUserId(int numberLevel, String userId);

    List<Progress> findAllByIdExerciseAndUserId(int idExercise, String userId);

    List<Progress> findAllByIdExerciseAndUserIdOrderByDateTimeExerciseDesc(int idExercise, String userId);

    List<Progress> findAllByIdExerciseAndUserIdOrderByResultNumberOfPointDesc(int idExercise, String userId);


}
