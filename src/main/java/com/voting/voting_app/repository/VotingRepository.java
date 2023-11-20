package com.voting.voting_app.repository;

import com.voting.voting_app.entity.Voting;

import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

public interface VotingRepository {
    Voting saveAndFlush(Voting voting);
    void delete(String id);
    Optional<Voting> findById(String id);
    List<Voting> findAll();
}
