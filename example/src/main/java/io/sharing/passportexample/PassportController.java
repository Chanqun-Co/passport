package io.sharing.passportexample;

import io.sharing.passport.Passport;
import io.sharing.passport.PassportDetails;
import io.sharing.passport.PassportTokenProvider;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PassportController {
    private final PassportTokenProvider tokenProvider;

    public PassportController(PassportTokenProvider tokenProvider) {
        this.tokenProvider = tokenProvider;
    }

    @PostMapping("/token")
    public String token(@RequestBody PassportDetails passportDetails){
        return tokenProvider.generate(passportDetails);
    }

    @GetMapping("/passport")
    public ResponseEntity<Object> passport(@Passport PassportDetails passport){
        return ResponseEntity.ok(passport);
    }
}
