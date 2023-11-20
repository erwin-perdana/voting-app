package com.voting.voting_app.repository;

import com.voting.voting_app.entity.Trainee;
import com.voting.voting_app.entity.Voting;

import java.util.Optional;

public interface TraineeRepository {
    Trainee saveAndFlush(Trainee trainee);
    Optional<Trainee> findByUserCredentialId(String id);
}
