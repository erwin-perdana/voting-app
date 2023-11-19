package com.voting.voting_app.repository;

import com.voting.voting_app.entity.Admin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

public interface AdminRepository {
    Admin saveAndFlush(Admin admin);
}
