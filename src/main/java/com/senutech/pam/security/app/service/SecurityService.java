package com.senutech.pam.security.app.service;

import com.senutech.pam.security.app.exception.PamException;
import com.senutech.pam.security.app.exception.ValidationError;
import com.senutech.pam.security.app.model.auth0.Auth0User;
import com.senutech.pam.security.app.model.auth0.Auth0UserIdentity;
import com.senutech.pam.security.app.model.auth0.GeoIp;
import com.senutech.pam.security.app.model.container.*;
import com.senutech.pam.security.app.model.domain.*;
import com.senutech.pam.security.app.repository.*;
import com.senutech.pam.security.app.util.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.encrypt.Encryptors;
import org.springframework.stereotype.Service;

import org.springframework.security.crypto.encrypt.TextEncryptor;

import javax.transaction.Transactional;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.apache.commons.text.WordUtils;

@Service
public class SecurityService {

    @Autowired
    private EmailService emailService;

    @Autowired
    private Auth0Service auth0Service;

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

    public static final int  MAX_FAILED_LOGIN_ATTEMPTS = PamConstants.MAX_FAILED_LOGIN_ATTEMPTS;
    public static final String ACTIVE = UserLoginStatus.ACTIVE.toString();

    public static final int MAX_MINUTES_FOR_EMAIL_VERIFICATION = PamConstants.MAX_MINUTES_FOR_EMAIL_VERIFICATION;

    private static String ENCRYPTION_KEY = "ABCABC";
    private static String ENCRYPTION_PASSWORD = "Max";
    private TextEncryptor encryptor = Encryptors.text(ENCRYPTION_PASSWORD,ENCRYPTION_KEY);

