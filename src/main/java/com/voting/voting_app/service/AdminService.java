package com.voting.voting_app.service;

import com.voting.voting_app.dto.request.UpdateAdminRequest;
import com.voting.voting_app.dto.response.AdminResponse;
import com.voting.voting_app.entity.Admin;

public interface AdminService {
    void createNew(Admin admin);
}
