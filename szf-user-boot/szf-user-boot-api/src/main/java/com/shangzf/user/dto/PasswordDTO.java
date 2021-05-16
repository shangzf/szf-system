package com.shangzf.user.dto;

import lombok.Data;

import java.io.Serializable;

@Data
public class PasswordDTO implements Serializable {

    private String userId;
    private String password;
    private String sourcePassword;
    private String configPassword;
}
