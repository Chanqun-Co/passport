package io.sharing.passport.core;

import io.sharing.passport.core.configuration.PassportProperties;
import io.sharing.passport.core.exception.PassportExpiredException;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.UUID;

import static org.assertj.core.api.Assertions.*;

class PassportTokenProviderTest {
    String secret = "awesklfjdjklejai2t4weagho03rqlwAEFklh3ioljkwaesjdfhap3wojt4fawnekl3sdfkkslegkrmwoej3io";
    PassportProperties passportProperties = new PassportProperties();
    PassportTokenProvider tokenProvider;

    @BeforeEach
    public void init() {
        PassportProperties.Token tokenProperties = new PassportProperties.Token();
        tokenProperties.setExpirationTime(60 * 1000);
        tokenProperties.setSecret(secret);
        tokenProperties.setSubject("passport");
        passportProperties.setToken(tokenProperties);

        tokenProvider = new PassportTokenProvider(passportProperties);
    }

    @DisplayName("토큰에 담긴 claim은 암호화 전과 암호화 후 파싱한 데이터가 같다.")
    @Test
    public void validTokenTest() {
        PassportDetails details = PassportDetails.builder()
                .userUuid(UUID.randomUUID().toString())
                .email("kimzerovirus@email.com")
                .lastName("kim")
                .firstName("zerovirus")
                .roles(List.of(UserRole.GUEST))
                .build();

        String token = tokenProvider.generate(details);

        Object parsedToken = tokenProvider.parseToken(token, details.getClass());
        assertThat(parsedToken.getClass()).isEqualTo(details.getClass());

        PassportDetails parsedDetails = (PassportDetails) parsedToken;
        assertThat(parsedDetails.getUserUuid()).isEqualTo(details.getUserUuid());
        assertThat(parsedDetails.getEmail()).isEqualTo(details.getEmail());
        assertThat(parsedDetails.getLastName()).isEqualTo(details.getLastName());
        assertThat(parsedDetails.getFirstName()).isEqualTo(details.getFirstName());
        assertThat(parsedDetails.getRoles()).isEqualTo(details.getRoles());
    }

    @DisplayName("토큰을 할당할 수 없는 자료형으로 파싱한 경우 ClassCastException이 발생한다.")
    @Test
    public void parsingTokenUnAssignableClass() {
        PassportDetails details = PassportDetails.builder().build();
        String token = tokenProvider.generate(details);

        assertThatThrownBy(() -> tokenProvider.parseToken(token, String.class))
                .isInstanceOf(ClassCastException.class);
    }

    @DisplayName("유효시간이 만료된 토큰은 PassportExpiredException이 발생한다.")
    @Test
    public void expiredTokenTest() {
        String expiredPassportToken = "eyJhbGciOiJIUzUxMiJ9.eyJwYXNzcG9ydCI6eyJ1c2VyVXVpZCI6Ijg1YjczZGJkLTQ0ZGEtNGQyMi04ZTYyLTUxOGY2N2ZhYWMzOCIsImVtYWlsIjoia2ltemVyb3ZpcnVzQGVtYWlsLmNvbSIsImZpcnN0TmFtZSI6Inplcm92aXJ1cyIsImxhc3ROYW1lIjoia2ltIn0sImV4cCI6MTcxMjM4NjMyMX0.MSE-h7MtoLuryQ0cb31vu-C2gBg3Fmg_yydZb5nwFCATSXR9Y1jkVnezbBR2sfN2aRwrYQFr6zF4Mad9y5cdPA";

        assertThatThrownBy(() -> tokenProvider.parseToken(expiredPassportToken, PassportDetails.class))
                .isInstanceOf(PassportExpiredException.class);
    }
}
