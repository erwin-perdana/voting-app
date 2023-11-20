package com.voting.voting_app.service;

import com.voting.voting_app.dto.request.VotingRequest;
import com.voting.voting_app.dto.response.VotingResponse;
import com.voting.voting_app.entity.Voting;

import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

public interface VotingService {
    VotingResponse saveVoting(VotingRequest request);
    void deleteVoting(String id);
    Optional<VotingResponse> findVotingById(String id);
    List<VotingResponse> findAllVotings();
}
