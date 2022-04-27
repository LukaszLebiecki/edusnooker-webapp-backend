package pl.edusnooker.webapp.web;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import pl.edusnooker.webapp.component.user.User;
import pl.edusnooker.webapp.component.user.UserRepository;

import java.util.Optional;

@Service
public class SignUpService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public SignUpService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }


    public User signUpUser(User user) {
        Assert.isNull(user.getId(), "Can't sign up given user, it already has set id. User: " + user.toString());
        Assert.isTrue(!(user.getEmail().equals("")), "Email cannot be empty");
        Assert.isTrue(!(user.getPassword().equals("")), "Email cannot be empty");

        Optional<User> byEmail = userRepository.findByEmail(user.getEmail());
        Assert.isTrue(byEmail.isEmpty(), "Email is already registered");

        user.setPassword(passwordEncoder.encode(user.getPassword()));

        User savedUser = userRepository.save(user);

        return savedUser;
    }
}
