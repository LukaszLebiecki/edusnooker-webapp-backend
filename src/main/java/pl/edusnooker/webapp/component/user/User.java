package pl.edusnooker.webapp.component.user;
import lombok.Data;
import javax.persistence.*;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Entity
@Data
@Table(name = "application_user")
class User {
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

//    private LocalDate dateRegister; //todo dodać kolumne do tabeli oraz zaimpelentować tworzenie daty przy rejestracji uzytkownika
//    private boolean isPaid = false; //todo dodać kolumne do tabeli z wartością domyślną false


}


//user:
//        - string: userName
//        - string: email
//        - string: password
//        - data: dateRegister
//        - boolen: isPaid
//        - List<Level>: levels