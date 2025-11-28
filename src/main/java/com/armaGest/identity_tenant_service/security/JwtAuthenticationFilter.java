package com.armaGest.identity_tenant_service.security;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {

        var auth = SecurityContextHolder.getContext().getAuthentication();

        if (auth instanceof JwtAuthenticationToken jwtAuth) {
            Jwt token = jwtAuth.getToken();

            // Leggiamo username e tenant_id dal token Keycloak
            String username = token.getSubject();
            Number tenantIdNum = token.getClaim("tenant_id");
            Long tenantId = tenantIdNum != null ? tenantIdNum.longValue() : null;

            // Creiamo il nostro oggetto AuthenticatedUser
            AuthenticatedUser user = new AuthenticatedUser(username, tenantId);

            // Memorizziamo l'oggetto nel dettaglio dell'autenticazione
            jwtAuth.setDetails(user);
        }

        filterChain.doFilter(request, response);
    }
}
