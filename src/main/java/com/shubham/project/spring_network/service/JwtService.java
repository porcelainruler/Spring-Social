package com.shubham.project.spring_network.service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Component
public class JwtService {

    public static final String JwtSecretKey = "6T74IF5G6A874T47YB84YY488YT987T07TVT08T0980Y98TYY9Y9Y857B8738978B8Y8YV8Y8VYTB89YT89Y888H645455GFHJH";

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

    private Key getSignatureKey() {
        byte[] keyByteArray = Decoders.BASE64.decode(JwtSecretKey);
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
                .parseClaimsJwt(token)
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

            return (username.equals(userDetails.getUsername()) && getExpiration(token).before(new Date()));
        } catch (Exception e) {
            // TODO: Log exception
            return false;
        }
    }
}
