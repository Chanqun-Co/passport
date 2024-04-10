package io.sharing.passportexample;

import io.sharing.passport.core.Passport;
import io.sharing.passport.core.PassportDetails;
import io.sharing.passport.core.PassportTokenProvider;
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

    /** 패스포트 토큰을 발급한다 */
    @PostMapping("/token")
    public String token(@RequestBody PassportDetails passportDetails){
        return tokenProvider.generate(passportDetails);
    }

    /** 패스포트 토큰을 파싱하여 패스포트 정보를 반환한다 */
    @GetMapping("/passport")
    public ResponseEntity<Object> passport(@Passport PassportDetails passport){
        return ResponseEntity.ok(passport);
    }
}
