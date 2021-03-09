package com.tomasfriends.bansikee_server.sign.dto.controllerdto;

import com.tomasfriends.bansikee_server.sign.exceptions.NotSamePasswordException;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Builder
@Getter
@NoArgsConstructor
public class BasicLoginUserRequest {

    @NotBlank
    private String name;
    @Email
    private String email;
    @NotBlank
    private String password;
    @NotBlank
    private String passwordRe;

    public BasicLoginUserRequest(String name, String email, String password, String passwordRe) {
        if (!password.equals(passwordRe)) {
            throw new NotSamePasswordException();
        }
        this.name = name;
        this.email = email;
        this.password = password;
        this.passwordRe = passwordRe;
    }
}