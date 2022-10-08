package pl.edusnooker.webapp.component.payment;

import org.springframework.stereotype.Service;
import pl.edusnooker.webapp.component.user.User;
import pl.edusnooker.webapp.component.user.UserRepository;
import pl.edusnooker.webapp.enumeration.Role;

import java.util.Date;

@Service
public class PaymentService {

    private UserRepository userRepository;

    public PaymentService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User setUserStripeId(String email, String stripeId) {
        User currentUser = findUserByEmail(email);
        currentUser.setStripeId(stripeId);
        userRepository.save(currentUser);
        return currentUser;
    }

    public User setUserRole(String stripeId, int currentPeriodEnd) {
        Date nextPay = new Date(currentPeriodEnd);
        User userByStripeId = findUserByStripeId(stripeId);
        userByStripeId.setRole(getRoleEnumName("ROLE_BASIC").name());
        userByStripeId.setAuthorities(getRoleEnumName("ROLE_BASIC").getAuthorities());
        userByStripeId.setNextPay(nextPay);
        userRepository.save(userByStripeId);
        return userByStripeId;
    }

    private User findUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    private User findUserByStripeId(String stripeId) {
        return userRepository.findByStripeId(stripeId);
    }

    private Role getRoleEnumName(String role) {
        return Role.valueOf(role.toUpperCase());
    }
}
