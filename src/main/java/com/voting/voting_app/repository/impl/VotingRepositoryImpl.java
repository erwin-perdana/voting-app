package com.voting.voting_app.repository.impl;

import com.voting.voting_app.entity.Voting;
import com.voting.voting_app.repository.VotingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

@Repository
public class VotingRepositoryImpl implements VotingRepository {
    private final EntityManager entityManager;

    @Autowired
    public VotingRepositoryImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Voting saveAndFlush(Voting voting) {
        entityManager.persist(voting);
        entityManager.flush();
        return voting;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void delete(String id) {
        String nativeQueryFind = "SELECT * FROM m_voting WHERE id = ?";
        Voting voting = (Voting) entityManager.createNativeQuery(nativeQueryFind, Voting.class)
                .setParameter(1, id)
                .getSingleResult();

        voting.getVotingDetails().stream().forEach(
                votingDetail -> {
                    String nativeQueryDelete = "DELETE FROM m_voting_detail WHERE id = ?";
                    entityManager.createNativeQuery(nativeQueryDelete)
                            .setParameter(1, votingDetail.getId())
                            .executeUpdate();
                }
        );

        String nativeQueryDelete = "DELETE FROM m_voting WHERE id = ?";
        entityManager.createNativeQuery(nativeQueryDelete)
                .setParameter(1, id)
                .executeUpdate();
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Voting> findById(String id) {
        String nativeQuery = "SELECT * FROM m_voting WHERE id = ?";
        Voting voting = (Voting) entityManager.createNativeQuery(nativeQuery, Voting.class)
                .setParameter(1, id)
                .getSingleResult();
        return Optional.ofNullable(voting);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Voting> findAll() {
        String nativeQuery = "SELECT * FROM m_voting";
        Query query = entityManager.createNativeQuery(nativeQuery, Voting.class);

        return query.getResultList();
    }
}
