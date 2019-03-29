package com.keycloak.accountservice.service;


import com.keycloak.accountservice.model.User;

import com.keycloak.accountservice.request.UserCredentials;
import org.springframework.http.ResponseEntity;
import sun.plugin.util.UserProfile;

import java.util.List;
import java.util.Map;

public interface KeycloakService {


    public ResponseEntity registerUser(User user);

    public void removeUser(String id);

    public String activateUser(Map<String, Object> request);

    public String getLastLoginTime(String userId);

    public void setEmailVerifiedTrue(String userId);

    public void setEmailVerifiedAsFalse(String userId);

    public String deactivateUser(String userId);

    public String getEmailVerifiedUpdatedFlag(String userId);

    public String getByRefreshToken(String refreshToken);

    public String getToken(UserCredentials userCredentials);

    public void updateUserProfile(String realmAdminCreds, String tenantId, String username, UserProfile userDetails);

    public void setEmailVerifiedUpdatedFlag(String userId, String flag);

    public boolean addUserLoginTime(String userId);

    public boolean enableUserAccount(String userId);

    public boolean addRoleToUser(String userId, String roleName);

    public boolean removeRoleFromUser(String userId, String roleName);

    public boolean doPasswordUpdate(String userId, String password);

    public boolean isEmailVerified(String userId);

    public void resetPassword(String newPassword, String userId);

    public void forgotPassword(String userId);

    public void logoutUser(String userId);

    public List<UserProfile> getUsersWithRole(String realmAdminCreds, String tenantId, String roleName);

    public List<UserProfile> findUser(String realmAdminCreds, String tenantId, String email, String userName);

}
