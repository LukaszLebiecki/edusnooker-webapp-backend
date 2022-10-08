package pl.edusnooker.webapp.component.payment.webhook;

import lombok.Data;

@Data
public class Object {
    private String id;
    private String email;
    private String currentPeriodEnd;
    private String customer;
}
