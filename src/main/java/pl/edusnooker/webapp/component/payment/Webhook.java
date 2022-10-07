package pl.edusnooker.webapp.component.payment;

import lombok.Data;

@Data
public class Webhook {
    private String currentPeriodEnd;
    private String customer_email;

}
