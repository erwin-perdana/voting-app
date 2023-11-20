package com.voting.voting_app.repository.impl;

import com.voting.voting_app.entity.Voting;
import com.voting.voting_app.entity.VotingDetail;
import com.voting.voting_app.repository.VotingDetailRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

@Repository
public class VotingDetailRepositoryImpl implements VotingDetailRepository {
    private final EntityManager entityManager;

    @Autowired
    public VotingDetailRepositoryImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public VotingDetail saveAndFlush(VotingDetail votingDetail) {
        entityManager.persist(votingDetail);
        entityManager.flush();
        return votingDetail;
    }

    @Override
    public void delete(String id) {
        String nativeQuery = "DELETE FROM m_voting_detail WHERE id = ?";
        entityManager.createNativeQuery(nativeQuery)
                .setParameter(1, id)
                .executeUpdate();
    }

    @Override
    public Optional<VotingDetail> findById(String id) {
        String nativeQuery = "SELECT * FROM m_voting_detail WHERE id = ?";
        VotingDetail votingDetail = (VotingDetail) entityManager.createNativeQuery(nativeQuery, VotingDetail.class)
                .setParameter(1, id)
                .getSingleResult();
        return Optional.ofNullable(votingDetail);
    }

    @Override
    public List<VotingDetail> findAll() {
        String nativeQuery = "SELECT * FROM m_voting_detail";
        List<VotingDetail> votings =  entityManager.createNativeQuery(nativeQuery, VotingDetail.class)
                .getResultList();
        return votings;
    }
}
