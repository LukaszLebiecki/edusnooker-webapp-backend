package pl.edusnooker.webapp.component.user;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Data
class User {
    @Id
    private Long id;
    private String name;
    private String email;
    private String password;
    private LocalDateTime registerDate;
    private Level level;
    @OneToMany
    @JoinColumn(name = "user_id")
    private List<Statistics> statistics;


}
