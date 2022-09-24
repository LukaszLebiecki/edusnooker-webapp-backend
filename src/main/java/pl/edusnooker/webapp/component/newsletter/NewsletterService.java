package pl.edusnooker.webapp.component.newsletter;

import org.springframework.stereotype.Service;

@Service
public class NewsletterService {

    private final NewsletterRepository newsletterRepository;

    public NewsletterService(NewsletterRepository newsletterRepository) {
        this.newsletterRepository = newsletterRepository;
    }

    Newsletter addNewNewsletter(String email) {
        Newsletter newsletter = new Newsletter();
        newsletter.setEmail(email);
        newsletterRepository.save(newsletter);
        return newsletter;
    }

    void deleteNewsletter(String email) {
        Newsletter newsletter = newsletterRepository.findByEmail(email).get();
        newsletterRepository.delete(newsletter);
    }

}
