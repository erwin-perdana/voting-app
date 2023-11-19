package com.voting.voting_app.repository;

import com.voting.voting_app.entity.Trainee;

public interface TraineeRepository {
    Trainee saveAndFlush(Trainee trainee);
}
