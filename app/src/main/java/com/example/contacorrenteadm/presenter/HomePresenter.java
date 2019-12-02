package com.example.contacorrenteadm.presenter;

import com.example.contacorrenteadm.data.BankServiceAPI;
import com.example.contacorrenteadm.interfaces_contract.HomeContract;
import com.example.contacorrenteadm.model.Client;

public class HomePresenter implements HomeContract.UserActionHome {
    private final BankServiceAPI mApi;
    private final HomeContract.ViewHome viewHome;

    public HomePresenter(BankServiceAPI mApi, HomeContract.ViewHome viewHome) {
        this.mApi = mApi;
        this.viewHome = viewHome;
    }

    @Override
    public void putDataUser(String email) {
        mApi.getUser(email, new BankServiceAPI.BankServiceCallBack<Client>() {
            @Override
            public void onLoaded(Client clientData) {
                viewHome.getDataUser(clientData);
            }
        });
    }
}
