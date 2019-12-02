package com.example.contacorrenteadm.data;

import com.example.contacorrenteadm.model.AuthenticationTransfer;
import com.example.contacorrenteadm.model.Login;
import com.example.contacorrenteadm.model.Client;
import com.example.contacorrenteadm.model.BankStatementResult;

import retrofit2.http.POST;

public interface BankServiceAPI {

    interface BankServiceCallBack<T> {
        void onLoaded(T clientData);
    }

    @POST("./check-login")
    void getLogin(String email, String password, BankServiceCallBack<Login> callBack);

    @POST("./get-user")
    void getUser(String email, BankServiceCallBack<Client> callBack);

    @POST("./transfer")
    void getTransfer(Integer idUserFrom, Integer idUserTo, Double valueTranfer, BankServiceCallBack<AuthenticationTransfer> callBack);

    @POST("./get-bank-statement")
    void getBankService(Integer idUser, BankServiceCallBack<BankStatementResult> callBack);
}
