package co.los_inges.sistema_compras.security;

import co.los_inges.sistema_compras.dtos.request.LoginRequest;
import co.los_inges.sistema_compras.dtos.response.AuthResponse;
import jakarta.validation.Valid;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.*;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
@RestController
@RequestMapping("/auth")
public class AuthController {

    private final AuthenticationManager authManager;
    private final JwtService jwtService;

    public AuthController(AuthenticationManager authManager, JwtService jwtService) {
        this.authManager = authManager;
        this.jwtService = jwtService;
    }

    @PostMapping("/login")
    public AuthResponse login(@RequestBody @Valid LoginRequest request) {
        Authentication authentication = authManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.username(), request.password())
        );
        UserDetails user = (UserDetails) authentication.getPrincipal();

        // Puedes incluir claims como roles, id de usuario, etc.
        String token = jwtService.generate(user.getUsername(), Map.of(
                "roles", user.getAuthorities().stream().map(GrantedAuthority::getAuthority).toList()
        ));

        return new AuthResponse(token, "Bearer", jwtService.expiresInSeconds());
    }
}

