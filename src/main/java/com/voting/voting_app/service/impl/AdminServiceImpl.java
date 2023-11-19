package com.voting.voting_app.service.impl;

import com.voting.voting_app.entity.Admin;
import com.voting.voting_app.repository.AdminRepository;
import com.voting.voting_app.service.AdminService;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
@RequiredArgsConstructor
public class AdminServiceImpl implements AdminService {
    private final AdminRepository adminRepository;

    @Override
    public void createNew(Admin admin) {
        try {
            adminRepository.saveAndFlush(admin);
        } catch (DataIntegrityViolationException e) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "admin already exist");
        }
    }
}
