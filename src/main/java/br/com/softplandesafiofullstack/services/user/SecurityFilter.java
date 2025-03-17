package br.com.softplandesafiofullstack.services.user;

import br.com.softplandesafiofullstack.repositories.user.UserRepositories;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Collections;

@Component
public class SecurityFilter extends OncePerRequestFilter {

    @Autowired
    TokenService tokenService;
    @Autowired
    UserRepositories userRepositories;

    public SecurityFilter(TokenService tokenService) {
        this.tokenService = tokenService;
    }

    private String recoverToken(HttpServletRequest request) {
        var authHeader = request.getHeader("Authorization");
        if (authHeader != null) {
            return authHeader.replace("Bearer ", "");
        } else {
            return null;
        }
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        var token = this.recoverToken(request);
        var subject = tokenService.validateToken(token);
        if (token != null) {

            UserDetails userDetails = userRepositories.findByEmail(subject).orElseThrow(() -> new UsernameNotFoundException("User not found"));
            var authorities = Collections.singletonList(new SimpleGrantedAuthority(userDetails.getAuthorities().iterator().next().getAuthority()));
            var authentication = new UsernamePasswordAuthenticationToken(userDetails, null, authorities);
            SecurityContextHolder.getContext().setAuthentication(authentication);
        } else {
            filterChain.doFilter(request, response);
        }
    }

}