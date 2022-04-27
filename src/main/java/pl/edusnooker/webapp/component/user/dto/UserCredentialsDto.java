package pl.edusnooker.webapp.component.user.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Set;


@AllArgsConstructor
public class UserCredentialsDto {
    @Getter
    private final String name;
    @Getter
    private final String email;
    @Getter
    private final String password;
    @Getter
    private final Set<String> roles;

}
