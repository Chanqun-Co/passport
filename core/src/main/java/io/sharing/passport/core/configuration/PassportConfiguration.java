package io.sharing.passport.core.configuration;

import io.sharing.passport.core.PassportArgumentResolver;
import io.sharing.passport.core.PassportTokenProvider;
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
