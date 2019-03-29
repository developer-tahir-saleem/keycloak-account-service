package com.keycloak.accountservice.service.implementation;


import com.keycloak.accountservice.constant.JsonKey;
import com.keycloak.accountservice.enums.RequiredAction;
import com.keycloak.accountservice.exception.ResourceNotFoundException;
import com.keycloak.accountservice.model.User;
import com.keycloak.accountservice.request.UserCredentials;
import com.keycloak.accountservice.response.ResponseRegister;
import com.keycloak.accountservice.service.KeycloakService;
import com.keycloak.accountservice.service.UserService;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.message.BasicNameValuePair;
import org.keycloak.admin.client.Keycloak;
import org.keycloak.admin.client.resource.RealmResource;
import org.keycloak.admin.client.resource.RoleResource;
import org.keycloak.admin.client.resource.UserResource;
import org.keycloak.admin.client.resource.UsersResource;
import org.keycloak.representations.idm.CredentialRepresentation;
import org.keycloak.representations.idm.RoleRepresentation;
import org.keycloak.representations.idm.UserRepresentation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import sun.plugin.util.UserProfile;

import javax.ws.rs.core.Response;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

@Service("keycloakService")
public class KeycloakServiceImpl implements KeycloakService {


    @Autowired
    private UserService userService;


    @Value("${keycloak.credentials.secret}")
    private String SECRETKEY;

    @Value("${keycloak.resource}")
    private String CLIENTID;

    @Value("${keycloak.auth-server-url}")
    private String AUTHURL;

    @Value("${keycloak.realm}")
    private String REALM;

    private final Keycloak keycloak;

    @Autowired
    public KeycloakServiceImpl(Keycloak keycloak) {
        this.keycloak = keycloak;
    }

    public RealmResource getRealmResource() {
        return keycloak.realm("ras-app");
    }

    private CredentialRepresentation createCredential(String cred) {
        CredentialRepresentation credentialRepresentation = new
                CredentialRepresentation();
        credentialRepresentation.setType(CredentialRepresentation.PASSWORD);
        credentialRepresentation.setValue(cred);
        credentialRepresentation.setTemporary(false);

        return credentialRepresentation;
    }

