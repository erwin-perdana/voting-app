package com.voting.voting_app.dto.request;

import com.voting.voting_app.entity.VotingDetail;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class VotingRequest {
    @NotNull(message = "order details is required")
    private List<VotingDetailRequest> votingDetails;
}
