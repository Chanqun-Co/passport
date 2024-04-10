package io.sharing.passport.core.configuration;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "passport")
public class PassportProperties {
    public static String HEADER_NAME = "X-PASSPORT-TOKEN";
    private Token token;

    public Token getToken() {
        return token;
    }

    public void setToken(Token token) {
        this.token = token;
    }

    public static class Token {
        private String subject;

        private String secret;

        /** millisecond */
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

        public void setSubject(String subject) {
            this.subject = subject;
        }

        public void setSecret(String secret) {
            this.secret = secret;
        }

        public void setExpirationTime(long expirationTime) {
            this.expirationTime = expirationTime;
        }
    }
}
