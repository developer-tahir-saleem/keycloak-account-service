package com.keycloak.accountservice.controller;


import com.keycloak.accountservice.model.Device;
import com.keycloak.accountservice.model.User;
import com.keycloak.accountservice.request.*;
import com.keycloak.accountservice.response.*;
import com.keycloak.accountservice.service.DeviceService;
import com.keycloak.accountservice.service.KeycloakService;
import com.keycloak.accountservice.service.UserService;
import org.keycloak.KeycloakPrincipal;
import org.keycloak.KeycloakSecurityContext;
import org.keycloak.adapters.springsecurity.token.KeycloakAuthenticationToken;
import org.keycloak.representations.AccessToken;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.security.Principal;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/keycloak")
public class AuthController {


    @Autowired
    private UserService userService;

    @Autowired
    private DeviceService deviceService;


    private final KeycloakService KeycloakService;

    @Autowired
    public AuthController(KeycloakService keycloakService) {

        this.KeycloakService = keycloakService;
    }


    @GetMapping("/publica")
    public String publica() {
        String msg = String.format("Welcome To Public Auth API");
        return msg;
    }


    @PostMapping(path = "/register", produces = "application/json")
    public ResponseEntity register(@RequestBody @Valid User account) {
        return KeycloakService.registerUser(account);
    }

    @GetMapping(path = "/whoami", produces = "application/json")
    public AccessToken me(User user, final Principal principal) {
        KeycloakAuthenticationToken token = (KeycloakAuthenticationToken) principal;
        KeycloakPrincipal kcprincipal = (KeycloakPrincipal) token.getPrincipal();
        KeycloakSecurityContext session = kcprincipal.getKeycloakSecurityContext();
        AccessToken accessToken = session.getToken();

        accessToken.setScope("test taddkasd tesasd ");
        return accessToken;
//        User appUser = userService.findByAuthId(accessToken.getSubject());
//        BeanUtils.copyProperties(appUser, user);
//        return user;
    }


