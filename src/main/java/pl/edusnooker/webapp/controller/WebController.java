package pl.edusnooker.webapp.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.edusnooker.webapp.component.user.User;
import pl.edusnooker.webapp.component.user.dto.UserCredentialsDto;
import pl.edusnooker.webapp.web.SignUpService;


@RestController
@RequestMapping("api")
class WebController {

    private final SignUpService signUpService;

    public WebController(SignUpService signUpService) {
        this.signUpService = signUpService;
    }

    @PostMapping("/login")
    public void login(@RequestBody UserCredentialsDto credentials) {


    }

    @PostMapping("sign_up")
    public void signUp(@RequestBody UserCredentialsDto credentials) {
        User user = new User();
        user.setName(credentials.getName());
        user.setEmail(credentials.getEmail());
        user.setPassword(credentials.getPassword());
        signUpService.signUpUser(user);
    }


}
