package pl.edusnooker.webapp.component.exercise;

import org.springframework.data.repository.CrudRepository;
import java.util.List;

public interface ExerciseRepository extends CrudRepository<Exercise, Long> {

    List<Exercise> findAllByLevel(Level level);

    List<Exercise> findAll();

}
