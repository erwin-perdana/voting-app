package com.voting.voting_app.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RegisterRequest {
    @NotBlank(message = "name is required")
    public String name;
    @NotBlank(message = "username is required")
    @Pattern(regexp = "^[A-Za-z0-9]+$", message = "invalid username")
    @Size(min = 6, max = 16, message = "must be greater than 6 character and less than 17 character")
    private String username;
    @NotBlank(message = "password is required")
    @Size(min = 6, message = "must be greater than 6 character")
    private String password;
}
