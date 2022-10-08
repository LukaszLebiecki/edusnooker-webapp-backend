package pl.edusnooker.webapp.component.payment.webhook;

import lombok.Data;

@Data
public class Object {
    private String id;
    private String email;
    private int currentPeriodEnd;
    private String customer;
}
