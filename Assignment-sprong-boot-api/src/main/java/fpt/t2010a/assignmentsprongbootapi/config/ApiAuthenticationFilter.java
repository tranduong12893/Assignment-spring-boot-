package fpt.t2010a.assignmentsprongbootapi.config;

import fpt.t2010a.assignmentsprongbootapi.entity.dto.CredentialDto;
import fpt.t2010a.assignmentsprongbootapi.entity.dto.LoginDto;
import fpt.t2010a.assignmentsprongbootapi.util.JWTUtil;
import com.google.gson.Gson;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.stream.Collectors;

@Log4j2
@RequiredArgsConstructor
public class ApiAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    private final AuthenticationManager authenticationManager;

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        log.info("checking ......................... ");
        try {
            String jsonData =  request.getReader().lines().collect(Collectors.joining());
            Gson gson = new Gson();
            LoginDto loginDTO= gson.fromJson(jsonData, LoginDto.class);
            UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(loginDTO.getUsername(), loginDTO.getPassword());
            return authenticationManager.authenticate(authenticationToken);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    protected void successfulAuthentication(
            HttpServletRequest request,
            HttpServletResponse response,
            FilterChain chain,
            Authentication authResult) throws IOException {
        User user = (User) authResult.getPrincipal();
        String accessToken = JWTUtil.generateToken(user.getUsername(),
                user.getAuthorities().iterator().next().getAuthority(),
                request.getRequestURI(),
                JWTUtil.ONE_DAY * 7);

        String refreshToken = JWTUtil.generateToken(user.getUsername(),
                user.getAuthorities().iterator().next().getAuthority(),
                request.getRequestURI(),
                JWTUtil.ONE_DAY * 14);
        CredentialDto credentialDTO = new CredentialDto(accessToken, refreshToken);
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        Gson gson = new Gson();
        response.getWriter().println(gson.toJson(credentialDTO));
    }

    @Override
    protected void unsuccessfulAuthentication(
            HttpServletRequest request,
            HttpServletResponse response,
            AuthenticationException failed) throws IOException {
        HashMap<String, String> errors = new HashMap<>();
        errors.put("message", "stupid user");
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        Gson gson = new Gson();
        response.getWriter().println(gson.toJson(errors));
    }
}
