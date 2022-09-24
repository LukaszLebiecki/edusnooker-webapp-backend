package pl.edusnooker.webapp.component.newsletter;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.edusnooker.webapp.http.HttpResponse;

@RestController
@RequestMapping("api")
class NewsletterController {

    public static final String YOUR_EMAIL_DELETE_SUCCESSFULLY = "YOUR_EMAIL_DELETE_SUCCESSFULLY";
    private final NewsletterService newsletterService;

    NewsletterController(NewsletterService newsletterService) {
        this.newsletterService = newsletterService;
    }


    @PostMapping("/newsletter/add")
    ResponseEntity<Newsletter> addNewNewsletter(@RequestParam("email") String email) {
        Newsletter newsletter = newsletterService.addNewNewsletter(email);
        return new ResponseEntity<>(newsletter, HttpStatus.OK);
    }

    @DeleteMapping("/newsletter/delete/{email}")
    public ResponseEntity<HttpResponse> deleteUser(@PathVariable("email") String email) {
        newsletterService.deleteNewsletter(email);
        return response(HttpStatus.OK, YOUR_EMAIL_DELETE_SUCCESSFULLY);
    }

    private ResponseEntity<HttpResponse> response(HttpStatus httpStatus, String message) {
        return new ResponseEntity<>(new HttpResponse(httpStatus.value(), httpStatus,
                httpStatus.getReasonPhrase().toUpperCase(), message), httpStatus);
    }

}
