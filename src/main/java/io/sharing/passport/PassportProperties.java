package io.sharing.passport;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "passport")
public class PassportProperties {
    public static String HEADER_NAME = "X-PASSPORT-TOKEN";

    private Token token;

    public Token getToken() {
        return token;
    }

    public static class Token {
        private String subject;

        private String secret;

        private long expirationTime;

        public String getSubject() {
            return subject;
        }

        public String getSecret() {
            return secret;
        }

        public long getExpirationTime() {
            return expirationTime;
        }
    }
}
