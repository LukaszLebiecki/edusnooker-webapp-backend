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
    private String description;
    private String gif;
    private int numberPointsToPassed;
}
