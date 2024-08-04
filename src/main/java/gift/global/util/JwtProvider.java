package gift.global.util;

import gift.member.dto.MemberDto;
import gift.global.exception.InvalidAccessTokenException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import java.util.Date;
import javax.crypto.SecretKey;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class JwtProvider {

    private final SecretKey secretKey;
    private final long accessTokenExpirationTime;

    public JwtProvider(@Value("${jwt.secret_key}") String secretKey, @Value("${jwt.access_token_expiration_time}") long accessTokenExpirationTime) {
        this.secretKey = Keys.hmacShaKeyFor(Decoders.BASE64URL.decode(secretKey));
        this.accessTokenExpirationTime = accessTokenExpirationTime;
    }

    public String createAccessToken(MemberDto memberDto) {
        Date now = new Date();
        return Jwts.builder()
            .claim("email", memberDto.email())
            .expiration(new Date(now.getTime() + accessTokenExpirationTime))
            .signWith(secretKey)
            .compact();
    }

    public String parseAccessToken(String accessToken) {
        try {
            var payload = Jwts.parser()
                .verifyWith(secretKey)
                .build()
                .parseSignedClaims(accessToken)
                .getPayload();
            return payload.get("email").toString();
        } catch (Exception e) {
            throw new InvalidAccessTokenException();
        }
    }
}