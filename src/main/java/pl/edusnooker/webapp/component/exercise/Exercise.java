package pl.edusnooker.webapp.component.exercise;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
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
