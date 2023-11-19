package com.voting.voting_app.service.impl;

import com.voting.voting_app.entity.Role;
import com.voting.voting_app.repository.RoleRepository;
import com.voting.voting_app.service.RoleService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class RoleServiceImpl implements RoleService {
    private final RoleRepository roleRepository;

    @Override
    public Role getOrSave(Role role) {
        Optional<Role> optionalRole = roleRepository.findByName(role.getName());
        return optionalRole.orElseGet(() -> roleRepository.save(role));
    }
}
