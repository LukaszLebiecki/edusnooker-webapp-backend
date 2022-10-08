package pl.edusnooker.webapp.component.payment.webhook;

@lombok.Data
public class StripeCreateSubscription {
    private String id;
    private Data data;
}
