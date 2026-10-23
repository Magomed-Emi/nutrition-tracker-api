
package com.emi.nutritrack.controller;

import com.emi.nutritrack.dto.LoginRequest;
import com.emi.nutritrack.dto.RegisterRequest;
import com.emi.nutritrack.service.AuthService;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/register")
    @ResponseStatus(HttpStatus.CREATED)
    public void register(@RequestBody RegisterRequest request) {
        authService.register(request);
    }

    @PostMapping("/login")
    public Map<String, String> login(@RequestBody LoginRequest request) {

        String token = authService.login(request);

        return Map.of(
                "token", token,
                "tokenType", "Bearer"
        );
    }
}
