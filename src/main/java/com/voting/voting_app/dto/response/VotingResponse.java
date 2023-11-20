package com.voting.voting_app.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class VotingResponse {
    private String votingId;
    private String votingDate;
    private List<VotingDetailResponse> votingDetails;
}
