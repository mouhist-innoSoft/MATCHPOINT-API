package com.matchpointecv.matchpointecv.security;

import com.matchpointecv.matchpointecv.auth.JwtTokenService;
import com.matchpointecv.matchpointecv.auth.UserDetailsImpl;
import com.matchpointecv.matchpointecv.exception.CustomException;
import com.matchpointecv.matchpointecv.usuario.Usuario;
import com.matchpointecv.matchpointecv.usuario.UsuarioRepository;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

@Component
public class UserAuthenticationFilter extends OncePerRequestFilter{

    @Autowired
    private JwtTokenService jwtTokenService;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        if (verificaEndpointsPublicos(request)) {
            String token = recuperaToken(request);
            if (token != null) {
                String subject = jwtTokenService.pegarToken(token);
                Usuario usuario = usuarioRepository.findByEmail(subject).get();
                UserDetailsImpl userDetails = new UserDetailsImpl(usuario);
                Authentication authentication =
                        new UsernamePasswordAuthenticationToken(
                                userDetails.getUsername(),
                                null,
                                userDetails.getAuthorities());

                SecurityContextHolder.getContext().setAuthentication(authentication);
            } else {
                throw new CustomException("Usuário não autenticado!");
            }
        }
        filterChain.doFilter(request, response);
    }

    private boolean verificaEndpointsPublicos(HttpServletRequest request) {
        String requestURI = request.getRequestURI();
        return !(requestURI.equals("/api/usuario/cadastrar") ||
                requestURI.equals("/api/auth/login") ||
                requestURI.startsWith("/swagger-ui/") ||
                requestURI.startsWith("/v3/api-docs"));
    }

    private String recuperaToken(HttpServletRequest request) {
        String authorizationHeader = request.getHeader("Authorization");
        if (authorizationHeader != null) {
            return authorizationHeader.replace("Bearer ", "");
        }
        return null;
    }

}