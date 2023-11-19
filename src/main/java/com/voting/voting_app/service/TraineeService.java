package com.voting.voting_app.service;

import com.voting.voting_app.dto.request.NewTraineeRequest;
import com.voting.voting_app.dto.response.TraineeResponse;

public interface TraineeService {
    TraineeResponse createNew(NewTraineeRequest request);
}
