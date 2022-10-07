package pl.edusnooker.webapp.component.payment;

import org.springframework.stereotype.Service;
import pl.edusnooker.webapp.component.user.User;
import pl.edusnooker.webapp.component.user.UserRepository;

@Service
public class PaymentService {

    private UserRepository userRepository;

    public PaymentService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User setUserStripeId(String email, String stripeId) {
        User currentUser = findUserByEmail(email);
        currentUser.setStripeId(stripeId);
        return currentUser;
    }

    private User findUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }
}
