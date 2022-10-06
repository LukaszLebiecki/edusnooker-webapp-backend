package pl.edusnooker.webapp.component.payment;

import lombok.Data;

@Data
public class Payment {
    private String priceId;
    private String successUrl;
    private String cancelUrl;
}
