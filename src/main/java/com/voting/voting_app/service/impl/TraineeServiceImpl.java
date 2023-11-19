package com.voting.voting_app.service.impl;

import com.voting.voting_app.dto.request.NewTraineeRequest;
import com.voting.voting_app.dto.response.TraineeResponse;
import com.voting.voting_app.entity.Trainee;
import com.voting.voting_app.entity.UserCredential;
import com.voting.voting_app.repository.TraineeRepository;
import com.voting.voting_app.service.TraineeService;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

@Service
@RequiredArgsConstructor
public class TraineeServiceImpl implements TraineeService {
    private final TraineeRepository traineeRepository;

    @Transactional(rollbackFor = Exception.class)
    @Override
    public TraineeResponse createNew(NewTraineeRequest request) {
        try {
            UserCredential userCredential = UserCredential.builder().
                    id(request.getUserCredentialId())
                    .build();
            Trainee trainee = Trainee.builder()
                    .name(request.getName())
                    .userCredential(userCredential)
                    .build();
            traineeRepository.saveAndFlush(trainee);
            return mapToResponse(trainee);
        } catch (DataIntegrityViolationException e) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "date invalid");
        }
    }

    private TraineeResponse mapToResponse(Trainee trainee) {
        return TraineeResponse.builder()
                .customerId(trainee.getId())
                .name(trainee.getName())
                .build();
    }
}
