package pl.edusnooker.webapp.component.user;

import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface UserRepository extends CrudRepository<User, Long> {
    User findByUsername(String username);
    User findByEmail(String email);
    List<User> findAll();
    User findAllByUserId(String userId);
    User findByStripeId(String stripeId);

}
