package pl.edusnooker.webapp.component.newsletter;

import org.springframework.stereotype.Service;
import pl.edusnooker.webapp.component.email.EmailService;

import javax.mail.MessagingException;

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
        newsletter.setEmail(email);
        newsletterRepository.save(newsletter);
        emailService.sendEmailNewsletter(email);
        return newsletter;
    }

    void deleteNewsletter(String email) {
        Newsletter newsletter = newsletterRepository.findByEmail(email).get();
        newsletterRepository.delete(newsletter);
    }

}
