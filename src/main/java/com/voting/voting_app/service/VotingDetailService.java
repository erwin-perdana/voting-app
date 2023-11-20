package com.voting.voting_app.service;

import com.voting.voting_app.entity.VotingDetail;

import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

public interface VotingDetailService {
    VotingDetail saveVotingDetail(VotingDetail votingDetail);

    void deleteVotingDetail(String id);

    Optional<VotingDetail> findVotingDetailById(String id);

    List<VotingDetail> findAllVotingDetails();
}
