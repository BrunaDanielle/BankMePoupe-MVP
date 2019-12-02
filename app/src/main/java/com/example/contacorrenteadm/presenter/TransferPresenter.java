package com.example.contacorrenteadm.presenter;

import com.example.contacorrenteadm.data.BankServiceAPI;
import com.example.contacorrenteadm.interfaces_contract.TransferContract;
import com.example.contacorrenteadm.model.AuthenticationTransfer;
import com.example.contacorrenteadm.model.Client;

public class TransferPresenter implements TransferContract.UserActionTransfer {
    private final BankServiceAPI mApi;
    private final TransferContract.ViewTransfer viewTransfer;

    public TransferPresenter(BankServiceAPI mApi, TransferContract.ViewTransfer viewTransfer) {
        this.mApi = mApi;
        this.viewTransfer = viewTransfer;
    }

    public void doTransfer(Integer idUserFrom, Integer idUserTo, double value) {
        mApi.getTransfer(idUserFrom, idUserTo, value, new BankServiceAPI.BankServiceCallBack<AuthenticationTransfer>() {
            @Override
            public void onLoaded(AuthenticationTransfer clientData) {
                viewTransfer.showTransfer(clientData.status);
            }
        });
    }

    @Override
    public void putEmailUserTo(String email) {
        mApi.getUser(email, new BankServiceAPI.BankServiceCallBack<Client>() {
            @Override
            public void onLoaded(Client clientData) {
                viewTransfer.getIdUser(clientData);
            }
        });
    }
}
