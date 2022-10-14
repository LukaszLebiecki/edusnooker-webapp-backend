package pl.edusnooker.webapp.component.user;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import pl.edusnooker.webapp.component.user.dto.UserProgressMode;
import pl.edusnooker.webapp.exception.domain.*;
import pl.edusnooker.webapp.http.HttpResponse;
import pl.edusnooker.webapp.utility.JWTTokenProvider;

import javax.mail.MessagingException;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

import static org.springframework.http.MediaType.IMAGE_JPEG_VALUE;
import static pl.edusnooker.webapp.constant.FileConstant.*;
import static pl.edusnooker.webapp.constant.SecurityConstant.JWT_TOKEN_HEADER;

@RestController
@RequestMapping(path = {"/", "/user"})
public class UserController extends ExceptionHandling {

    public static final String EMAIL_SENT = "An email with a new password was sent to: ";
    public static final String USER_DELETE_SUCCESSFULLY = "User delete successfully";
    private UserService userService;
    private AuthenticationManager authenticationManager;
    private JWTTokenProvider jwtTokenProvider;

    public UserController(UserService userService, AuthenticationManager authenticationManager, JWTTokenProvider jwtTokenProvider) {
        this.userService = userService;
        this.authenticationManager = authenticationManager;
        this.jwtTokenProvider = jwtTokenProvider;
    }

    @PostMapping("/login")
    public ResponseEntity<User> login(@RequestBody User user) {
        authenticate(user.getUsername(), user.getPassword());
        User loginUser = userService.findUserByUsername(user.getUsername());
        UserPrincipal userPrincipal = new UserPrincipal(loginUser);
        HttpHeaders jwtHeader = getJwtHeader(userPrincipal);
        return new ResponseEntity<>(loginUser, jwtHeader, HttpStatus.OK);
    }

    @PostMapping("/register")
    public ResponseEntity<User> register(@RequestBody User user) throws EmailExistException, UsernameExistException, MessagingException {
        User newUser = userService.register(user.getFirstName(), user.getLastName(), user.getUsername(), user.getEmail());
        return new ResponseEntity<>(newUser, HttpStatus.OK);
    }

    @PostMapping("/add")
    @PreAuthorize("hasAnyAuthority('user:delete')")
    public ResponseEntity<User> addNewUser(@RequestParam("firstName") String firstName,
                                           @RequestParam("lastName") String lastName,
                                           @RequestParam("username") String username,
                                           @RequestParam("email") String email,
                                           @RequestParam("role") String role,
                                           @RequestParam("isActive") String isActive,
                                           @RequestParam("isNotLocked") String isNotLocked,
                                           @RequestParam(value = "profileImage", required = false) MultipartFile profileImage) throws EmailExistException, IOException, UsernameExistException, NotAnImageFileException {
        User newUser = userService.addNewUser(firstName, lastName, username, email, role, Boolean.parseBoolean(isActive), Boolean.parseBoolean(isNotLocked), profileImage);
        return new ResponseEntity<>(newUser, HttpStatus.OK);
    }

    @PostMapping("/update")
    @PreAuthorize("hasAnyAuthority('user:delete')")
    public ResponseEntity<User> update(@RequestParam("currentUsername") String currentUsername,
                                       @RequestParam("firstName") String firstName,
                                       @RequestParam("lastName") String lastName,
                                       @RequestParam("username") String username,
                                       @RequestParam("email") String email,
                                       @RequestParam("role") String role,
                                       @RequestParam("isActive") String isActive,
                                       @RequestParam("isNotLocked") String isNotLocked,
                                       @RequestParam(value = "profileImage", required = false) MultipartFile profileImage) throws EmailExistException, IOException, UsernameExistException, NotAnImageFileException {
        User updatedUser = userService.updateUser(currentUsername, firstName, lastName, username, email, role, Boolean.parseBoolean(isActive), Boolean.parseBoolean(isNotLocked), profileImage);
        return new ResponseEntity<>(updatedUser, HttpStatus.OK);
    }

    @PostMapping("/updateuser")
    @PreAuthorize("hasAnyAuthority('user:demo')")
    public ResponseEntity<User> updateUser(@RequestParam("currentUsername") String currentUsername,
                                       @RequestParam("firstName") String firstName,
                                       @RequestParam("lastName") String lastName,
                                       @RequestParam("username") String username,
                                       @RequestParam("email") String email,
                                       @RequestParam(value = "profileImage", required = false) MultipartFile profileImage) throws EmailExistException, IOException, UsernameExistException, NotAnImageFileException {
        User updatedUser = userService.updateUserToUser(currentUsername, firstName, lastName, username, email, profileImage);
        return new ResponseEntity<>(updatedUser, HttpStatus.OK);
    }

    @GetMapping("/get/progressmode/{userId}")
    @PreAuthorize("hasAnyAuthority('user:basic')")
    public ResponseEntity<UserProgressMode> getProgressMode(@PathVariable("userId") String userId) {
        UserProgressMode userProgressMode = userService.getUserProgressMode(userId);
        return new ResponseEntity<>(userProgressMode, HttpStatus.OK);
    }

