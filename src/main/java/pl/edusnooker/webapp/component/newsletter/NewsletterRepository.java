package pl.edusnooker.webapp.component.newsletter;

import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface NewsletterRepository extends CrudRepository<Newsletter, Long> {

    Optional<Newsletter> findByEmail(String email);

}
