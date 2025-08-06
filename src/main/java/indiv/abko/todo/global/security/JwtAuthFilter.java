package indiv.abko.todo.global.security;

import com.auth0.jwt.interfaces.Claim;
import jakarta.servlet.*;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;

import java.io.IOException;
import java.util.Optional;

@RequiredArgsConstructor
public class JwtAuthFilter implements Filter {
    public static final String AUTH_HEADER = "Authorization";
    private final JwtUtil jwtUtil;

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        var tokenOpt = resolveTokenFromCookie(httpServletRequest);
        tokenOpt.filter(jwtUtil::validate)
                .map(token -> jwtUtil.getClaim(token, AuthClaim.MEMBER_ID))
                .filter(claim -> claim.isNull() == false)
                .ifPresent(memberIdClaim -> {
                    request.setAttribute(AuthClaim.MEMBER_ID.getKey(), memberIdClaim.asLong());
                });
        chain.doFilter(request, response);
    }

    private Optional<String> resolveTokenFromCookie(HttpServletRequest request) {
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (AUTH_HEADER.equals(cookie.getName())) {
                    return Optional.of(cookie.getValue());
                }
            }
        }
        return Optional.empty();
    }
}
