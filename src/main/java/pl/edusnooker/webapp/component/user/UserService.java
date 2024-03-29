package pl.edusnooker.webapp.component.user;

import org.springframework.web.multipart.MultipartFile;
import pl.edusnooker.webapp.component.user.dto.UserProgressMode;
import pl.edusnooker.webapp.exception.domain.EmailExistException;
import pl.edusnooker.webapp.exception.domain.EmailNotFoundException;
import pl.edusnooker.webapp.exception.domain.NotAnImageFileException;
import pl.edusnooker.webapp.exception.domain.UsernameExistException;

import javax.mail.MessagingException;
import java.io.IOException;
import java.util.List;

public interface UserService {

    User register(String firstName, String lastName, String username, String email) throws EmailExistException, UsernameExistException, MessagingException;

    List<User> getUsers();

    User findUserByUsername(String username);

    User findUserByEmail(String email);

    User addNewUser(String firstName, String lastName, String username, String email, String role, boolean isNonLocked, boolean isActive, MultipartFile profileImage) throws EmailExistException, UsernameExistException, IOException, NotAnImageFileException, MessagingException;

    User updateUser(String currentUsername, String newFirstName, String newLastName, String newUsername, String newEmail, String role, boolean isNonLocked, boolean isActive, MultipartFile profileImage) throws EmailExistException, UsernameExistException, IOException, NotAnImageFileException;

    User updateUserToUser(String currentUsername, String newFirstName, String newLastName, String newUsername, String newEmail, MultipartFile profileImage) throws EmailExistException, UsernameExistException, IOException, NotAnImageFileException;

    User updateUserSlotOne(String currentUserId, String favoriteSlotOne) throws EmailExistException, UsernameExistException, IOException, NotAnImageFileException;

    User updateUserSlotTwo(String currentUserId, String favoriteSlotTwo) throws EmailExistException, UsernameExistException, IOException, NotAnImageFileException;

    User updateUserSlotThree(String currentUserId, String favoriteSlotThree) throws EmailExistException, UsernameExistException, IOException, NotAnImageFileException;

    void deleteUser(String username) throws IOException, MessagingException;

    void resetPassword(String email) throws MessagingException, EmailNotFoundException;

    User updateProfileImage(String username, MultipartFile profileImage) throws EmailExistException, UsernameExistException, IOException, NotAnImageFileException;

    UserProgressMode getUserProgressMode(String currentUserId);

    User updateUserProgressMode(String currentUserId, boolean progressMode);
}
