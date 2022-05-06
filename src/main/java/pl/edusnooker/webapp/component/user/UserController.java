package pl.edusnooker.webapp.component.user;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.edusnooker.webapp.exception.domain.EmailExistException;
import pl.edusnooker.webapp.exception.domain.ExceptionHandling;
import pl.edusnooker.webapp.exception.domain.UserNotFoundException;
import pl.edusnooker.webapp.exception.domain.UsernameExistException;

@RestController
@RequestMapping(path = {"/", "/user"})
public class UserController extends ExceptionHandling {

    private UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/register")
    public ResponseEntity<User> register(@RequestBody User user) throws UserNotFoundException, EmailExistException, UsernameExistException {
        User newUser = userService.register(user.getFirstName(), user.getLastName(), user.getUsername(), user.getEmail());
        return new ResponseEntity<>(newUser, HttpStatus.OK);
    }

}
