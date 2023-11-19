package com.voting.voting_app.repository;

import com.voting.voting_app.constant.ERole;
import com.voting.voting_app.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

public interface RoleRepository{
    Optional<Role> findByName(ERole name);

    Role save(Role role);
}
