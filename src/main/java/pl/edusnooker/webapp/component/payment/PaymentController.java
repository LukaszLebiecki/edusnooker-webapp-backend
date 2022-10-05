package pl.edusnooker.webapp.component.payment;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api")
public class PaymentController {

    @PostMapping("checkout")
    @PreAuthorize("hasAnyAuthority('user:demo')")
    ResponseEntity<String>createCheckoutSession() {
        return ResponseEntity.ok("ok - dostalem zapytanie");
    }

}
