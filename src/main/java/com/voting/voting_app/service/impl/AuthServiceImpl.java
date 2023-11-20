package com.voting.voting_app.service.impl;

import com.voting.voting_app.constant.ERole;
import com.voting.voting_app.dto.request.AuthRequest;
import com.voting.voting_app.dto.request.NewTraineeRequest;
import com.voting.voting_app.dto.request.RegisterRequest;
import com.voting.voting_app.dto.response.LoginResponse;
import com.voting.voting_app.dto.response.RegisterResponse;
import com.voting.voting_app.entity.Admin;
import com.voting.voting_app.entity.AppUser;
import com.voting.voting_app.entity.Role;
import com.voting.voting_app.entity.UserCredential;
import com.voting.voting_app.repository.UserCredentialRepository;
import com.voting.voting_app.security.JwtUtil;
import com.voting.voting_app.service.AdminService;
import com.voting.voting_app.service.AuthService;
import com.voting.voting_app.service.TraineeService;
import com.voting.voting_app.service.RoleService;
import com.voting.voting_app.util.ValidationUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {
    private final UserCredentialRepository userCredentialRepository;
    private final PasswordEncoder passwordEncoder;
    private final RoleService roleService;
    private final AdminService adminService;
    private final TraineeService traineeService;
    private final JwtUtil jwtUtil;
    private final ValidationUtil validationUtil;
    private final AuthenticationManager authenticationManager;

    @Transactional(rollbackFor = Exception.class)
    @Override
    public RegisterResponse registerTrainee(RegisterRequest request) {
        try {
            validationUtil.validate(request);
            // role
            Role role = roleService.getOrSave(Role.builder().name(ERole.ROLE_TRAINEE).build());

            // usercredential
            UserCredential userCredential = UserCredential.builder()
                    .username(request.getUsername().toLowerCase())
                    .password(passwordEncoder.encode(request.getPassword()))
                    .role(role)
                    .build();
            userCredentialRepository.saveAndFlush(userCredential);

            // customer
            NewTraineeRequest customer = NewTraineeRequest.builder()
                    .userCredentialId(userCredential.getId())
                    .build();
            traineeService.createNew(customer);

            return RegisterResponse.builder()
                    .username(userCredential.getUsername())
                    .role(userCredential.getRole().getName().toString())
                    .build();
        } catch (DataIntegrityViolationException e) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "user already exist");
        }
    }

    @Override
    public RegisterResponse registerAdmin(RegisterRequest request) {
        try {
            validationUtil.validate(request);
            // role
            Role role = roleService.getOrSave(Role.builder().name(ERole.ROLE_ADMIN).build());

            // usercredential
            UserCredential userCredential = UserCredential.builder()
                    .username(request.getUsername().toLowerCase())
                    .password(passwordEncoder.encode(request.getPassword()))
                    .role(role)
                    .build();
            userCredentialRepository.saveAndFlush(userCredential);

            // admin
            Admin admin = Admin.builder()
                    .userCredential(userCredential)
                    .name(request.name)
                    .build();
            adminService.createNew(admin);

            return RegisterResponse.builder()
                    .username(userCredential.getUsername())
                    .role(userCredential.getRole().getName().toString())
                    .build();
        } catch (DataIntegrityViolationException e) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "user already exist");
        }
    }

    @Override
    public LoginResponse login(AuthRequest request) {
        validationUtil.validate(request);
        Authentication authenticate = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                request.getUsername().toLowerCase(),
                request.getPassword()
        ));
        SecurityContextHolder.getContext().setAuthentication(authenticate);

        AppUser appUser = (AppUser) authenticate.getPrincipal();
        String token = jwtUtil.generateToken(appUser);

        return LoginResponse.builder()
                .token(token)
                .role(appUser.getRole().name())
                .build();
    }
}
