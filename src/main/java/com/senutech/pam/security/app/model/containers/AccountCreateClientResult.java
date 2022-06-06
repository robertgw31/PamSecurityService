package com.senutech.pam.security.app.model.containers;

import com.senutech.pam.security.app.controller.response.ApiResponseBase;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter


public class AccountCreateClientResult extends ApiResponseBase {
    private UUID accountId;
    private UUID userId;

    public AccountCreateClientResult() {
        super(true);
    }
    public AccountCreateClientResult(AccountCreateResult fullResult) {
        super(true);
        this.accountId = fullResult.getAccount().getId();
        this.userId = fullResult.getUserlogin().getId();
    }
}
