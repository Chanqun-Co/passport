package io.sharing.passport;

import io.sharing.passport.configuration.PassportProperties;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.core.MethodParameter;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

public class PassportArgumentResolver implements HandlerMethodArgumentResolver {
    private final PassportTokenProvider passportTokenProvider;

    public PassportArgumentResolver(PassportTokenProvider passportTokenProvider) {
        this.passportTokenProvider = passportTokenProvider;
    }

    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        return findPassportMethodAnnotation(parameter) != null;
    }

    @Override
    public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer, NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {
        HttpServletRequest request = (HttpServletRequest) webRequest.getNativeRequest();
        String passportToken = request.getHeader(PassportProperties.HEADER_NAME);

        return passportTokenProvider.parseToken(passportToken, parameter.getParameterType());
    }

    private Passport findPassportMethodAnnotation(MethodParameter parameter) {
        return parameter.getParameterAnnotation(Passport.class);
    }
}
