package api;

import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import use_case.signup.SignupInputBoundary;
import use_case.signup.SignupInputData;
import use_case.signup.UserExistsException;

@RestController
public class Authentification {
    private SignupInputBoundary signUpInteractor;

    public Authentification(SignupInputBoundary signUpInteractor) {
        this.signUpInteractor = signUpInteractor;
    }

    @PostMapping("/signup")
    public ResponseEntity<String> createUser(@RequestBody Map<String, String> requestBody) {
        final SignupInputData signupInputData = new SignupInputData(
            requestBody.get("username"), 
            requestBody.get("password"),
            requestBody.get("confirmation"),
            requestBody.get("email"),
            requestBody.get("birth_date"),
            requestBody.get("full_name")
        );

        try {
            signUpInteractor.signupUser(signupInputData);
            return ResponseEntity.status(HttpStatus.OK).body("Signed up user successfully");
        } catch (UserExistsException ex) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("User with email exists");
        }
    } 
}
