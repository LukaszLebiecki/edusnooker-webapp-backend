package pl.edusnooker.webapp.component.exercise;

import lombok.Data;
import pl.edusnooker.webapp.enumeration.Level;

import javax.persistence.*;

@Entity
@Data
public
class Exercise {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String shortDescription;
    private String description;
    private String gif;
    private String img;
    private int numberOfPointsToPassed;
    private Level level;
}
