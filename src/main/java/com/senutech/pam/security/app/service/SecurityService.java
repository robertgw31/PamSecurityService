package com.senutech.pam.security.app.service;

import com.senutech.pam.security.app.exception.ExceptionDetail;
import com.senutech.pam.security.app.exception.PamException;
import com.senutech.pam.security.app.exception.ValidationError;
import com.senutech.pam.security.app.model.containers.AccountCreateRequest;
import com.senutech.pam.security.app.model.containers.AccountCreateResult;
import com.senutech.pam.security.app.model.domain.*;
import com.senutech.pam.security.app.repository.*;
import com.senutech.pam.security.app.util.*;
import com.senutech.pam.security.app.model.domain.*;
import com.senutech.pam.security.app.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.Instant;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class SecurityService {

    @Autowired
    private EmailService emailService;

    @Autowired
    private UserloginRepository userloginRepository;
    @Autowired
    private TranauditRepository tranauditRepository;
    @Autowired
    private UsersessionRepository usersessionRepository;
    @Autowired
    private UserrequestRepository userrequestRepository;
    @Autowired
    private AccountRepository accountRepository;

    public static final int  MAX_FAILED_LOGIN_ATTEMPTS = Constants.MAX_FAILED_LOGIN_ATTEMPTS;
    public static final String ACTIVE = UserLoginStatus.ACTIVE.toString();

    @Transactional
    public Tranaudit writeTranAuditForUserLoginId(UUID userLoginId) throws PamException {

        Tranaudit tranaudit = new Tranaudit();
        tranaudit.setId(UUID.randomUUID());
        tranaudit.setAudittimestamp(OffsetDateTime.now(ZoneOffset.UTC));
        tranaudit.setUserloginid(userLoginId);
        try {
            tranauditRepository.save(tranaudit);
        } catch(Exception e) {
            throw PamException.normalize("Could not save Tranaudit record",tranaudit,e);
        }
        return tranaudit;
    }

    @Transactional
    public Tranaudit writeTranAuditForUserRequestId(UUID userRequestId) throws PamException {
        Tranaudit tranaudit = new Tranaudit();
        tranaudit.setId(UUID.randomUUID());
        tranaudit.setAudittimestamp(OffsetDateTime.now(ZoneOffset.UTC));
        tranaudit.setLoginrequestid(userRequestId);
        try {
            tranauditRepository.save(tranaudit);
        } catch(Exception e) {
            throw PamException.normalize("Could not save Tranaudit record",tranaudit,e);
        }
        return tranaudit;
    }

    @Transactional
    public Usersession writeUserSession(UUID userLoginID, String clientMachine,
                                        String notes) throws PamException {
        Usersession usersession = new Usersession();
        usersession.setUserloginid(userLoginID);
        usersession.setClientmachine(clientMachine);
        usersession.setNotes(notes);
        usersession.setStarttime(OffsetDateTime.now(ZoneOffset.UTC));
        try {
            usersessionRepository.save(usersession);
        } catch(Exception e) {
            String message = String.format("Could not save userSession record for UserLoginid: %s",userLoginID.toString());
            throw PamException.normalize(message,usersession,e);
        }
        return usersession;
    }
    @Transactional
    public void logoutFromSession(UUID userSessionID) throws PamException {
        try {
            usersessionRepository.logout(userSessionID,OffsetDateTime.now(ZoneOffset.UTC));
        } catch(Exception e) {
            String message = String.format("Could not log out session for id: %s",userSessionID.toString());
            throw PamException.normalize(message,userSessionID,e);
        }
    }
    @Transactional
    public AccountCreateResult createAccount(AccountCreateRequest request) throws PamException {
        try {
            List<ValidationError> exceptionDetails = new ArrayList<ValidationError>();
            AccountCreateResult result = new AccountCreateResult();


            // basic validateions
            if (request.getUserLocalDateTime() == null)
                exceptionDetails.add(new ValidationError("AccountCreateRequest","UserLocalDateTime","Missing",""));
            if (request.getRequestRecieptTime() == null)
                exceptionDetails.add(new ValidationError("AccountCreateRequest","RequestRecieptTime","Missing",""));
            if (request.getAccountName() == null)
                exceptionDetails.add(new ValidationError("AccountCreateRequest","AccountName","Missing",""));
            else
                request.setAccountName(request.getAccountName().trim());
            if (request.getLoginFullName() == null)
                exceptionDetails.add(new ValidationError("AccountCreateRequest","LoginFullName","Missing",""));
            else
                request.setLoginFullName(request.getLoginFullName().trim());
            if (request.getLoginEmail() == null)
                exceptionDetails.add(new ValidationError("AccountCreateRequest","LoginEmail","Missing",""));
            else
                request.setLoginEmail(request.getLoginEmail().trim());
            if (!ValidateionUtil.validateEmailAddress(request.getLoginEmail()))
                exceptionDetails.add(new ValidationError("AccountCreateRequest","LoginEmail","Bad email format",request.getLoginEmail()));
            if (request.getLoginPassword() != null)
                request.setLoginPassword(request.getLoginPassword().trim());
            if (request.getLoginAuthProvider() != null)
                request.setLoginAuthProvider(request.getLoginAuthProvider().trim());
            if (request.getLoginPassword() == null && request.getLoginAuthProvider() == null)
                exceptionDetails.add(new ValidationError("AccountCreateRequest","LoginPassword","Missing",""));
            if (request.getIsoCountry() == null)
                exceptionDetails.add(new ValidationError("AccountCreateRequest","IsoCountry","Missing",""));
            else
                request.setIsoCountry(request.getIsoCountry().trim());
            if (request.getIsoLanguage() == null)
                exceptionDetails.add(new ValidationError("AccountCreateRequest","IsoLanguage","Missing",""));
            else
                request.setIsoLanguage(request.getIsoLanguage().trim());



            // data integrity validations
            if (request.getIsoCountry() != null && !ValidateionUtil.isValidISOCountry(request.getIsoCountry()))
                exceptionDetails.add(new ValidationError("AccountCreateRequest","IsoCountry","Unknown country code",request.getIsoCountry()));
            if (request.getIsoLanguage() != null && !ValidateionUtil.isValidISOLanguage(request.getIsoLanguage()))
                exceptionDetails.add(new ValidationError("AccountCreateRequest","IsoLanguage","Unknown language code",request.getIsoLanguage()));

            if (exceptionDetails.size() >0) {
                throw new PamException("Bad or missing information",exceptionDetails);
            }

            // check if user already exists
            if (userloginRepository.existsByEmail(request.getLoginEmail()))
                throw new PamException("Duplicate user creation attempt", String.format("A account user with email '%s' already exists", request.getLoginEmail()));


            UUID id = UUID.randomUUID();
            OffsetDateTime timestamp = OffsetDateTime.now(ZoneOffset.UTC);


            request.setOpenDateTime(timestamp); // override provided d
            request.setTimestamp(timestamp);
            request.setId(UUID.randomUUID());

            Tranaudit tranaudit = new Tranaudit();
            tranaudit.setId(id);
            tranaudit.setLoginrequestid(id);
            tranaudit.setUserloginid(id);
            tranaudit.setAudittimestamp(timestamp);

            Userlogin login = new Userlogin();
            login.setId(id);
            login.setAccountid(id);
            login.setAuthprovider(request.getLoginAuthProvider());
            login.setAuthproviderid(request.getLoginAuthProvider());
            login.setCreatetranauditid(id);
            login.setUpdatetranauditid(id);
            login.setAccountid(id);
            login.setFullname(request.getLoginFullName());
            login.setEmail(request.getLoginEmail());
            login.setImageurl(request.getLoginImageURL());
            login.setLastaccesstimestamp(timestamp);
            login.setFailedloginattempts(0);
            login.setNotes("Account owner");
            login.setStatus(UserLoginStatus.PENDING_EMAIL_VERIFICATION.toString());
            login.setStatustimestamp(timestamp);
            login.setRecversion(1L);
            login.setSortorder(1);

            Usersession usersession = new Usersession();
            usersession.setId(id);
            usersession.setUserloginid(id);
            usersession.setStarttime(timestamp);
            usersession.setAccountid(id);
            usersession.setIsactive(true);
            usersession.setWastimedout(false);
            usersession.setNotes("User's first session to create login and account");
            usersession.setStarttime(timestamp);

            usersession.setClientmachine(request.getClientMachine());

            Userrequest userrequest = new Userrequest();
            userrequest.setAccountid(id);
            userrequest.setId(id);
            userrequest.setRequesttype(RequestType.ACCOUNT_CREATION.toString());
            userrequest.setRequestdetails("Creating new account and login");
            userrequest.setUsersessionid(id);
            userrequest.setUserloginid(id);
            userrequest.setStarttime(request.getRequestRecieptTime());
            userrequest.setEndtime(timestamp);

            Account account = new Account();
            account.setId(id);
            account.setFriendlyname(request.getLoginFullName());
            account.setCreatetranauditid(id);
            account.setUpdatetranauditid(id);
            account.setIsocountry(request.getIsoCountry());
            account.setIsolanguage(request.getIsoLanguage());
            account.setOpendate(timestamp);
            account.setOwneruserloginid(id);
            account.setStatus(AccountStatus.PENDING_ACTIVATION.toString());
            account.setStatustimestamp(timestamp);
            account.setIsclosed(false);
            account.setRecversion(1L);
            account.setSortorder(1);
            try {
                tranauditRepository.save(tranaudit);
                usersessionRepository.save(usersession);
                userrequestRepository.save(userrequest);
                userloginRepository.save(login);
                accountRepository.save(account);
                emailService.sendEmailConfirmationMessage(login.getFullname(), login.getEmail(), request.getEmailVerificationUrlRoot());
            } catch (Exception e) {
                throw PamException.normalize("Internal error saving user account information", e);
            }

            result.setAccount(account);
            result.setUserlogin(login);
            result.setTranaudit(tranaudit);
            result.setUserrequest(userrequest);
            result.setUsersession(usersession);

            return result;
        } catch(Exception e) {
            throw PamException.normalize(String.format("Exception creating account: %s",e.getMessage()),e);
        }
    }

    public void validateUserLoginEmail(String loginEmail, OffsetDateTime statusTimestamp) throws PamException {
        UUID id = UUID.randomUUID();
        OffsetDateTime  timestamp = OffsetDateTime.now(ZoneOffset.UTC);
        if(!userloginRepository.existsByEmailAndStatusTimestamp(loginEmail,statusTimestamp)) {
            throw PamException.normalize("The user login email validation parameters do not match a user login record",loginEmail);
        }
        Userlogin login = userloginRepository.findByEmailNotClosed(loginEmail);
        if (login == null) {
            throw PamException.normalize("Login with provided email does not exist", loginEmail);
        }
        Tranaudit transaudit = new Tranaudit();
        transaudit.setId(id);
        transaudit.setAudittimestamp(timestamp);
        transaudit.setUserloginid(login.getId());
        userloginRepository.validateLoginEmail(login.getId(),ACTIVE, timestamp,transaudit.getId());
        // the following will activate an account only where it was newly created with the login creation
        accountRepository.activateAccountForPrimaryUserActivation(login.getId(),timestamp,transaudit.getId());
    }
}
