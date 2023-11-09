package com.matchpointecv.matchpointecv.filter;

import at.favre.lib.crypto.bcrypt.BCrypt;
import com.matchpointecv.matchpointecv.usuario.Usuario;
import com.matchpointecv.matchpointecv.usuario.UsuarioRepository;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Base64;
import java.util.HashSet;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

@Component
public class FilterAuth extends OncePerRequestFilter {
    @Autowired
    private UsuarioRepository usuarioRepository;

    private static final Set<String> URLS_PERMITIDAS = new HashSet<>();

    static {
        URLS_PERMITIDAS.add("/swagger-ui");
        URLS_PERMITIDAS.add("/v3/api-docs");
        URLS_PERMITIDAS.add("/usuarios/cadastrar");
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {

        String servletPath = request.getServletPath();

        if(contemUrlPermitida(servletPath)) {

            filterChain.doFilter(request, response);

        } else {
            String authorization = request.getHeader("Authorization");

            String authEncoded = authorization.substring("Basic".length()).trim();

            byte[] authDecode = Base64.getDecoder().decode(authEncoded);

            String authString = new String(authDecode);

            String[] credentials = authString.split(":");
            String email = credentials[0];
            String password = credentials[1];

            Usuario user = usuarioRepository.findByEmail(email);
            verifyUser(request, response, filterChain, password, user);
        }

    }

    private void verifyUser(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain, String password, Usuario user)
            throws IOException, ServletException {
        if ( user == null) {
            response.sendError(401);
        } else {
            BCrypt.Result passwordVerify =  BCrypt.verifyer().verify(password.toCharArray(), user.getSenha());
            this.verifyPassword(passwordVerify, user, request, response, filterChain);
        }
    }

    private void verifyPassword(BCrypt.Result password, Usuario user, HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        if (password.verified) {
            request.setAttribute("idUser", user.getId());
            filterChain.doFilter(request, response);
        } else {
            response.sendError(401);
        }
    }

    private boolean contemUrlPermitida(String servletPath) {
        for (String urlPermitida : URLS_PERMITIDAS) {
            if (servletPath.contains(urlPermitida)) {
                return true;
            }
        }
        return false;
    }

}
