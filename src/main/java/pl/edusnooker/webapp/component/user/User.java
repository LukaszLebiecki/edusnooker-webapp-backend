package pl.edusnooker.webapp.component.user;
import lombok.Data;
import pl.edusnooker.webapp.component.progress.Progress;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Data
@Table(name = "application_user")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String email;
    private String password;
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "user_roles",
            joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "role_id", referencedColumnName = "id")
    )
    private Set<UserRole> roles = new HashSet<>();

    @OneToMany(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    private List<Progress> progressList = new ArrayList<>();

//    private LocalDate dateRegister; //todo dodać kolumne do tabeli oraz zaimpelentować tworzenie daty przy rejestracji uzytkownika
//    private boolean isPaid = false; //todo dodać kolumne do tabeli z wartością domyślną false

}