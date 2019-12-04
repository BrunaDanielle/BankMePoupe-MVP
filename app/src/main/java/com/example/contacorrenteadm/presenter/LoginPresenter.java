package com.example.contacorrenteadm.presenter;

import com.example.contacorrenteadm.data.BankServiceAPI;
import com.example.contacorrenteadm.interfaces_contract.LoginContract;
import com.example.contacorrenteadm.model.Client;
import com.example.contacorrenteadm.model.Login;

public class LoginPresenter implements LoginContract.UserActionLogin {
    private final BankServiceAPI mApi;
    private final LoginContract.ViewLogin viewLogin;

    public LoginPresenter(BankServiceAPI mApi, LoginContract.ViewLogin viewLogin) {
        this.mApi = mApi;
        this.viewLogin = viewLogin;
    }

    @Override
    public void putDataLogin(String email, String password) {
        mApi.getLogin(email, password, new BankServiceAPI.BankServiceCallBack<Login>() {
            @Override
            public void onLoaded(Login clientData) {
                viewLogin.showLogin(clientData.status);
            }

            @Override
            public void onError() {
                viewLogin.onError();
            }
        });
    }

    @Override
    public void putDataUser(String email) {

        mApi.getUser(email, new BankServiceAPI.BankServiceCallBack<Client>() {
            @Override
            public void onLoaded(Client clientData) {
                viewLogin.getDataUser(clientData);
            }

            @Override
            public void onError() {
                viewLogin.onError();
            }
        });
    }
}
