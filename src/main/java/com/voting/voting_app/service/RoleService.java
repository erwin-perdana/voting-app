package com.voting.voting_app.service;

import com.voting.voting_app.entity.Role;

public interface RoleService {
    Role getOrSave(Role role);
}
