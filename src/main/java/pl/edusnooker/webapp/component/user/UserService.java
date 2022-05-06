package pl.edusnooker.webapp.component.user;

import pl.edusnooker.webapp.exception.domain.EmailExistException;
import pl.edusnooker.webapp.exception.domain.UsernameExistException;

import java.util.List;

public interface UserService {

    User register(String firstName, String lastName, String username, String email) throws EmailExistException, UsernameExistException;

    List<User> getUsers();

    User findUserByUsername(String username);

    User findUserByEmail(String email);

}
