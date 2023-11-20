package com.voting.voting_app.service;

import com.voting.voting_app.dto.request.VotingAnswerRequest;
import com.voting.voting_app.dto.request.VotingRequest;
import com.voting.voting_app.dto.response.VotingResponse;

import java.util.List;
import java.util.Optional;

public interface VotingAnswerService {
    void vote(VotingAnswerRequest request);
}
