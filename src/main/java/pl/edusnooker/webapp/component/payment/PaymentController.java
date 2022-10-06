package pl.edusnooker.webapp.component.payment;

import com.google.gson.Gson;
import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import com.stripe.model.checkout.Session;
import com.stripe.param.checkout.SessionCreateParams;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("api")
public class PaymentController {

    // todo Ukryć przy prod!
    String secretKey = "sk_test_51LpHi7LJcGDwiGcWjUyw4MnjWaOAsJoYmQFw5fj55BgUk503vjf1pWhxYzXF6VeLCApewoJrlWYMT5oR2ORt2R3G00ZbanFYpx";

    private static Gson gson = new Gson();

    @PostMapping("checkout")
    @PreAuthorize("hasAnyAuthority('user:demo')")
    public String subscriptionWithCheckoutPage(@RequestBody Payment checkout) throws StripeException {
        init(secretKey);
        SessionCreateParams params = new SessionCreateParams.Builder()
                .setSuccessUrl(checkout.getSuccessUrl())
                .setCancelUrl(checkout.getCancelUrl())
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

    private static void init(String secretKey) {
        Stripe.apiKey = secretKey;
    }
}
