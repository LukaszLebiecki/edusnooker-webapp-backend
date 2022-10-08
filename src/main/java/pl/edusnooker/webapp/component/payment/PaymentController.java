package pl.edusnooker.webapp.component.payment;

import com.google.gson.Gson;
import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import com.stripe.model.checkout.Session;
import com.stripe.param.checkout.SessionCreateParams;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.edusnooker.webapp.component.payment.webhook.StripeCreateSubscription;
import pl.edusnooker.webapp.component.payment.webhook.StripeCreateUser;


import java.awt.*;
import java.util.HashMap;
import java.util.Map;

import static pl.edusnooker.webapp.constant.StripeConstant.SECRET_KEY;

@RestController
@RequestMapping("api")
public class PaymentController {

    String secretKey = SECRET_KEY;
    private static Gson gson = new Gson();
    Robot robot;
    private PaymentService paymentService;

    public PaymentController(PaymentService paymentService) {
        this.paymentService = paymentService;
    }

    @PostMapping("checkout")
    @PreAuthorize("hasAnyAuthority('user:demo')")
    public String subscriptionWithCheckoutPage(@RequestBody Payment checkout) throws StripeException {
        init(secretKey);
        SessionCreateParams params = new SessionCreateParams.Builder()
                .setSuccessUrl(checkout.getSuccessUrl())
                .setCancelUrl(checkout.getCancelUrl())
                .setCustomerEmail(checkout.getEmail())
                .addPaymentMethodType(SessionCreateParams.PaymentMethodType.CARD)
                .setMode(SessionCreateParams.Mode.SUBSCRIPTION)
                .addLineItem(new SessionCreateParams.LineItem.Builder()
                        .setQuantity(1L)
                        .setPrice(checkout.getPriceId())
                        .build())
                .build();

        try {
            Session session = Session.create(params);
            Map<String, Object> responseData = new HashMap<>();
            responseData.put("sessionId", session.getId());
            return gson.toJson(responseData);
        } catch (Exception e) {
            Map<String, Object> messageData = new HashMap<>();
            messageData.put("message", e.getMessage());
            Map<String, Object> responseData = new HashMap<>();
            responseData.put("error", messageData);
            return gson.toJson(responseData);
        }
    }

    @PostMapping("webhook/userCreate")
    public ResponseEntity<String> userCreateToStripe(@RequestBody StripeCreateUser stripeCreateUser) {
        paymentService.setUserStripeId(stripeCreateUser.getData().getObject().getEmail(), stripeCreateUser.getData().getObject().getId());
        return new ResponseEntity<>(gson.toJson(stripeCreateUser), HttpStatus.OK);
    }

    @PostMapping("webhook/subscriptionCreate")
    public ResponseEntity<String> subscriptionCreate(@RequestBody StripeCreateSubscription stripeCreateSubscription) throws InterruptedException {
        Thread.sleep(5000);
        paymentService.setUserRole(stripeCreateSubscription.getData().getObject().getCustomer(), stripeCreateSubscription.getData().getObject().getCurrentPeriodEnd());
        return new ResponseEntity<>(gson.toJson(stripeCreateSubscription), HttpStatus.OK);
    }



    private static void init(String secretKey) {
        Stripe.apiKey = secretKey;
    }


}
