package com.ecommerce.project.securityServices;

import com.ecommerce.project.model.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Value;
import io.jsonwebtoken.io.Decoders;
import java.util.Date;
import java.util.function.Function;
import java.util.HashMap;
import java.security.Key;
import java.util.Map;
import java.util.Objects;

@Service
public class JwtService {
    @Value("${security.jwt.secret-key}")
    private String secretKey;

    @Value("${security.jwt.expiration-time}")
    private long jwtExpiration;

    public String generateToken (UserDetails userDetails){
        Map<String, Object> extraClaims = new HashMap<>();
        if(userDetails instanceof User){
            User user = (User) userDetails;
            extraClaims.put("role",user.getUser_role());
        }
        return generateToken(extraClaims, userDetails);
    }
    public String extractRole(String token) {
        return extractClaim(token, claims -> claims.get("role", String.class));
    }

    public String generateToken(Map<String, Object> extraClaims, UserDetails userDetails){
        return buildToken(extraClaims , userDetails , jwtExpiration);
    }

    private String buildToken(Map<String , Object> extraClaims, UserDetails userDetails,  long expiration){
        return Jwts.builder().subject(userDetails.getUsername()).issuedAt(new Date()).expiration(new Date(System.currentTimeMillis() + jwtExpiration)).claims(extraClaims)
                .signWith(getSignInKey(), SignatureAlgorithm.HS256).compact();
    }

    private Key getSignInKey() {
        byte[] keyBytes = Decoders.BASE64.decode(secretKey);
        return Keys.hmacShaKeyFor(keyBytes);

    }

    public String extractUsername (String token){
        return extractClaim(token, Claims::getSubject);
    }

    public <T> T extractClaim (String token , Function <Claims , T> claimsResolver){
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

    private Claims extractAllClaims(String token) {
        return Jwts.parser()
                .setSigningKey(getSignInKey()) // Key should be HMAC or RSA depending on your use
                .build()
                .parseClaimsJws(token)
                .getBody(); // returns the claims (payload) of the JWT
    }

    public boolean isTokenValid(String token, UserDetails userDetails) {
        final String username = extractUsername(token);
        return (username.equals(userDetails.getUsername())) && !isTokenExpired(token);
    }

    public long getExpirationTime() {
        return jwtExpiration;
    }

    private boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    private Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }

}
