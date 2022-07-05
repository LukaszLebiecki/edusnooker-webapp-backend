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
    private String idExercise;
    private int numberLevel;
    private int numberOfPointsToPassed;
    private int resultNumberOfPoint;
    private LocalDateTime dateTimeExercise;
    @Column(name = "user_id")
    private String userId;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Progress progress = (Progress) o;
        return Objects.equals(idExercise, progress.idExercise);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idExercise);
    }
}
