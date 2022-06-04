package com.senutech.pam.security.app.model.containers;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AccountCreateClientResult {
    private UUID accountId;
    private UUID userId;

    public AccountCreateClientResult(AccountCreateResult fullResult) {
        this.accountId = fullResult.getAccount().getId();
        this.userId = fullResult.getUserlogin().getId();
    }
}
