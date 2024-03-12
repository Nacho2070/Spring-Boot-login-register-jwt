
package com.proyect_Ignacio.Proyect.jwt;

import org.springframework.http.HttpHeaders;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import io.jsonwebtoken.io.IOException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;

/**
 * JwtAuthenticationFilter
 */

@Component
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter{

    private final JwtService jwtService;
    private final UserDetailsService userDetailsService;

    @Override
    protected void doFilterInternal(@SuppressWarnings("null") HttpServletRequest request, 
                                     @SuppressWarnings("null") HttpServletResponse response, @SuppressWarnings("null") FilterChain filterChain)
                                     throws ServletException, IOException, java.io.IOException {
             
        final String token = getTokenFromRequest(request);
        final String username;


        if (token==null)
        {
            filterChain.doFilter(request, response);
            return;
        }

        username = jwtService.getUsernameFromToken(token);
      // We check that the username is valid and that there isn´t authentication in the security context.
        if (username!=null && SecurityContextHolder.getContext().getAuthentication()==null)
        {
            //We get the data from the database.
            UserDetails userDetails = userDetailsService.loadUserByUsername(username);
           //If the user is valid we need to update the security context
            if (jwtService.isTokenValid(token, userDetails))
            {
              // Create an authentication object and set it in the security context
                UsernamePasswordAuthenticationToken authToken= new UsernamePasswordAuthenticationToken(
                    userDetails,
                    null,
                    userDetails.getAuthorities());

                authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

                SecurityContextHolder.getContext().setAuthentication(authToken);
            }

        }
        
        filterChain.doFilter(request, response);
    }

    private String getTokenFromRequest(HttpServletRequest request) {
        final String authHeader=request.getHeader(HttpHeaders.AUTHORIZATION);
        // We extract the token from the header, and if it is present and in the correct format
        if(StringUtils.hasText(authHeader) && authHeader.startsWith("Bearer "))
        {
            return authHeader.substring(7);
        }
        return null;
    }
    
}