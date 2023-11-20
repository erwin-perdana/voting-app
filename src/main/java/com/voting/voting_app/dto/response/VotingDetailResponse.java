package com.voting.voting_app.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class VotingDetailResponse {
    private String votingDetailId;
    private String votingId;
    private String name;
    private String description;
}
