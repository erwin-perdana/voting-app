package com.voting.voting_app.repository;

import com.voting.voting_app.entity.VotingDetail;

import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

public interface VotingDetailRepository {
    VotingDetail saveAndFlush(VotingDetail votingDetail);
    void delete(String id);
    Optional<VotingDetail> findById(String id);
    List<VotingDetail> findAll();
}
