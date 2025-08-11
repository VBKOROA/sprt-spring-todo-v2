package indiv.abko.todo.global.security;

import indiv.abko.todo.global.exception.BusinessException;
import indiv.abko.todo.global.exception.GlobalExceptionEnum;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.core.MethodParameter;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

@Component
@RequiredArgsConstructor
public class AuthUserArgumentResolver implements HandlerMethodArgumentResolver {
    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        return parameter.hasParameterAnnotation(AuthUser.class);
    }

    @Override
    public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer, NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {
        final var request = (HttpServletRequest) webRequest.getNativeRequest();
        final Object attribute = request.getAttribute(AuthClaim.MEMBER_ID.getKey());
        if (attribute == null) {
            throw new BusinessException(GlobalExceptionEnum.UNAUTHORIZED);
        }
        try {
            return Long.valueOf(attribute.toString());
        } catch (NumberFormatException e) {
            throw new BusinessException(GlobalExceptionEnum.UNAUTHORIZED);
        }
    }
}
