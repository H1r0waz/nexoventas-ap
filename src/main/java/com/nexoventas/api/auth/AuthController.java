package com.nexoventas.api.auth;
import jakarta.validation.Valid;
import org.springframework.http.*;
import org.springframework.security.authentication.*;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
@RestController @RequestMapping("/api/v1/auth")
public class AuthController {
    private final AuthenticationManager authenticationManager; private final JwtService jwt;
    public AuthController(AuthenticationManager authenticationManager, JwtService jwt) { this.authenticationManager=authenticationManager; this.jwt=jwt; }
    @PostMapping("/login") public LoginResponse login(@Valid @RequestBody LoginRequest request) {
        var authentication=authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.username(), request.password()));
        return new LoginResponse(jwt.createToken((UserDetails) authentication.getPrincipal()), "Bearer");
    }
}