    private String sendPost(List<NameValuePair> urlParameters) throws Exception {

        HttpClient client = HttpClientBuilder.create().build();
        HttpPost post = new HttpPost(AUTHURL + "/realms/" + REALM + "/protocol/openid-connect/token");

        post.setEntity(new UrlEncodedFormEntity(urlParameters));

        HttpResponse response = client.execute(post);

        BufferedReader rd = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));

        StringBuffer result = new StringBuffer();
        String line = "";
        while ((line = rd.readLine()) != null) {
            result.append(line);
        }

        return result.toString();

    }


    @Override
    public ResponseEntity registerUser(User userDTO) {
        ResponseRegister responseRegister = new ResponseRegister();

        int statusId = 0;
        try {

            UsersResource userRessource = getRealmResource().users();

            UserRepresentation user = new UserRepresentation();
            user.setUsername(userDTO.getEmail());
            user.setEmail(userDTO.getEmail());
            user.setRequiredActions(Arrays.asList(RequiredAction.VERIFY_EMAIL.toString()));
            // Define password credential
            // Set password credential
            user.setCredentials(Arrays.asList(createCredential(userDTO.getPassword())));
            user.setEnabled(true);
            user.setEmailVerified(true);
//            user.set


            // Create user
            Response result = userRessource.create(user);
            System.out.println("Keycloak create user response code>>>>" + result.getStatus());

            statusId = result.getStatus();

            if (statusId == 201) {

                String userId = result.getLocation().getPath().replaceAll(".*/([^/]+)$", "$1");


                userDTO.setAuthId(userId);
                responseRegister.setUser(userService.save(userDTO));
                responseRegister.setStatus(true);

                asyncMethodWithVoidReturnType(userId);


//                // Define password credential
//                CredentialRepresentation passwordCred = new CredentialRepresentation();
//                passwordCred.setTemporary(false);
//                passwordCred.setType(CredentialRepresentation.PASSWORD);
//                passwordCred.setValue("test");
//
//                // Set password credential
//                userRessource.get(userId).resetPassword(passwordCred);


                responseRegister.setMessage("" + userDTO.getEmail() + " created successfully");
                System.out.println("User created with userId:" + userId);

                System.out.println("Username==" + userDTO.getEmail() + " created in keycloak successfully");

            } else if (statusId == 409) {
                System.out.println("Username==" + userDTO.getEmail() + " already present in keycloak");
                responseRegister.setMessage("" + userDTO.getEmail() + " already present");

            } else {
                responseRegister.setMessage("" + userDTO.getEmail() + " could not be created");

                System.out.println("Username==" + userDTO.getEmail() + " could not be created in keycloak");
            }

        } catch (Exception e) {
            e.printStackTrace();

        }
//        userDTO.se
//        BeanUtils.copyProperties(userDTO, responseRegister);


//        int statusId = 0;
//        try {
//
//            UsersResource userRessource = getRealmResource().users();
//
//            UserRepresentation user = new UserRepresentation();
//            user.setUsername(userDTO.getEmail());
//            user.setEmail(userDTO.getEmail());
//            user.setFirstName(userDTO.getEmail());
//            user.setLastName(userDTO.getEmail());
//            user.setEnabled(true);
//
//            // Create user
//            Response result = userRessource.create(user);
//            System.out.println("Keycloak create user response code>>>>" + result.getStatus());
//
//            statusId = result.getStatus();
//
//            if (statusId == 201) {
//
//                String userId = result.getLocation().getPath().replaceAll(".*/([^/]+)$", "$1");
//
//                System.out.println("User created with userId:" + userId);
//
//                // Define password credential
//                CredentialRepresentation passwordCred = new CredentialRepresentation();
//                passwordCred.setTemporary(false);
//                passwordCred.setType(CredentialRepresentation.PASSWORD);
//                passwordCred.setValue(userDTO.getPassword());
//
//                // Set password credential
//                userRessource.get(userId).resetPassword(passwordCred);
//
//                // set role
////                RealmResource realmResource = getRealmResource();
////                RoleRepresentation savedRoleRepresentation = realmResource.roles().get("user").toRepresentation();
////                realmResource.users().get(userId).roles().realmLevel().add(Arrays.asList(savedRoleRepresentation));
//
//                System.out.println("Username==" + userDTO.getEmail() + " created in keycloak successfully");
//
//            }
//
//            else if (statusId == 409) {
//                System.out.println("Username==" + userDTO.getEmail() + " already present in keycloak");
//
//            } else {
//                System.out.println("Username==" + userDTO.getEmail() + " could not be created in keycloak");
//
//            }
//
//        } catch (Exception e) {
//            e.printStackTrace();
//
//        }



        return ResponseEntity.status(statusId).body(responseRegister);
    }

    @Async
    public void asyncMethodWithVoidReturnType(String userId) {

        UserResource userItem = getRealmResource().users().get(userId);
//                 Send Verify Email
        userItem.sendVerifyEmail();
        System.out.println("Execute method asynchronously. "
                + Thread.currentThread().getName());
    }


    @Override
    public void removeUser(String userId) {

        UserResource resource = getRealmResource().users().get(userId);


        if (resource != null) {
            try {
                resource.remove();
            } catch (Exception ex) {
                throw new ResourceNotFoundException(ex.getMessage(), "user not found",
                        userId);
            }

        }
    }

    @Override
    public String activateUser(Map<String, Object> request) {
        return null;
    }

    @Override
    public String getLastLoginTime(String userId) {
        UsersResource userResource = getRealmResource().users();

        String lastLoginTime = null;
        try {
            UserResource resource =
                    userResource.get(userId);
            UserRepresentation ur = resource.toRepresentation();
            Map<String, List<String>> map = ur.getAttributes();
            if (map == null) {
                map = new HashMap<>();
            }
            List<String> list = map.get(JsonKey.LAST_LOGIN_TIME);
            if (list != null && !list.isEmpty()) {
                lastLoginTime = list.get(0);
            }
        } catch (Exception ex) {
            throw new ResourceNotFoundException(ex.getMessage(), "user not found",
                    userId);
        }
        return lastLoginTime;
    }

    @Override
    public void setEmailVerifiedTrue(String userId) {
        UsersResource userResource = getRealmResource().users();

        try {
            UserResource resource =
                    userResource.get(userId);
            UserRepresentation ur = resource.toRepresentation();
            ur.setEmailVerified(false);
            if (resource != null) {
                try {
                    resource.update(ur);
                } catch (Exception ex) {
                    throw new ResourceNotFoundException("Resource Not Found",
                            ex.getMessage(),
                            ex.getCause());
                }

            }
        } catch (Exception ex) {
            throw new ResourceNotFoundException("Resource Not Found",
                    ex.getMessage(),
                    ex.getCause());
        }
    }

    @Override
    public void setEmailVerifiedAsFalse(String userId) {
        UsersResource userResource = getRealmResource().users();

        try {
            UserResource resource =
                    userResource.get(userId);
            UserRepresentation ur = resource.toRepresentation();
            ur.setEmailVerified(false);
            if (resource != null) {
                try {
                    resource.update(ur);
                } catch (Exception ex) {
                    throw new ResourceNotFoundException("Resource Not Found",
                            ex.getMessage(),
                            ex.getCause());
                }
            }
        } catch (Exception ex) {
            throw new ResourceNotFoundException("Resource Not Found",
                    ex.getMessage(),
                    ex.getCause());
        }
    }

    @Override
    public String getEmailVerifiedUpdatedFlag(String userId) {
        return null;
    }


    @Override
    public String getByRefreshToken(String refreshToken) {
        String responseToken = null;
        try {

            List<NameValuePair> urlParameters = new ArrayList<NameValuePair>();
            urlParameters.add(new BasicNameValuePair("grant_type", "refresh_token"));
            urlParameters.add(new BasicNameValuePair("client_id", CLIENTID));
            urlParameters.add(new BasicNameValuePair("refresh_token", refreshToken));
            urlParameters.add(new BasicNameValuePair("client_secret", SECRETKEY));

            responseToken = sendPost(urlParameters);

        } catch (Exception e) {
            e.printStackTrace();

        }

        return responseToken;
    }

    @Override
    public String getToken(UserCredentials userCredentials) {
        String responseToken = null;
        try {

            String username = userCredentials.getEmail();

            List<NameValuePair> urlParameters = new ArrayList<NameValuePair>();
            urlParameters.add(new BasicNameValuePair("grant_type", "password"));
            urlParameters.add(new BasicNameValuePair("client_id", CLIENTID));
            urlParameters.add(new BasicNameValuePair("username", username));
            urlParameters.add(new BasicNameValuePair("password", userCredentials.getPassword()));
            urlParameters.add(new BasicNameValuePair("client_secret", SECRETKEY));

            responseToken = sendPost(urlParameters);

        } catch (Exception e) {
            e.printStackTrace();
        }

        return responseToken;
    }

    @Override
    public void updateUserProfile(String realmAdminCreds, String tenantId, String username, UserProfile userDetails) {

    }

    @Override
    public void setEmailVerifiedUpdatedFlag(String userId, String flag) {

    }

    @Override
    public boolean addUserLoginTime(String userId) {
        return false;
    }

    @Override
    public String deactivateUser(String userId) {
        UsersResource userResource = getRealmResource().users();

        try {

            UserResource resource =
                    userResource.get(userId);
            UserRepresentation ur = resource.toRepresentation();
            ur.setEnabled(false);
            if (resource != null) {
                try {
                    resource.update(ur);
                } catch (Exception ex) {
                    throw new ResourceNotFoundException("Resource Not Found",
                            ex.getMessage(),
                            ex.getCause());
                }

            }
        } catch (Exception ex) {
            throw new ResourceNotFoundException("Resource Not Found",
                    ex.getMessage(),
                    ex.getCause());
        }
        return JsonKey.SUCCESS;
    }

    @Override
    public boolean enableUserAccount(String userId) {

        try {
            UserResource userResource = getRealmResource().users().get(userId);
            UserRepresentation profile = userResource.toRepresentation();
            profile.setEnabled(true);
            // We require that a user verify their email before enabling the account
            profile.setEmailVerified(true);
            userResource.update(profile);
            return true;
        } catch (Exception ex) {
            throw new ResourceNotFoundException("Resource Not Found",
                    ex.getMessage(),
                    ex.getCause());
        } finally {
            return false;
        }
    }

    @Override
    public boolean addRoleToUser(String userId, String roleName) {
        UsersResource userResource = getRealmResource().users();

        try {

            UserResource retrievedUser = userResource.get(userId);

            // Add user to the role
            RoleResource roleResource = getRealmResource().roles().get(roleName);
            retrievedUser.roles().realmLevel().add(Arrays.asList(roleResource.toRepresentation()));
            return true;
        } catch (Exception ex) {

            throw new ResourceNotFoundException("Error getting values from property file, reason:",
                    ex.getMessage(),
                    ex.getCause());

        } finally {
            return false;
        }
    }

    @Override
    public boolean removeRoleFromUser(String userId, String roleName) {
        UsersResource userResource = getRealmResource().users();
        try {
            UserResource retrievedUser = userResource.get(userId);

            // Remove role from user
            RoleResource roleResource = getRealmResource().roles().get(roleName);
            retrievedUser.roles().realmLevel().remove(Arrays.asList(roleResource.toRepresentation()));
            return true;
        } catch (Exception ex) {
            throw new ResourceNotFoundException("Error getting values from property file, reason:",
                    ex.getMessage(),
                    ex.getCause());
        } finally {
            return false;
        }
    }

    @Override
    public boolean doPasswordUpdate(String userId, String password) {
        return false;
    }

    @Override
    public boolean isEmailVerified(String userId) {
        return false;
    }

    @Override
    public void resetPassword(String newPassword, String userId) {
        UsersResource userResource = getRealmResource().users();

        // Define password credential
        CredentialRepresentation passwordCred = new CredentialRepresentation();
        passwordCred.setTemporary(false);
        passwordCred.setType(CredentialRepresentation.PASSWORD);
        passwordCred.setValue(newPassword.toString().trim());

        // Set password credential
        userResource.get(userId).resetPassword(passwordCred);
//        ['VERIFY_EMAIL', 'UPDATE_PASSWORD']
//        userResource.get(userId).sendVerifyEmail();//  executeActionsEmail(Arrays.asList("VERIFY_EMAIL","UPDATE_PASSWORD"));

    }

    @Override
    public void forgotPassword(String userId) {
        UsersResource userRessource = getRealmResource().users();
        UserResource user = userRessource.get(userId);
        UserRepresentation useritem = new UserRepresentation();
        useritem.setRequiredActions(Arrays.asList(RequiredAction.UPDATE_PASSWORD.toString()));
        user.update(useritem);
        userRessource.get(userId).sendVerifyEmail();
    }

    @Override
    public void logoutUser(String userId) {
        UsersResource userRessource = getRealmResource().users();
        userRessource.get(userId).logout();
    }

    @Override
    public List<UserProfile> getUsersWithRole(String realmAdminCreds, String tenantId, String roleName) {
        return null;
    }

    @Override
    public List<UserProfile> findUser(String realmAdminCreds, String tenantId, String email, String userName) {
        return null;
    }


}
