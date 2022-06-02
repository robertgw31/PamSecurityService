package com.senutech.pam.security.app.model.containers;

import com.senutech.pam.security.app.model.domain.*;
import com.senutech.pam.security.app.model.domain.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AccountCreateResult {

    private Account account;
    private Userlogin userlogin;
    private Usersession usersession;
    private Userrequest userrequest;
    private Tranaudit tranaudit;
}
