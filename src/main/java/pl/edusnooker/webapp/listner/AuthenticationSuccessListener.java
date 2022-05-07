package pl.edusnooker.webapp.listner;

import org.springframework.context.event.EventListener;
import org.springframework.security.authentication.event.AuthenticationSuccessEvent;
import org.springframework.stereotype.Component;
import pl.edusnooker.webapp.component.user.LoginAttemptService;
import pl.edusnooker.webapp.component.user.UserPrincipal;

@Component
public class AuthenticationSuccessListener {

    private LoginAttemptService loginAttemptService;

    public AuthenticationSuccessListener(LoginAttemptService loginAttemptService) {
        this.loginAttemptService = loginAttemptService;
    }

    @EventListener
    public void onAuthenticationSuccess(AuthenticationSuccessEvent event) {
        Object principal = event.getAuthentication().getPrincipal();
        if (principal instanceof UserPrincipal) {
            UserPrincipal user = (UserPrincipal) event.getAuthentication().getPrincipal();
            loginAttemptService.evictUserFromLoginAttemptCache(user.getUsername());
        }
    }
}
