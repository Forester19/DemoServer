package com.realserver.security.jwt;

import com.realserver.model.Role;
import io.jsonwebtoken.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import java.util.Base64;
import java.util.Date;

@Component
public class JwtTokenProvider {

    @Value("${jwt.token.secret}")
    private String secret;
    @Value("${jwt.token.expired}")
    private long validityInMilliSecs;

    @Autowired
    @Qualifier("jwtUserDetailsService")
    private  UserDetailsService userDetailsService;



    @Bean
    public BCryptPasswordEncoder passwordEncoder(){
     return new BCryptPasswordEncoder();
    }

    @PostConstruct
    public void init(){
        secret = Base64.getEncoder().encodeToString(secret.getBytes());
    }

    public String crateToken(String username, Role userRole) {
        Claims claims = Jwts.claims().setSubject(username);
        claims.put("role", userRole.name());

        Date now = new Date();
        Date validity = new Date(now.getTime() + validityInMilliSecs);

        return Jwts.builder().setClaims(claims)
                .setIssuedAt(now)
                .setExpiration(validity)
                .signWith(SignatureAlgorithm.ES256, secret)
                .compact();

    }

    public Authentication getAuthentication(String token) {
        UserDetails userDetails = userDetailsService.loadUserByUsername(getUserName(token));
        return new UsernamePasswordAuthenticationToken(userDetails, "", userDetails.getAuthorities());
    }

    private String getUserName(String token) {
        return Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody().getSubject();
    }

    public boolean validateToken(String token) {
        Jws<Claims> claimsJwt = Jwts.parser().setSigningKey(secret).parseClaimsJws(token);

        return !claimsJwt.getBody().getExpiration().before(new Date());
    }

    public String resolveToken(HttpServletRequest request){
        String bearerToken = request.getHeader("Authorization");
        if(bearerToken != null && bearerToken.startsWith("Bearer_")){
             return bearerToken.substring(7);
        }
        return null;
    }

}