    @PostMapping("/update/progressmode")
    @PreAuthorize("hasAnyAuthority('user:basic')")
    public ResponseEntity<User> updateProgressMode(@RequestParam("currentUserId") String currentUserId,
                                              @RequestParam("progressMode") boolean progressMode) throws EmailExistException, IOException, UsernameExistException, NotAnImageFileException {
        User updatedUser = userService.updateUserProgressMode(currentUserId, progressMode);
        return new ResponseEntity<>(updatedUser, HttpStatus.OK);
    }

    @PostMapping("/update/slotone")
    @PreAuthorize("hasAnyAuthority('user:basic')")
    public ResponseEntity<User> updateSlotOne(@RequestParam("currentUserId") String currentUserId,
                                       @RequestParam("favoriteSlot") String favoriteSlot) throws EmailExistException, IOException, UsernameExistException, NotAnImageFileException {
        User updatedUser = userService.updateUserSlotOne(currentUserId, favoriteSlot);
        return new ResponseEntity<>(updatedUser, HttpStatus.OK);
    }

    @PostMapping("/update/slottwo")
    @PreAuthorize("hasAnyAuthority('user:basic')")
    public ResponseEntity<User> updateSlotTwo(@RequestParam("currentUserId") String currentUserId,
                                       @RequestParam("favoriteSlot") String favoriteSlot) throws EmailExistException, IOException, UsernameExistException, NotAnImageFileException {
        User updatedUser = userService.updateUserSlotTwo(currentUserId, favoriteSlot);
        return new ResponseEntity<>(updatedUser, HttpStatus.OK);
    }

    @PostMapping("/update/slotthree")
    @PreAuthorize("hasAnyAuthority('user:basic')")
    public ResponseEntity<User> updateSlotThree(@RequestParam("currentUserId") String currentUserId,
                                              @RequestParam("favoriteSlot") String favoriteSlot) throws EmailExistException, IOException, UsernameExistException, NotAnImageFileException {
        User updatedUser = userService.updateUserSlotThree(currentUserId, favoriteSlot);
        return new ResponseEntity<>(updatedUser, HttpStatus.OK);
    }

    @GetMapping("/find/{username}")
    @PreAuthorize("hasAnyAuthority('user:delete')")
    public ResponseEntity<User> getUser(@PathVariable("username") String username) {
        User user = userService.findUserByUsername(username);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @GetMapping("/list")
    @PreAuthorize("hasAnyAuthority('user:delete')")
    public ResponseEntity<List<User>> getAllUsers() {
        List<User> users = userService.getUsers();
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    @GetMapping("/resetpassword/{email}")
    public ResponseEntity<HttpResponse> resetPassword(@PathVariable("email") String email) throws EmailNotFoundException, MessagingException {
        userService.resetPassword(email);
        return response(HttpStatus.OK, EMAIL_SENT + email);
    }

    @DeleteMapping("/delete/{username}")
    @PreAuthorize("hasAnyAuthority('user:delete')")
    public ResponseEntity<HttpResponse> deleteUser(@PathVariable("username") String username) throws IOException, MessagingException {
        userService.deleteUser(username);
        return response(HttpStatus.OK, USER_DELETE_SUCCESSFULLY);
    }

    @DeleteMapping("/deletemyaccount/{username}")
    @PreAuthorize("isFullyAuthenticated()")
    public ResponseEntity<HttpResponse> deleteMyAccount(@PathVariable("username") String username) throws IOException, MessagingException {
        userService.deleteUser(username);
        return response(HttpStatus.OK, USER_DELETE_SUCCESSFULLY);
    }

    @PostMapping("/updateProfileImage")
    @PreAuthorize("hasAnyAuthority('user:demo')")
    public ResponseEntity<User> updateProfileImage(@RequestParam("username") String username,
                                                   @RequestParam(value = "profileImage") MultipartFile profileImage) throws EmailExistException, IOException, UsernameExistException, NotAnImageFileException {
        User user = userService.updateProfileImage(username, profileImage);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @GetMapping(path = "/image/{username}/{fileName}", produces = IMAGE_JPEG_VALUE)
    public byte[] getProfileImage(@PathVariable("username") String username, @PathVariable("fileName") String fileName) throws IOException {
        return Files.readAllBytes(Paths.get(USER_FOLDER + username + FORWARD_SLASH + fileName));
    }

    @GetMapping(path = "/image/profile/{username}", produces = IMAGE_JPEG_VALUE)
    public byte[] getTempProfileImage(@PathVariable("username") String username) throws IOException {
        URL url = new URL(TEMP_PROFILE_IMAGE_BASE_URL + username);
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        try (InputStream inputStream = url.openStream()) {
            int bytesRead;
            byte[] chunk = new byte[1024];
            while ((bytesRead = inputStream.read(chunk)) > 0) {
                byteArrayOutputStream.write(chunk, 0, bytesRead);
            }
        }
        return byteArrayOutputStream.toByteArray();
    }

    private ResponseEntity<HttpResponse> response(HttpStatus httpStatus, String message) {
        return new ResponseEntity<>(new HttpResponse(httpStatus.value(), httpStatus,
                httpStatus.getReasonPhrase().toUpperCase(), message), httpStatus);
    }

    private HttpHeaders getJwtHeader(UserPrincipal user) {
        HttpHeaders headers = new HttpHeaders();
        headers.add(JWT_TOKEN_HEADER, jwtTokenProvider.generateJwtToken(user));
        return headers;
    }

    private void authenticate(String username, String password) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
    }
}
