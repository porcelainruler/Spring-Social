package com.shubham.project.spring_network.service;

import io.jsonwebtoken.*;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

@Component
public class JwtService {

    private final Logger logger = LoggerFactory.getLogger(JwtService.class);
    @Value("${jwt.token.validity}")
    public long TOKEN_VALIDITY;

    @Value("${jwt.signing.key}")
    public String JWT_SIGNING_KEY;

    @Value("${jwt.authorities.key}")
    public String AUTHORITIES_KEY;


    /* ! Old Token gen logic
        public String generateToken (String username) {
            Map<String, Object> claims = new HashMap<>();
            return createToken(claims, username);
        }

        private String createToken(Map<String, Object> claims, String username) {
            return Jwts.builder()
                    .addClaims(claims)
                    .setSubject(username)
                    .setIssuedAt(new Date(System.currentTimeMillis()))
                    .setExpiration(new Date(System.currentTimeMillis() + 30 * 60 * 1000))
                    .signWith(getSignatureKey(), SignatureAlgorithm.HS512).compact();
        }
    */
    public String generateToken (Authentication auth) {
        String authorities = auth.getAuthorities()
                .stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.joining(","));

        return Jwts.builder()
                .setSubject(auth.getName())
                .claim(AUTHORITIES_KEY, authorities)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + TOKEN_VALIDITY))
                .signWith(SignatureAlgorithm.HS512, JWT_SIGNING_KEY)
                .compact();
    }

    private Key getSignatureKey() {
        byte[] keyByteArray = Decoders.BASE64.decode(JWT_SIGNING_KEY);
        return Keys.hmacShaKeyFor(keyByteArray);
    }

    public String getUsername (String token) {
        return extractClaim(token, Claims::getSubject);
    }

    public Date getExpiration (String token) {
        return extractClaim(token, Claims::getExpiration);
    }

    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaims(token);

        return claimsResolver.apply(claims);
    }

    private Claims extractAllClaims (String token) {
        return Jwts.parserBuilder()
                .setSigningKey(getSignatureKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    public Boolean isTokenExpired (String token) {
        try {
            Boolean isExpired = getExpiration(token).before(new Date());

            return isExpired;
        } catch (Exception e) {
            // TODO: Log error
            return true;
        }
    }

    public Boolean validateJWTToken (String token, UserDetails userDetails) {
        try {
            final String username = getUsername(token);

            return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
        } catch (MalformedJwtException e) {
            logger.error("Invalid JWT token: {}", e.getMessage());
        } catch (ExpiredJwtException e) {
            logger.error("JWT token is expired: {}", e.getMessage());
        } catch (UnsupportedJwtException e) {
            logger.error("JWT token is unsupported: {}", e.getMessage());
        } catch (IllegalArgumentException e) {
            logger.error("JWT claims string is empty: {}", e.getMessage());
        } catch (Exception e) {
            // TODO: Log exception
            logger.error("JWT validation failed with error: {}", e.getMessage());
        }
        return false;
    }

    public UsernamePasswordAuthenticationToken getAuthenticationToken(final String token, final Authentication existingAuth, final UserDetails userDetails) {

        final Claims claims = extractAllClaims(token);

        final Collection<? extends GrantedAuthority> authorities =
                Arrays.stream(claims.get(AUTHORITIES_KEY).toString().split(","))
                        .map(SimpleGrantedAuthority::new)
                        .collect(Collectors.toList());

        return new UsernamePasswordAuthenticationToken(userDetails, "", authorities);
    }
}
