package pl.edusnooker.webapp.component.exercise;

import org.springframework.data.repository.CrudRepository;
import pl.edusnooker.webapp.enumeration.Level;

import java.util.List;
import java.util.Optional;

public interface ExerciseRepository extends CrudRepository<Exercise, Long> {

    List<Exercise> findAllByLevel(Level level);

    List<Exercise> findAll();

    Optional<Exercise> findById(Long id);

}
