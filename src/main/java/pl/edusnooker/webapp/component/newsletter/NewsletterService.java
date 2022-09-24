package pl.edusnooker.webapp.component.newsletter;

import org.springframework.stereotype.Service;
import pl.edusnooker.webapp.component.email.EmailService;

import javax.mail.MessagingException;
import java.util.Optional;

@Service
public class NewsletterService {

    private final NewsletterRepository newsletterRepository;

    private final EmailService emailService;

    public NewsletterService(NewsletterRepository newsletterRepository, EmailService emailService) {
        this.newsletterRepository = newsletterRepository;
        this.emailService = emailService;
    }

    Newsletter addNewNewsletter(String email) throws MessagingException {
        Newsletter newsletter = new Newsletter();
        Optional<Newsletter> byEmail = newsletterRepository.findByEmail(email);
        if (byEmail.isPresent()) {
            newsletter.setEmail(email);
            newsletterRepository.save(newsletter);
            emailService.sendEmailNewsletter(email);
            return newsletter;
        }
        return new Newsletter();
    }

    void deleteNewsletter(String email) {
        Newsletter newsletter = newsletterRepository.findByEmail(email).get();
        newsletterRepository.delete(newsletter);
    }

}
