package com.example.contacorrenteadm.interfaces_contract;

import com.example.contacorrenteadm.model.Client;

public class LoginContract {
    public interface ViewLogin {
        void showLogin(boolean authenticationAPI);
        void getDataUser(Client client);
    }
    public interface UserActionLogin {
        void putDataLogin(String email, String password);
        void putDataUser(String email);
    }
}