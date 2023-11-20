package com.voting.voting_app.service;

import com.voting.voting_app.dto.request.NewTraineeRequest;
import com.voting.voting_app.dto.response.TraineeResponse;
import com.voting.voting_app.dto.response.VotingResponse;
import com.voting.voting_app.entity.Trainee;

import java.util.Optional;

public interface TraineeService {
    TraineeResponse createNew(NewTraineeRequest request);
    Optional<Trainee> findTraineeByUserCredentialId(String id);
}
