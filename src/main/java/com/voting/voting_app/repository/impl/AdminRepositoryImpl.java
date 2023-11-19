package com.voting.voting_app.repository.impl;

import com.voting.voting_app.entity.Admin;
import com.voting.voting_app.repository.AdminRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;

@Repository
public class AdminRepositoryImpl implements AdminRepository {
    private final EntityManager entityManager;

    @Autowired
    public AdminRepositoryImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }
    @Override
    @Transactional(rollbackFor = Exception.class)
    public Admin saveAndFlush(Admin admin) {
        entityManager.persist(admin);
        entityManager.flush();
        return admin;
    }
}
