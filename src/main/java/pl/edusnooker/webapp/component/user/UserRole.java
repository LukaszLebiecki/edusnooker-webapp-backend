package pl.edusnooker.webapp.component.user;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name = "user_role")
class UserRole {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String description;
}
