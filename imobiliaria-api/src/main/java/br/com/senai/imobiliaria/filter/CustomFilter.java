package br.com.senai.imobiliaria.filter;

import br.com.senai.imobiliaria.model.User;
import br.com.senai.imobiliaria.model.UserPrincipal;
import br.com.senai.imobiliaria.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Base64;
import java.util.Optional;

public class CustomFilter extends BasicAuthenticationFilter {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    public CustomFilter() {
        super(authenticationManager -> null);
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        String header = request.getHeader("Authorization");

        if (header == null || !header.startsWith("Basic ")) {
            chain.doFilter(request, response);
            return;
        }

        UsernamePasswordAuthenticationToken authentication = getAuthentication(header);
        SecurityContextHolder.getContext().setAuthentication(authentication);
        chain.doFilter(request, response);
    }

    private UsernamePasswordAuthenticationToken getAuthentication(String header) {
        byte[] decodedHeader = Base64.getDecoder().decode(header.replace("Basic ", ""));
        String[] credentials = new String(decodedHeader).split(":", 2);

        String username = credentials[0];
        String password = credentials[1];

        Optional<User> userOpt = userRepository.findByUsername(username);
        if (userOpt.isPresent() && passwordEncoder.matches(password, userOpt.get().getPassword())) {
            UserPrincipal userPrincipal = new UserPrincipal(userOpt.get());
            return new UsernamePasswordAuthenticationToken(userPrincipal, null, userPrincipal.getAuthorities());
        }

        return null;
    }
}
