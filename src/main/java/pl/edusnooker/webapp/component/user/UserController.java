package pl.edusnooker.webapp.component.user;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
class UserController {

    @GetMapping("/home")
    public String showUser() {
        return "application works";
    }

}
