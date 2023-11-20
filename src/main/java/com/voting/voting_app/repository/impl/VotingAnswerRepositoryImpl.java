package com.voting.voting_app.repository.impl;

import com.voting.voting_app.entity.Voting;
import com.voting.voting_app.entity.VotingAnswer;
import com.voting.voting_app.repository.VotingAnswerRepository;
import com.voting.voting_app.repository.VotingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.List;
import java.util.Optional;

@Repository
public class VotingAnswerRepositoryImpl implements VotingAnswerRepository {
    private final EntityManager entityManager;

    @Autowired
    public VotingAnswerRepositoryImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public VotingAnswer save(VotingAnswer votingAnswer) {
        entityManager.persist(votingAnswer);
        return votingAnswer;
    }
}
