package com.voting.voting_app.repository.impl;

import com.voting.voting_app.constant.ERole;
import com.voting.voting_app.entity.Role;
import com.voting.voting_app.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.Query;
import java.util.Optional;

@Repository
public class RoleRepositoryImpl implements RoleRepository {
    private final EntityManager entityManager;

    @Autowired
    public RoleRepositoryImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }
    @Transactional(readOnly = true)
    public Optional<Role> findByName(ERole name) {
        String nativeQuery = "SELECT * FROM m_role WHERE name = ?";
        Query query = entityManager.createNativeQuery(nativeQuery, Role.class)
                .setParameter(1, name.toString());

        try {
            Role role = (Role) query.getSingleResult();
            return Optional.ofNullable(role);
        } catch (NoResultException e) {
            return Optional.empty();
        }
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Role save(Role role) {
        entityManager.persist(role);
        return role;
    }
}
