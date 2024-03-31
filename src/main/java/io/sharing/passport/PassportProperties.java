package io.sharing.passport;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "passport")
public class PassportProperties {
    public static String HEADER_NAME = "X-PASSPORT-TOKEN";
}
