package io.sharing.passportexample.controller;

import io.sharing.passport.Passport;
import io.sharing.passport.PassportDetails;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PassportController {
    @PostMapping("/passport")
    public ResponseEntity<PassportDetails> sendPassport(){
        return ResponseEntity.ok(PassportDetails.builder().build());
    }

    @GetMapping("/passport")
    public ResponseEntity<PassportDetails> getPassport(@Passport PassportDetails passport){
        return ResponseEntity.ok(passport);
    }
}
