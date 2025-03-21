package com.example.intia_assurance_test.config.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.io.Serializable;
import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Component
public class JwtTokenUtil implements Serializable {

    private static final long serialVersionUID = -4185403130426972988L;

    static final String CLAM_KEY_USERNAME = "sub";

    static final String CLAM_KEY_AUDIENCE = "audience";

    static final String CLAM_KEY_CREATED = "created";

    @Value("${jwt.secret}")
    private String secret;

    @Value("${jwt.expiration}")
    private Long expiration;

    public String getUsernameFromToken(String token) {
        String username = null;
        try{
            final Claims claims = getClaimsFromToken(token);
            username = claims.getSubject();
        }
        catch (Exception e){
            username = null;
        }
        return username;
    }

    private Claims getClaimsFromToken(String token) {
        Claims claims = null;

        SecretKey secretKey = Keys.hmacShaKeyFor(secret.getBytes(StandardCharsets.UTF_8));

        try{
            claims = Jwts.parser().verifyWith(secretKey).build()
                    .parseSignedClaims(token).getPayload();
        }
        catch (Exception e){
            claims = null;
        }
        return claims;
    }

    public boolean validateToken(String token, UserDetails userDetails) {
        JwtUser user = (JwtUser) userDetails;
        final String username = getUsernameFromToken(token);
        return (username.equals(user.getUsername()) && !isTokenExpired(token));
    }

    private boolean isTokenExpired(String token) {
        final Date expiration = getExpirationDateFromToken(token);
        return expiration.before(new Date());
    }

    private Date getExpirationDateFromToken(String token) {
        Date expiration = null;
        try {
            final Claims claims = getClaimsFromToken(token);
            if (claims != null) {
                expiration = claims.getExpiration();
            } else {
                expiration = null;
            }
        } catch (Exception e) {
            expiration = null;
        }
        return expiration;
    }

    public String generateToken(JwtUser userDetails) {
        Map<String, Object> clamis = new HashMap<String, Object>();
        clamis.put(CLAM_KEY_USERNAME, userDetails.getUsername());
        clamis.put(CLAM_KEY_CREATED, new Date());
        return generatetoken(clamis);
    }

    private String generatetoken(Map<String, Object> clamis) {
        return Jwts.builder().setClaims(clamis).setExpiration(generateExpirationDate()).signWith(SignatureAlgorithm.HS512, secret).compact();
    }

    private Date generateExpirationDate() {
        return new Date(System.currentTimeMillis() + expiration * 1000);
    }
}
