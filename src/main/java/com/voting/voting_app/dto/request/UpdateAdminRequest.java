package com.voting.voting_app.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UpdateAdminRequest {
    @NotBlank(message = "id is required")
    private String id;
    @NotBlank(message = "name is required")
    private String name;
}
