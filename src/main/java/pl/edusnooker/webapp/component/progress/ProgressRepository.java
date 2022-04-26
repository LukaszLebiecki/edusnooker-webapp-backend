package pl.edusnooker.webapp.component.progress;

import org.springframework.data.repository.CrudRepository;
import org.springframework.security.core.userdetails.User;

import java.util.List;

public interface ProgressRepository extends CrudRepository<Progress, Long> {

    List<Progress> findAllByNumberLevelAndUserId(int  numberLevel, int userId);
}