    @RequestMapping(value = "/refresh", method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity<?> getRefreshToken(@RequestHeader(value = "Refresh") String refreshToken) {
        String responseToken = null;
        try {
            responseToken = KeycloakService.getByRefreshToken(refreshToken);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(responseToken, HttpStatus.OK);
    }

    /*
     * Get token for the first time when user log in. We need to pass
     * credentials only once. Later communication will be done by sending token.
     */

    @RequestMapping(value = "/token", method = RequestMethod.POST, produces = "application/json")
    public ResponseEntity getTokenUsingCredentials(@Valid @RequestBody UserCredentials userCredentials) {

//        This is for Mobile Notification Device Register
        if(userCredentials.getDevice()!= null){
            Device device = userCredentials.getDevice();
            if (device.getDeviceToken() != "") {
                User user = userService.findByEmail(userCredentials.getEmail());
                device.setUserId(user.getId().toString());
                device.setLoggedin(true);
                deviceService.insertOrUpdate(device);
            }
        }

//        This is for Mobile Notification Device Register

        String responseToken = null;
        try {
            responseToken = KeycloakService.getToken(userCredentials);
        } catch (Exception e) {
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
        return ResponseEntity.status(HttpStatus.OK).body(responseToken);
    }


    @PostMapping(path = "forgotPassword", produces = "application/json")
    public ResponseForget forgotPassword(@RequestBody @Valid RequestForget reqForget) {
        User user = userService.findByEmail(reqForget.getEmail());
        KeycloakService.forgotPassword(user.getAuthId());
        return new ResponseForget(" Forget Password Email Send Successfully ! @" + reqForget.getEmail(), true);
    }
//

    @PostMapping(path = "/updatePassword", produces = "application/json")
    public ResponseChangePassword changePassword(final Principal principal, @RequestBody @Valid RequestChangePassword requestChangePassword) {
        KeycloakAuthenticationToken token = (KeycloakAuthenticationToken) principal;
        KeycloakPrincipal kcprincipal = (KeycloakPrincipal) token.getPrincipal();
        KeycloakSecurityContext session = kcprincipal.getKeycloakSecurityContext();
        AccessToken accessToken = session.getToken();
        String userId = accessToken.getSubject();
        KeycloakService.resetPassword(requestChangePassword.getPassword(), userId);
        return new ResponseChangePassword(" Hi!, your password has been successfully updated! ", true);
    }

    @PostMapping(path = "/remove/user", produces = "application/json")
    public ResponseRemoveAccount changePassword(@RequestBody @Valid RequestRemoveAccount requestRemoveAccount) {
        KeycloakService.removeUser(requestRemoveAccount.getId());
        return new ResponseRemoveAccount(" Hi!, user has been successfully deleted! ", true);
    }


    @RequestMapping(value = "/logoutAll", method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity<?> logoutAllSessions(final Principal principal) {
        KeycloakAuthenticationToken token = (KeycloakAuthenticationToken) principal;
        KeycloakPrincipal kcprincipal = (KeycloakPrincipal) token.getPrincipal();
        KeycloakSecurityContext session = kcprincipal.getKeycloakSecurityContext();
        AccessToken accessToken = session.getToken();
        User user = userService.findByAuthId(accessToken.getSubject());
        //        This is for Mobile Notification Device Register
        deviceService.logoutAnyWhere(user.getId().toString());
//        This is for Mobile Notification Device Register

        KeycloakService.logoutUser(accessToken.getSubject());
        return new ResponseEntity<>(new ResponseUser("Hi!, you have logged out All Sessions successfully!", true), HttpStatus.OK);
    }

    @GetMapping(path = "/logout")
    public ResponseEntity<?> logout(HttpServletRequest req, @RequestParam(value = "refresh_token") String refresh_token, @RequestParam(value = "hardware_id") String  hardware_id) throws ServletException {
        final HttpServletRequest request = (HttpServletRequest) req;
        Principal principal = request.getUserPrincipal();
        KeycloakAuthenticationToken token = (KeycloakAuthenticationToken) principal;
        KeycloakPrincipal kcprincipal = (KeycloakPrincipal) token.getPrincipal();
        KeycloakSecurityContext session = kcprincipal.getKeycloakSecurityContext();
        AccessToken accessToken = session.getToken();


        //        This is for Mobile Notification Device Register


        User user = userService.findByAuthId(accessToken.getSubject());
        Device device = new Device();
        device.setUserId(user.getId().toString());
        device.setLoggedin(false);
        device.setHardwareId(hardware_id);
        deviceService.insertOrUpdate(device);

//        This is for Mobile Notification Device Register

        final String authHeader = request.getHeader("authorization");
        KeycloakService.singleSessionLogout(authHeader, refresh_token);
        return new ResponseEntity<>(new ResponseUser("Hi!, you have logged out successfully!", true), HttpStatus.OK);
    }

    @PostMapping(path = "updateProfile", produces = "application/json")
    public ResponseChangeStatus updateAccount(final Principal principal, @RequestBody @Valid RequestUpdateAccount account) {
        KeycloakAuthenticationToken token = (KeycloakAuthenticationToken) principal;
        KeycloakPrincipal kcprincipal = (KeycloakPrincipal) token.getPrincipal();
        KeycloakSecurityContext session = kcprincipal.getKeycloakSecurityContext();
        AccessToken accessToken = session.getToken();
        User user = userService.findByAuthId(accessToken.getSubject());
        BeanUtils.copyProperties(account, user);
        userService.save(user);
        return new ResponseChangeStatus(" Update Status Successfully ! @" + user.getEmail(), true);
    }
//    //    @PreAuthorize("hasRole('ADMIN')")
//    @DeleteMapping(path = "removeaccount", produces = "application/json")
//    public ResponseRemoveAccount removeAccount(@RequestParam(value = "email") String email) {
//        User user = accountService.removeAccount(email);
//        return new ResponseRemoveAccount(user.getType() + " is Deleted Successfully ! @" + user.getUsername(), true);
//    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public List<String> handleValidationExceptions(MethodArgumentNotValidException ex) {
        return ex.getBindingResult()
                .getAllErrors().stream()
                .map(ObjectError::getDefaultMessage)
                .collect(Collectors.toList());
    }

}