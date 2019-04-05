package com.keycloak.accountservice.model;

import com.sun.istack.internal.NotNull;
import lombok.*;

import javax.persistence.Entity;
import javax.validation.constraints.Size;

/**
 * Created by tahir on 2/12/19.
 */
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@EqualsAndHashCode(callSuper = true)
public class Device extends BaseModel {


    @NotNull
    private long type;
    private String userId;
    private boolean isLoggedin;
    @NotNull
    @Size(message = "Device HardwareId is required !")
    private String hardwareId;
    @NotNull
    @Size(message = "Device FCM Token is required !")
    private String deviceToken;

}

