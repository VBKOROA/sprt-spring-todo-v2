package indiv.abko.todo.global.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.Claim;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.util.Optional;

@Component
public class JwtUtil {
    @Value("${jwt.secret.key}")
    private String secretKey; // 비밀키

    @Value("${jwt.secret.expiration.time}")
    private long expirationTime; // 만료 시간 (밀리초)

    private Algorithm algorithm; // 서명 알고리즘

    @PostConstruct
    public void init() {
        algorithm = Algorithm.HMAC256(secretKey); // 비밀키를 이용하여 서명 알고리즘 제작
    }

    public String createToken(long memberId, String memberName) {
        var issuedAt = Instant.now();
        return JWT.create()
                .withIssuedAt(issuedAt)
                .withSubject(memberName)
                .withClaim(AuthClaim.MEMBER_ID.getKey(), memberId)
                .withExpiresAt(issuedAt.plusMillis(expirationTime))
                .sign(algorithm);
    }

    public boolean validate(String token) {
        try {
            JWT.require(algorithm).build().verify(token);
            return true;
        } catch (JWTVerificationException e) {
            return false;
        }
    }

    public String getSubject(String token) {
        return JWT.require(algorithm).build().verify(token).getSubject();
    }

    public Optional<Claim> getClaim(String token, String claimKey) {
        return Optional.ofNullable(JWT.require(algorithm).build().verify(token).getClaim(claimKey));
    }
}
