package com.voting.voting_app.repository.impl;

import com.voting.voting_app.entity.Trainee;
import com.voting.voting_app.entity.Voting;
import com.voting.voting_app.repository.TraineeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.Optional;

@Repository
public class TraineeRepositoryImpl implements TraineeRepository {
    private final EntityManager entityManager;

    @Autowired
    public TraineeRepositoryImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }
    @Override
    @Transactional(rollbackFor = Exception.class)
    public Trainee saveAndFlush(Trainee trainee) {
        entityManager.persist(trainee);
        entityManager.flush();
        return trainee;
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Trainee> findByUserCredentialId(String id) {
        String nativeQuery = "SELECT * FROM m_trainee WHERE user_credential_id = ?";
        Trainee trainee = (Trainee) entityManager.createNativeQuery(nativeQuery, Trainee.class)
                .setParameter(1, id)
                .getSingleResult();
        return Optional.ofNullable(trainee);
    }
}
