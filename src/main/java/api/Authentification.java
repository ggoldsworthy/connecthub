package api;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import use_case.signup.SignupInputBoundary;
import use_case.signup.SignupInputData;

@RestController
@RequestMapping("/signup")
public class Authentification {
    private SignupInputBoundary signUpInteractor;

    // public Authentification(SignupInputBoundary signUpInteractor) {
    //     this.signUpInteractor = signUpInteractor;
    // }

    @PostMapping
    public ResponseEntity<String> createUser(@RequestBody String username,
                           @RequestBody String userID,
                           @RequestBody String password1, 
                           @RequestBody String password2,
                           @RequestBody String email, 
                           @RequestBody String birthDate,
                           @RequestBody String fullName) {
        final SignupInputData signupInputData = new SignupInputData(
            username, userID, password1, password2, email, birthDate, fullName);
        
        // make signupUser return status code
        try {
            signUpInteractor.signupUser(signupInputData);
            return ResponseEntity.status(HttpStatus.OK).body("Signed up user successfully");
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("");
        }
    } 
}
