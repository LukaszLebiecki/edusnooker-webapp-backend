package pl.edusnooker.webapp.component.user;

import pl.edusnooker.webapp.component.user.dto.UserCredentialsDto;

import java.util.Set;
import java.util.stream.Collectors;

class UserCredentialsDtoMapper {
    static UserCredentialsDto map(User user) {
        String name = user.getUsername();
        String email = user.getEmail();
        String password = user.getPassword();

        return new UserCredentialsDto(name, email, password);
    }
}
