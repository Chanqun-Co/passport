package io.sharing.passport.token;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import io.sharing.passport.PassportProperties;

import javax.crypto.SecretKey;
import java.util.Date;

public class TokenProvider {
    private final SecretKey secretKey;
    private final PassportProperties.Token tokenProperties;

    public TokenProvider(PassportProperties passportProperties) {
        byte[] keyBytes = Decoders.BASE64.decode(passportProperties.getToken().getSecret());
        this.secretKey = Keys.hmacShaKeyFor(keyBytes);
        this.tokenProperties = passportProperties.getToken();
    }

    public String generate(Object passport) {
        long expirationIn = new Date().getTime() + this.tokenProperties.getExpirationTime();
        Date expirationTime = new Date(expirationIn);

        return Jwts.builder()
                .subject(this.tokenProperties.getSubject())
                .claim("passport", passport)
                .expiration(expirationTime)
                .signWith(this.secretKey)
                .compact();
    }

    public Object parseToken(String token) {
        return Jwts.parser()
                .decryptWith(this.secretKey).build()
                .parse(token)
                .getPayload();
    }
}
