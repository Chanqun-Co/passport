package io.sharing.passport.configuration;

import io.sharing.passport.PassportArgumentResolver;
import io.sharing.passport.PassportTokenProvider;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;

@EnableConfigurationProperties(PassportProperties.class)
@AutoConfiguration
public class PassportConfiguration implements WebMvcConfigurer {
    private final PassportTokenProvider passportTokenProvider;

    public PassportConfiguration(PassportTokenProvider passportTokenProvider) {
        this.passportTokenProvider = passportTokenProvider;
    }

    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> resolvers) {
        resolvers.add(new PassportArgumentResolver(passportTokenProvider));
    }
}
