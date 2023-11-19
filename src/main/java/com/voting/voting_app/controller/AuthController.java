package com.voting.voting_app.controller;

import com.voting.voting_app.dto.request.AuthRequest;
import com.voting.voting_app.dto.request.RegisterRequest;
import com.voting.voting_app.dto.response.CommonResponse;
import com.voting.voting_app.dto.response.LoginResponse;
import com.voting.voting_app.dto.response.RegisterResponse;
import com.voting.voting_app.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
    private final AuthService authService;

    @Autowired
    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/register/trainee")
    public ResponseEntity<?> register(@RequestBody RegisterRequest request) {
        RegisterResponse registerResponse = authService.registerTrainee(request);
        CommonResponse<RegisterResponse> response = CommonResponse.<RegisterResponse>builder()
                .message("successfully register new customer")
                .statusCode(HttpStatus.CREATED.value())
                .data(registerResponse)
                .build();
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(response);
    }

    @PostMapping("/register/admin")
    public ResponseEntity<?> registerAdmin(@RequestBody RegisterRequest request) {
        RegisterResponse registerResponse = authService.registerAdmin(request);
        CommonResponse<RegisterResponse> response = CommonResponse.<RegisterResponse>builder()
                .message("successfully register new admin")
                .statusCode(HttpStatus.CREATED.value())
                .data(registerResponse)
                .build();
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(response);
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody AuthRequest request) {
        LoginResponse login = authService.login(request);
        CommonResponse<LoginResponse> response = CommonResponse.<LoginResponse>builder()
                .message("successfully login")
                .statusCode(HttpStatus.OK.value())
                .data(login)
                .build();
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(response);
    }
}
