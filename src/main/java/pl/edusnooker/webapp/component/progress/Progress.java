package pl.edusnooker.webapp.component.progress;

import lombok.Data;
import org.springframework.security.core.userdetails.User;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalDateTime;

@Entity
@Data
public class Progress {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private User user;
    private int idExercise;
    private LocalDateTime dateTimeExercise;
    private int resultNumberOfPoint;


}
