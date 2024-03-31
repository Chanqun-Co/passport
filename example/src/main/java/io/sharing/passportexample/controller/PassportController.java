package io.sharing.passportexample.controller;

import io.sharing.passport.Passport;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PassportController {
    @GetMapping("/passport")
    public ResponseEntity<Object> getPassport(@Passport String passport){
        System.out.println(passport);
        return ResponseEntity.ok("test");
    }
}
