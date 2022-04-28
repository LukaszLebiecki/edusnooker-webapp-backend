package pl.edusnooker.webapp.component.progress;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Objects;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Progress progress = (Progress) o;
        return idExercise == progress.idExercise;
    }

    @Override
    public int hashCode() {
        return Objects.hash(idExercise);
    }
}
