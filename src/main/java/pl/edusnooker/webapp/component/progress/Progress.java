package pl.edusnooker.webapp.component.progress;

import lombok.Data;
import org.springframework.security.core.userdetails.User;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Data
public class Progress {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private int numberLevel;
    private int idExercise;
    private int numberOfPointsToPassed;
    private int resultNumberOfPoint;
    private LocalDateTime dateTimeExercise;
    @Column(name = "user_id")
    private int userId;


}
