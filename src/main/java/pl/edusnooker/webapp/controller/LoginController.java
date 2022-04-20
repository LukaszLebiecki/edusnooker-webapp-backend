package pl.edusnooker.webapp.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import pl.edusnooker.webapp.component.user.dto.UserCredentialsDto;


@RestController
class LoginController {

    @PostMapping("/login")
    public void login(@RequestBody UserCredentialsDto credentials) {

    }
}
