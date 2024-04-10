package io.sharing.passport.core;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import io.sharing.passport.core.configuration.PassportProperties;
import io.sharing.passport.core.exception.PassportExpiredException;
import io.sharing.passport.core.exception.UnAuthenticatedPassportException;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.util.Date;

@Component
public class PassportTokenProvider {
    private final SecretKey secretKey;
    private final PassportProperties.Token tokenProperties;
    private final String claimKey = "passport";


    public PassportTokenProvider(PassportProperties passportProperties) {
        byte[] keyBytes =  Decoders.BASE64.decode(passportProperties.getToken().getSecret());
        this.secretKey = Keys.hmacShaKeyFor(keyBytes);
        this.tokenProperties = passportProperties.getToken();
    }

    public String generate(Object passport) {
        long expirationIn = new Date().getTime() + this.tokenProperties.getExpirationTime();
        Date expirationTime = new Date(expirationIn);

        return Jwts.builder()
                .subject(this.tokenProperties.getSubject())
                .claim(this.claimKey, passport)
                .expiration(expirationTime)
                .signWith(this.secretKey, Jwts.SIG.HS512)
                .compact();
    }

    public <T> Object parseToken(String token, Class<T> clazz) {
        Object payload;

        try {
            payload = Jwts.parser()
                    .verifyWith(this.secretKey).build()
                    .parseSignedClaims(token)
                    .getPayload().get(this.claimKey, Object.class);
        } catch (ExpiredJwtException e) {
            throw new PassportExpiredException(e.getMessage(), e.getCause());
        }

        if (payload == null) {
            throw new UnAuthenticatedPassportException();
        }

        try {
            return new ObjectMapper().convertValue(payload, clazz);
        } catch (IllegalArgumentException e) {
            throw new ClassCastException("Passport token is not assignable to " + clazz);
        }
    }
}
