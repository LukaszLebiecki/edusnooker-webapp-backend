package pl.edusnooker.webapp.constant;

public class Authority {
    public static final String[] DEMO_AUTHORITIES = { "user:read" };
    public static final String[] BASIC_AUTHORITIES = { "user:read", "user:update" };
    public static final String[] PREMIUM_AUTHORITIES = { "user:read", "user:update" };
    public static final String[] ADMIN_AUTHORITIES = { "user:read","user:create", "user:update", "user:delete"  };
}
