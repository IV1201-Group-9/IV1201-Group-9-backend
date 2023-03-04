package com.iv1201.recapp.Service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

/**
 * Provides Jason Web Token service functions for <code>JwtFilter<code/>
 * so tokens can be created and checked. Also holds methods for getting
 * information of the token.
 */
@Service
public class JwtService {

    @Value("${jwt.secret}")
    private String secret;

    public JwtService(){
    }

    /**
     *
     * @param userDetails
     * @return
     */
    public String createToken(UserDetails userDetails){
        return createToken(new HashMap<>(), userDetails);
    }

    /**
     * Creates a token from claims and user details.
     * Set when the token is created and how long til token expires.
     * Expiration now set to 1 sec -> 1 min -> 1 hour -> 24 hour
     * @param creationClaims
     * @param userDetails
     * @return a build JWT
     */
    public String createToken(Map<String, Object> creationClaims, UserDetails userDetails){
        return Jwts
                .builder()
                .setClaims(creationClaims)
                .setSubject(userDetails.getUsername())
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis()+1000*60*90)) // 1 sec -> 1 min -> 1 hour
                .signWith(createSignInKey(), SignatureAlgorithm.HS256)
                .compact();
    }

    private Key createSignInKey() {
        byte[] keyBase64 = Decoders.BASE64.decode(secret);
        return Keys.hmacShaKeyFor(keyBase64);
    }

    public boolean checkTokenValidity(String jwtToken, UserDetails userDetails){
        final String username = getUsernameFromToken(jwtToken);
        return (username.equals(userDetails.getUsername())) && !checkExpirationOfToken(jwtToken);
    }

    private boolean checkExpirationOfToken(String jwtToken) {
        return getExpirationFromToken(jwtToken).before(new Date());
    }

    private Date getExpirationFromToken(String jwtToken) {
        return getSingleClaim(jwtToken, Claims::getExpiration);
    }

    public String getUsernameFromToken(String jwtToken) {
        return getSingleClaim(jwtToken, Claims::getSubject);
    }

    public<T> T getSingleClaim(String jwtToken, Function<Claims, T> claimFetcher){
        final Claims allClaims = getAllClaims(jwtToken);
        return claimFetcher.apply(allClaims);
    }

    private Claims getAllClaims(String jwtToken){
        return Jwts
                .parserBuilder()
                .setSigningKey(createSignInKey())
                .build()
                .parseClaimsJws(jwtToken)
                .getBody();
    }

}
