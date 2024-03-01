package ru.ivan.instazoo.payload.response;

import lombok.Data;

@Data
public class InvalidLoginResponse {
    private String login;
    private String password;

    public InvalidLoginResponse(){
        this.login = "Invalid login";
        this.password = "Invalid password";
    }
}
