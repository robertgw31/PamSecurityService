package com.senutech.pam.security.app.service;

import com.senutech.pam.security.app.exception.PamException;
import com.senutech.pam.security.app.exception.ValidationError;
import com.senutech.pam.security.app.model.container.AccountCreateRequest;
import com.senutech.pam.security.app.model.container.AccountCreateResult;
import com.senutech.pam.security.app.model.domain.*;
import com.senutech.pam.security.app.repository.*;
import com.senutech.pam.security.app.util.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.encrypt.Encryptors;
import org.springframework.security.crypto.keygen.KeyGenerators;
import org.springframework.stereotype.Service;

import org.springframework.security.crypto.encrypt.TextEncryptor;

import javax.transaction.Transactional;
import java.time.Duration;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.time.temporal.TemporalUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
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

    public static final int MAX_MINUTES_FOR_EMAIL_VERIFICATION = Constants.MAX_MINUTES_FOR_EMAIL_VERIFICATION;

    private static String ENCRYPTION_KEY = KeyGenerators.string().generateKey();
    private static String ENCRYPTION_PASSWORD = "Max";
    private TextEncryptor encryptor = Encryptors.text(ENCRYPTION_PASSWORD,ENCRYPTION_KEY);

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
            String token = UUID.randomUUID().toString();
            String e = encryptor.encrypt(request.getLoginEmail());
            String t = encryptor.encrypt(token);
            String emailVerificationUrlBase = request.getEmailVerificationUrl();
            String verificationUrl = String.format("%s?e=%s&t=%s",emailVerificationUrlBase,e,t);



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
           // login.setEmailVerificationToken(t);
            login.setImageurl(request.getLoginImageURL());
            login.setLastaccesstimestamp(timestamp);
            login.setFailedloginattempts(0);
            login.setNotes("Account owner");
            login.setStatus(UserLoginStatus.PENDING_EMAIL_VERIFICATION.toString());
            login.setStatustimestamp(timestamp);
            login.setRecversion(1L);
            login.setSortorder(1);
            login.setIsolanguage("en" );
            login.setIsocountrycode("us");

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

                emailService.sendEmailConfirmationMessage(login.getFullname(), login.getEmail(), verificationUrl);
            } catch (Exception ex) {
                throw PamException.normalize("Internal error saving user account information", ex);
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

    public void validateUserLoginEmail(String e, String t) throws PamException {
        if (e == null) {
            throw PamException.normalize("'e' paramter missing",null);
        }
        if (t == null) {
            throw PamException.normalize("t parameter missing",null);
        }
        UUID id = UUID.randomUUID();
        OffsetDateTime  nowTimestamp = OffsetDateTime.now(ZoneOffset.UTC);
        String email = encryptor.decrypt(e);
        String token = encryptor.decrypt(t);
        Userlogin userlogin = userloginRepository.findByEmailNotClosed(email);
        if(userlogin == null) {
            throw PamException.normalize("An activation was attempted for an unkknown email",null);
        }
        if (!Constants.WEB_APP_PATH_EMAIL_VERIFICTION.equals(userlogin.getStatus())) {
            // we treat this as a no-op
            return;
        }
        String dbToken = userlogin.getEmailVerificationToken();
        if (dbToken == null || !dbToken.equals(token)) {
            throw PamException.normalize("t parameter invalid",null);
        }
        OffsetDateTime statusTimestamp = userlogin.getStatustimestamp();
        long minutes = Duration.between(statusTimestamp,nowTimestamp).toMinutes();
        if (minutes > Constants.MAX_MINUTES_FOR_EMAIL_VERIFICATION) {
            String message = String.format("Email verification must be completed within %d minutes",minutes);
            throw PamException.normalize(message,null);
        }



        Tranaudit transaudit = new Tranaudit();
        transaudit.setId(id);
        transaudit.setAudittimestamp(nowTimestamp);
        transaudit.setUserloginid(userlogin.getId());
        userlogin.setEmailVerificationToken(null);
        userlogin.setStatus(Constants.USER_LOGON_STATUS_ACTIVE);
        userlogin.setStatustimestamp(nowTimestamp);
        userlogin.setUpdatetranauditid(transaudit.getId());
        try {
            tranauditRepository.save(transaudit);
            userloginRepository.save(userlogin);
        } catch (Exception ex) {
            throw PamException.normalize("Internal error updating user account for email verification", ex);
        }

    }


}
