package pl.edusnooker.webapp.constant;

public class Authority {
    public static final String[] DEMO_AUTHORITIES = { "user:demo" };
    public static final String[] BASIC_AUTHORITIES = { "user:demo", "user:basic" };
    public static final String[] PREMIUM_AUTHORITIES = { "user:demo", "user:basic", "user:premium" };
    public static final String[] ADMIN_AUTHORITIES = { "user:demo", "user:basic", "user:premium", "user:delete"  };
}
