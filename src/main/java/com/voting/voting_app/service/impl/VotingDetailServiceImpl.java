package com.voting.voting_app.service.impl;

import com.voting.voting_app.entity.VotingDetail;
import com.voting.voting_app.repository.VotingDetailRepository;
import com.voting.voting_app.service.VotingDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class VotingDetailServiceImpl implements VotingDetailService {
    private final VotingDetailRepository votingDetailRepository;

    @Autowired
    public VotingDetailServiceImpl(VotingDetailRepository votingDetailRepository) {
        this.votingDetailRepository = votingDetailRepository;
    }

    public VotingDetail saveVotingDetail(VotingDetail votingDetail) {
        VotingDetail save = votingDetailRepository.saveAndFlush(votingDetail);
        return save;
    }

    @Override
    public void deleteVotingDetail(String id) {
        votingDetailRepository.delete(id);
    }

    public Optional<VotingDetail> findVotingDetailById(String id) {
        return votingDetailRepository.findById(id);
    }

    public List<VotingDetail> findAllVotingDetails() {
        return votingDetailRepository.findAll();
    }
}