    @Transactional
    public Tranaudit writeTranAuditForUserLoginId(UUID userLoginId) throws PamException {

        Tranaudit tranaudit = new Tranaudit();
        tranaudit.setId(UUID.randomUUID());
        tranaudit.setAudittimestamp(OffsetDateTime.now(ZoneOffset.UTC));
        tranaudit.setUserloginid(userLoginId);
        try {
            tranauditRepository.saveAndFlush(tranaudit);
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
            tranauditRepository.saveAndFlush(tranaudit);
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
            usersessionRepository.saveAndFlush(usersession);
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

            // go to auth0 to get additional information
            Auth0User auth0User = auth0Service.getUserByEmail(request.getLoginEmail());
            Auth0UserIdentity auth0UserIdentity = auth0User.getIdentities().get(0);
            GeoIp geoIp = null;
            if (auth0User.getMetaData() != null && auth0User.getMetaData().getGeoIp() != null) {
                geoIp = auth0User.getMetaData().getGeoIp();
            }


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
            login.setAuthprovider(auth0UserIdentity.getProvider());
            login.setAuthProviderConnection(auth0UserIdentity.getConnection());
            login.setSocial(auth0UserIdentity.isSocial());
            login.setAuthProviderUserId(auth0User.getUserId());
            login.setCreatetranauditid(id);
            login.setUpdatetranauditid(id);
            login.setAccountid(id);
            login.setFullname(request.getLoginFullName());
            login.setEmail(request.getLoginEmail());
            login.setEmailVerificationToken(token);
            login.setEmailverified(true);
            login.setImageurl(request.getLoginImageURL());
            login.setLastaccesstimestamp(timestamp);
            login.setFailedloginattempts(0);
            login.setNotes("Account owner");
            login.setStatus(UserLoginStatus.ACTIVE.toString());
            login.setStatustimestamp(timestamp);
            login.setRecversion(1L);
            login.setSortorder(1);
            login.setIsolanguage("en" );
            if (geoIp != null) {
                login.setIsocountrycode(geoIp.getContinentCode());
            }

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
            account.setStatus(AccountStatus.ACTIVE.toString());
            account.setStatustimestamp(timestamp);
            account.setIsclosed(false);
            account.setRecversion(1L);
            account.setSortorder(1);
            try {
                tranauditRepository.saveAndFlush(tranaudit);
                usersessionRepository.saveAndFlush(usersession);
                userrequestRepository.saveAndFlush(userrequest);
                userloginRepository.saveAndFlush(login);
                accountRepository.saveAndFlush(account);

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
    @Transactional
    public VerifyEmailResponse validateUserLoginEmail(VerifyEmailRequest request) throws PamException {
        if(request == null || request.getE() == null || request.getT() == null) {
            throw PamException.normalize("Parameter missing",null);
        }

        String e = request.getE();
        VerifyEmailResponse response = new VerifyEmailResponse();
        Userlogin userlogin = userloginRepository.findByEmailNotClosed(e);
        if(userlogin == null) {
            response.setExists(false);
            response.setSuccess(false);
            response.setMessage(String.format("An account has not yet been set up for email %s",e));
        } else {
            response.setSuccess(true);
            response.setExists(true);
            response.setMessage(String.format("An account exists for email %s", e));
        }
        return response;

    }

    @Transactional
    public UserLogonResponse doLoginByEmail(UserLogonRequest request) throws PamException {
        try {
            UUID id = UUID.randomUUID();
            OffsetDateTime  nowTimestamp = OffsetDateTime.now(ZoneOffset.UTC);

            UserLogonResponse res = new UserLogonResponse();

            if (request.getEmail() == null || !ValidateionUtil.validateEmailAddress(request.getEmail())) {
                throw PamException.normalize("Invalid email",null);
            }
            Userlogin userlogin = userloginRepository.findByEmailNotClosed(request.getEmail());

            if (userlogin == null) {
                res.setLogonExists(false);
                return res;
                // this is a first-time logon so we will create the account
//                UUID createID = UUID.randomUUID();
//                String fullName = request.getNickName();
//                if (request.getGivenName() != null && request.getGivenName().length() > 0
//                        && request.getFamilyName() != null && request.getFamilyName().length() > 0) {
//                    fullName = String.format("%s %s",request.getGivenName(), request.getFamilyName());
//                }
//
//                AccountCreateRequest acctCreate = new AccountCreateRequest();
//                acctCreate.setLoginEmail(request.getEmail());
//                acctCreate.setOpenDateTime(nowTimestamp);
//                acctCreate.setClientMachine(request.getClientMachine());
//                acctCreate.setAccountName(fullName);
//                acctCreate.setLoginFullName(fullName);
//                acctCreate.setUserLocalDateTime(OffsetDateTime.now());
//                acctCreate.setRequestRecieptTime(OffsetDateTime.now());
//                acctCreate.setTimestamp(nowTimestamp);
//                acctCreate.setIsoLanguage("en");
//                acctCreate.setIsoCountry("us");
//                acctCreate.setLoginAuthProvider("AUTH0");
//                try {
//                    AccountCreateResult acctCreateResult = createAccount(acctCreate);
//                    userlogin = acctCreateResult.getUserlogin();
//                } catch(Exception xx) {
//                    throw PamException.normalize(String.format("Could not find a user with email %s", request.getEmail()), null);
//                }
            }
            userlogin = userloginRepository.findByEmailActive(request.getEmail());
            Tranaudit transaudit = new Tranaudit();
            transaudit.setId(id);
            transaudit.setAudittimestamp(nowTimestamp);
            transaudit.setUserloginid(userlogin.getId());

            Usersession userSession = new Usersession();
            userSession.setId(id);
            userSession.setStarttime(nowTimestamp);
            userSession.setAccountid(userlogin.getAccountid());
            userSession.setUserloginid(userlogin.getId());
            userSession.setIsactive(true);
            userSession.setClientmachine(request.getClientMachine());


            Userrequest userRequest = new Userrequest();
            userRequest.setId(id);
            userRequest.setUserloginid(userlogin.getId());
            userRequest.setStarttime(nowTimestamp);
            userRequest.setAccountid(userlogin.getAccountid());
            userRequest.setEndtime(nowTimestamp);
            userRequest.setRequesttype(RequestType.USER_LOGON.toString());
            userRequest.setUsersessionid(userSession.getId());
            userRequest.setRequestdetails("User Logon");

            String nickName = request.getNickName();
            if (nickName != null && !nickName.equals(userlogin.getNickname())) {
                userlogin.setNickname(nickName);
            }
            String givenName = request.getGivenName();
            String familyName = request.getFamilyName();
            String fullName = null;
            if (givenName != null && !givenName.isEmpty()
            && familyName != null && !familyName.isEmpty()) {
                fullName = WordUtils.capitalize(String.format("%s %s",givenName,familyName));
            }
            if (fullName == null) {
                fullName = givenName;
            }
            if (fullName != null && !fullName.equals(userlogin.getFullname())) {
                userlogin.setFullname(fullName);
            }
            if (fullName == null)

            userlogin.setUpdatetranauditid(transaudit.getId());
            userlogin.setLastaccesstimestamp(nowTimestamp);
            userlogin.setUpdatetranauditid(transaudit.getId());
            try {
                tranauditRepository.saveAndFlush(transaudit);
                userrequestRepository.saveAndFlush(userRequest);
                usersessionRepository.saveAndFlush(userSession);
                userloginRepository.saveAndFlush(userlogin);
            } catch (Exception ex) {
                throw PamException.normalize("Internal error updating user account for email logon", ex);
            }

            res.setUserLogin(userlogin);
            res.setSessionId(userSession.getId().toString());
            res.setAccessToken(request.getAccessToken());
            return res;

        } catch (Exception ex) {
            throw PamException.normalize("Internal error updating user account for email verification", ex);
        }
    }


}
