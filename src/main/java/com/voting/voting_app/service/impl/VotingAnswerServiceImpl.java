package com.voting.voting_app.service.impl;

import com.voting.voting_app.dto.request.VotingAnswerRequest;
import com.voting.voting_app.entity.AppUser;
import com.voting.voting_app.entity.Trainee;
import com.voting.voting_app.entity.VotingAnswer;
import com.voting.voting_app.entity.VotingDetail;
import com.voting.voting_app.repository.VotingAnswerRepository;
import com.voting.voting_app.service.TraineeService;
import com.voting.voting_app.service.UserService;
import com.voting.voting_app.service.VotingAnswerService;
import com.voting.voting_app.service.VotingDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class VotingAnswerServiceImpl implements VotingAnswerService {
    private final VotingAnswerRepository votingAnswerRepository;
    private final VotingDetailService votingDetailService;
    private final TraineeService traineeService;

    @Autowired
    public VotingAnswerServiceImpl(VotingAnswerRepository votingAnswerRepository, VotingDetailService votingDetailService, TraineeService traineeService) {
        this.votingAnswerRepository = votingAnswerRepository;
        this.votingDetailService = votingDetailService;
        this.traineeService = traineeService;
    }
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void vote(VotingAnswerRequest request) {
        try {
            Optional<VotingDetail> votingDetail = votingDetailService.findVotingDetailById(request.getVotingDetailId());

            //check if user has vote
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            AppUser appUser = (AppUser) authentication.getPrincipal();

            votingDetail.get().getVoting().getVotingAnswers().stream().forEach(
                    votingAnswer -> {
                        if (appUser.getId().equals(votingAnswer.getTrainee().getUserCredential().getId())) {
                            throw new RuntimeException("User has voted this voting!");
                        }
                    }
            );

            Optional<Trainee> trainee = traineeService.findTraineeByUserCredentialId(appUser.getId());

            VotingAnswer votingAnswer = VotingAnswer.builder()
                    .voteDate(LocalDateTime.now())
                    .trainee(trainee.get())
                    .voting(votingDetail.get().getVoting())
                    .build();

            votingAnswerRepository.save(votingAnswer);
        } catch (Exception e) {
            throw new RuntimeException("Failed vote", e);
        }
    }
}
