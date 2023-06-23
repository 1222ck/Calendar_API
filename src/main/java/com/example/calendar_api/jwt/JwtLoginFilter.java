package com.example.calendar_api.jwt;

import com.example.calendar_api.members.domain.Member;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import java.io.IOException;

public class JwtLoginFilter extends UsernamePasswordAuthenticationFilter {
    public JwtLoginFilter(AuthenticationManager authenticationManager) {
        super(authenticationManager);
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authResult) {
        try {
            //User user = (User) authResult.getPrincipal();
            //String username = user.getUsername(); // 아이디(학번)

            Member member = (Member) authResult.getPrincipal();
            String email = member.getEmail();

            // JWT 생성
            Algorithm algorithm = Algorithm.HMAC256("secret");
            String accessToken = JWT.create()
                    .withIssuer("issuer")
                    .withSubject(email)
                    .sign(algorithm);

            response.getWriter().write(accessToken);
        } catch (JWTCreationException | IOException exception) {
            exception.printStackTrace();
        }
    }
}
