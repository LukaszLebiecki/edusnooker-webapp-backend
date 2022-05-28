package pl.edusnooker.webapp.enumeration;

import static pl.edusnooker.webapp.constant.Authority.*;

public enum Role {
    ROLE_DEMO(DEMO_AUTHORITIES),
    ROLE_BASIC(BASIC_AUTHORITIES),
    ROLE_PREMIUM(PREMIUM_AUTHORITIES),
    ROLE_ADMIN(ADMIN_AUTHORITIES);

   private String[] authorities;

    Role(String... authorities) {
        this.authorities = authorities;
    }

    public String[] getAuthorities() {
        return authorities;
    }
}
