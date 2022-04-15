package pl.edusnooker.webapp.component.exercise;

import lombok.Data;
import pl.edusnooker.webapp.component.user.Level;

import javax.persistence.*;

@Entity
@Data
class Exercise {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String description;
    private String gif;
    private int numberPointsToPassed;
    private Level level;
}
