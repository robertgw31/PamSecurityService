package com.senutech.pam.security.app.service;

import com.senutech.pam.security.app.exception.PamException;
import com.senutech.pam.security.app.model.auth0.*;
import com.senutech.pam.security.app.util.PamConstants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;

@Service
public class Auth0Service {

    private static final Logger logger = LoggerFactory.getLogger(Auth0Service.class);

    @Autowired
    private RestTemplate restTemplate;

    @Value("${auth0.management.url}")
    private String API_BASE;

    @Value("${auth0.management.api}")
    private String MANAGEMENT_API_BASE;

    @Value("${auth0.management.clientId}")
    private String CLIENT_ID;

    @Value("${auth0.management.clientSecret}")
    private String CLIENT_SECRET;

    @Value("${auth0.management.audience}")
    private String AUDIENCE;

    private static final String TOKEN_REQUEST_GRANT_TYPE = "client_credentials";


    private Auth0AccessPass auth0AccessPass;
    private String getAccessToken() throws PamException {
        try {
            if (auth0AccessPass == null || auth0AccessPass.isExpired()) {
                fetchAccessPass();
            }
            return auth0AccessPass.getAccessToken();
        } catch(Exception e) {
            throw PamException.normalize("Error in resetAccessToken",e);
        }
    }

    public Auth0ApiResponse getJobStatus(String jobId) throws PamException {
        // jobs/{id}
        try {
            String accessToken = getAccessToken();
            final String requestURL = String.format("%s/jobs/%s", MANAGEMENT_API_BASE,jobId);

            HttpHeaders headers = new HttpHeaders();
            headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
            headers.add("User-Agent", "PAM Security Microservice" );  // value can be whatever
            headers.setBearerAuth(accessToken);
            //headers.add("Authorization", "Bearer "+ auth0AccessToken.getAccessToken() );
            ResponseEntity<Auth0ApiResponse> response = restTemplate.exchange(requestURL,
                    HttpMethod.GET,
                    new HttpEntity<>(headers),
                    Auth0ApiResponse.class);
            Auth0ApiResponse jobstatus = response.getBody();
            return jobstatus;

        } catch(Exception e) {
            throw PamException.normalize("Error in getJobStatus",e);
        }

    }

    public Auth0User getUserByEmail(String email) throws PamException {
        try {
            String accessToken = getAccessToken();

            final String requestURL = String.format("%s/users-by-email?email=%s", MANAGEMENT_API_BASE,email);
            HttpHeaders headers = new HttpHeaders();
            headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
            headers.add("User-Agent", "PAM Security Microservice" );  // value can be whatever
            headers.setBearerAuth(accessToken);
            //headers.add("Authorization", "Bearer "+ auth0AccessToken.getAccessToken() );
            ResponseEntity<Auth0User[]> response = restTemplate.exchange(requestURL,
                    HttpMethod.GET,
                    new HttpEntity<>(headers),
                    Auth0User[].class);
            Auth0User[] userList = response.getBody();
            if (userList.length == 0) {
                return null;
            }
            return userList[0];
        } catch(Exception e) {
            throw PamException.normalize("Error in getUserByEmail",e);
        }
    }

    public Auth0ApiResponse resendVerificationEmail(String auth0UserId) throws PamException {
        try {
            String accessToken = getAccessToken();
            final String requestURL = String.format("%s/jobs/verification-email", MANAGEMENT_API_BASE);
            String requestBody = String.format("{\"user_id: \"%s\" }",auth0UserId);
            Auth0UserId userIdStruct = new Auth0UserId();
            userIdStruct.setUserId(auth0UserId);
            HttpHeaders headers = new HttpHeaders();
            headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
            headers.add("User-Agent", "PAM Security Microservice" );  // value can be whatever
            headers.setBearerAuth(accessToken);
            ResponseEntity<Auth0ApiResponse> res = restTemplate.exchange(requestURL,
                    HttpMethod.POST,
                    new HttpEntity<Auth0UserId>(userIdStruct,headers),
                    Auth0ApiResponse.class);
            Auth0ApiResponse response = res.getBody();
            boolean isSuccesful = isOkAsyncJobStatus(response.getStatus());
            logger.info(String.format("Request to resend verification email to user '%s' resulted in a status of %s, which is considered %s",
                    auth0UserId,response.getStatus(),
                    isSuccesful ? "not a failure" : "a failure"));
            String status = response.getStatus();

            return response;

        } catch(Exception e) {
            throw PamException.normalize("Error in resendVerificationEmail",e);
        }
    }



    public void fetchAccessPass() throws PamException {
        try {
            final String requestURL = API_BASE + "/oauth/token";
            Auth0AccessPassRequest request = new Auth0AccessPassRequest();
            request.setAudience(AUDIENCE);
            request.setClientId(CLIENT_ID);
            request.setClientSecret(CLIENT_SECRET);
            request.setGrantType(TOKEN_REQUEST_GRANT_TYPE);
            ResponseEntity<Auth0AccessPass> response  = restTemplate.postForEntity(requestURL,request, Auth0AccessPass.class);
            if(response.getStatusCodeValue() == 200) {
                auth0AccessPass = response.getBody();
            } else {
                throw new PamException(String.format("Failure to retrieve token: %d - %s",response.getStatusCode().value(), response.getStatusCode().getReasonPhrase()));
            }
        } catch(Exception e) {
            throw PamException.normalize("Error retrieving Auth0 token",e);
        }
    }

    public boolean isOkAsyncJobStatus(String status) {
        if(PamConstants.AUTH0_API_OP_STATUS_COMPLETED.equals(status) ||
                PamConstants.AUTH0_API_OP_STATUS_PENDING.equals(status)) {
            return true;
        } else {
            return false;
        }
    }

}
