package com.voting.voting_app.service;

import com.voting.voting_app.dto.request.AuthRequest;
import com.voting.voting_app.dto.request.RegisterRequest;
import com.voting.voting_app.dto.response.LoginResponse;
import com.voting.voting_app.dto.response.RegisterResponse;

public interface AuthService {
    RegisterResponse registerTrainee(RegisterRequest request);
    RegisterResponse registerAdmin(RegisterRequest request);
    LoginResponse login(AuthRequest request);
}
