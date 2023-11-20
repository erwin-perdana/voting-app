package com.voting.voting_app.controller;

import com.voting.voting_app.dto.request.VotingRequest;
import com.voting.voting_app.dto.response.CommonResponse;
import com.voting.voting_app.dto.response.VotingResponse;
import com.voting.voting_app.entity.Voting;
import com.voting.voting_app.service.VotingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@RestController
@RequestMapping("/api/votings")
public class VotingController {
    private final VotingService votingService;

    @Autowired
    public VotingController(VotingService votingService) {
        this.votingService = votingService;
    }

    @PostMapping()
    public ResponseEntity<?> saveVoting(@RequestBody VotingRequest request) {
        VotingResponse save = votingService.saveVoting(request);
        CommonResponse<VotingResponse> response = CommonResponse.<VotingResponse>builder()
                .message("successfully create new voting")
                .statusCode(HttpStatus.CREATED.value())
                .data(save)
                .build();
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteVoting(@PathVariable String id) {
        Optional<VotingResponse> existingVoting = votingService.findVotingById(id);
        if (existingVoting.isPresent()) {
            votingService.deleteVoting(existingVoting.get().getVotingId());
            CommonResponse<VotingResponse> response = CommonResponse.<VotingResponse>builder()
                    .message("successfully delete voting")
                    .statusCode(HttpStatus.OK.value())
                    .data(null)
                    .build();
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(response);
        }
        CommonResponse<VotingResponse> response = CommonResponse.<VotingResponse>builder()
                .message("data not found")
                .statusCode(HttpStatus.NOT_FOUND.value())
                .data(null)
                .build();
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getVotingById(@PathVariable String id) {
        Optional<VotingResponse> voting = votingService.findVotingById(id);
        CommonResponse<VotingResponse> response = CommonResponse.<VotingResponse>builder()
                .message("successfully get voting")
                .statusCode(HttpStatus.OK.value())
                .data(voting.get())
                .build();
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(response);
    }

    @GetMapping()
    public ResponseEntity<?> getAllVotings() {
        List<VotingResponse> votings = votingService.findAllVotings();
        CommonResponse<List<VotingResponse>> response = CommonResponse.<List<VotingResponse>>builder()
                .message("successfully get all voting")
                .statusCode(HttpStatus.OK.value())
                .data(votings)
                .build();
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(response);
    }
}
