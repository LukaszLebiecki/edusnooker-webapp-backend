package pl.edusnooker.webapp.component.user;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalTime;

@Entity
@Data
class Statistics {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nameExercise;
    private int points;
    private boolean isPassed;
    private LocalDate dateOfExercise;
    private LocalTime timeStartOfExercise;
    private LocalTime timeEndOfExercise;
    private int exerciseCounter;


}
