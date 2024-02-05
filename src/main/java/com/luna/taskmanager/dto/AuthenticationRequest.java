package com.luna.taskmanager.dto;

import lombok.*;

// used by authetication controller (jwt token for restfull APIs)
// this is a dto used to transfer information for authentication  between client and server
// DTOs are used to encapsulate data
@Getter
@Setter
@NoArgsConstructor
public class AuthenticationRequest {
    private String username;
    private String password;

}