package api;

import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import entity.User;
import use_case.get_user.GetUserInputBoundary;
import use_case.get_user.GetUserInputData;
import use_case.get_user.UserDoesNotExistException;

@RestController
public class UserController {
    private final GetUserInputBoundary getUserInteractor;

    public UserController(GetUserInputBoundary getUserInteractor) {
        this.getUserInteractor = getUserInteractor;
    }

    @GetMapping("/current-user")
    public ResponseEntity<User> getCurrentUser() {
        return ResponseEntity.status(HttpStatus.OK).body(this.getUserInteractor.getCurrentUser());
    }

    @GetMapping("/user")
    public ResponseEntity<User> getUserInfo(@RequestBody Map<String, String> requestBody) {
        return getUserInformation((String) requestBody.get("user_id"));
    }

    private ResponseEntity<User> getUserInformation(String userID) {
        try {
            GetUserInputData getUserInputData = new GetUserInputData(userID);
            User user = this.getUserInteractor.getUserByID(getUserInputData);
            return ResponseEntity.status(HttpStatus.OK).body(user);
        } catch (UserDoesNotExistException ex) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }
}
