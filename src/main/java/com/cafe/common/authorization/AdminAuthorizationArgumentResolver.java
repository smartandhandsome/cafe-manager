package com.cafe.common.authorization;

import com.cafe.admin.service.impl.AuthTokenExtractor;
import com.cafe.common.exception.ErrorCode;
import com.cafe.common.exception.ForbiddenException;
import com.cafe.common.model.AdminAuthorization;
import lombok.RequiredArgsConstructor;
import org.springframework.core.MethodParameter;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import java.text.MessageFormat;

import static org.springframework.http.HttpHeaders.AUTHORIZATION;

@Component
@RequiredArgsConstructor
public class AdminAuthorizationArgumentResolver implements HandlerMethodArgumentResolver {   // TODO: 2/20/24 인증 어노테이션 기반으로 변경

    public static final String JWT_TYPE_PREFIX = "Bearer ";
    private final AuthTokenExtractor authTokenExtractor;

    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        return parameter.getParameterType().equals(AdminAuthorization.class);
    }

    @Override
    public AdminAuthorization resolveArgument(
            MethodParameter parameter,
            ModelAndViewContainer mavContainer,
            NativeWebRequest webRequest,
            WebDataBinderFactory binderFactory) {
        String authorizationHeader = webRequest.getHeader(AUTHORIZATION);

        if (authorizationHeader == null || !authorizationHeader.startsWith(JWT_TYPE_PREFIX)) {
            throw new ForbiddenException(
                    ErrorCode.FORBIDDEN,
                    MessageFormat.format("인가되지 않은 사용자 접근. [header: {0}]", authorizationHeader)
            );
        }
        String jwt = authorizationHeader.substring(JWT_TYPE_PREFIX.length());
        Long memberId = authTokenExtractor.extractAdminId(jwt);
        return new AdminAuthorization(memberId);
    }

}
