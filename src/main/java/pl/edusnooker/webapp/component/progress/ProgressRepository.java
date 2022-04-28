package pl.edusnooker.webapp.component.progress;

import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ProgressRepository extends CrudRepository<Progress, Long> {

    List<Progress> findAllByNumberLevelAndUserId(int numberLevel, int userId);

    List<Progress> findAllByIdExerciseAndUserId(int idExercise, int userId);

    List<Progress> findAllByIdExerciseAndUserIdOrderByDateTimeExerciseDesc(int idExercise, int userId);

    List<Progress> findAllByIdExerciseAndUserIdOrderByResultNumberOfPointDesc(int idExercise, int userId);


}
