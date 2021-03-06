package com.tomasfriends.bansikee_server.dto.controllerdto;

import lombok.AllArgsConstructor;
import lombok.Getter;

import javax.validation.constraints.NotBlank;

@Getter
@AllArgsConstructor
public class EmailAndPassword {

    @NotBlank
    private final String email;
    @NotBlank
    private final String password;
}
