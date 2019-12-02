package com.example.contacorrenteadm.model;

public class Login {

    public boolean status;
    public String email;
    public String password;

    public Login(String email, String password) {
        this.email = email;
        this.password = password;
    }
}
