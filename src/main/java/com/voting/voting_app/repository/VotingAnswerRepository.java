package com.voting.voting_app.repository;

import com.voting.voting_app.entity.Voting;
import com.voting.voting_app.entity.VotingAnswer;

import java.util.List;
import java.util.Optional;

public interface VotingAnswerRepository {
    VotingAnswer save(VotingAnswer votingAnswer);
}
