package com.voting.voting_app.service.impl;

import com.voting.voting_app.constant.Status;
import com.voting.voting_app.dto.request.VotingRequest;
import com.voting.voting_app.dto.response.VotingDetailResponse;
import com.voting.voting_app.dto.response.VotingResponse;
import com.voting.voting_app.entity.Voting;
import com.voting.voting_app.entity.VotingDetail;
import com.voting.voting_app.repository.VotingRepository;
import com.voting.voting_app.service.VotingDetailService;
import com.voting.voting_app.service.VotingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class VotingServiceImpl implements VotingService {
    private final VotingRepository votingRepository;
    private final VotingDetailService votingDetailService;

    @Autowired
    public VotingServiceImpl(VotingRepository votingRepository, VotingDetailService votingDetailService) {
        this.votingRepository = votingRepository;
        this.votingDetailService = votingDetailService;
    }
    @Override
    @Transactional(rollbackFor = Exception.class)
    public VotingResponse saveVoting(VotingRequest request) {
        try {
            Voting voting = Voting.builder()
                    .votingDate(LocalDateTime.now())
                    .status(Status.START.toString())
                    .build();

            Voting savedVoting = votingRepository.saveAndFlush(voting);

            List<VotingDetail> votingDetails = request.getVotingDetails().stream().map(votingDetail -> {
                VotingDetail data = VotingDetail.builder()
                        .voting(savedVoting)
                        .name(votingDetail.getName())
                        .description(votingDetail.getDescription())
                        .build();
                return votingDetailService.saveVotingDetail(data);
            }).collect(Collectors.toList());

            savedVoting.setVotingDetails(votingDetails);

            return VotingResponse.builder()
                    .votingId(savedVoting.getId())
                    .votingDate(savedVoting.getVotingDate().toString())
                    .build();
        } catch (Exception e) {
            // Handling error
            throw new RuntimeException("Failed save", e);
        }
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deleteVoting(String id) {
        votingRepository.delete(id);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<VotingResponse> findVotingById(String id) {
        Optional<Voting> voting = votingRepository.findById(id);
        return Optional.ofNullable(VotingResponse.builder()
                .votingId(voting.get().getId())
                .votingDate(voting.get().getVotingDate().toString())
                .votingDetails(voting.get().getVotingDetails().stream().map(
                        votingDetail -> {
                            return VotingDetailResponse.builder()
                                    .votingDetailId(votingDetail.getId())
                                    .votingId(votingDetail.getId())
                                    .name(votingDetail.getName())
                                    .description(votingDetail.getDescription())
                                    .build();
                        }
                ).collect(Collectors.toList()))
                .build());
    }

    @Override
    @Transactional(readOnly = true)
    public List<VotingResponse> findAllVotings() {
        List<Voting> votings = votingRepository.findAll();
        return votings.stream().map(voting -> {
            return VotingResponse.builder()
                    .votingId(voting.getId())
                    .votingDate(voting.getVotingDate().toString())
                    .votingDetails(voting.getVotingDetails().stream().map(
                            votingDetail -> {
                                return VotingDetailResponse.builder()
                                        .votingDetailId(votingDetail.getId())
                                        .votingId(votingDetail.getId())
                                        .name(votingDetail.getName())
                                        .description(votingDetail.getDescription())
                                        .build();
                            }
                    ).collect(Collectors.toList()))
                    .build();
        }).collect(Collectors.toList());
    }
}
