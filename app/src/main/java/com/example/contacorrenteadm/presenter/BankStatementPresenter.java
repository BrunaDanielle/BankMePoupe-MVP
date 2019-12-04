package com.example.contacorrenteadm.presenter;

import com.example.contacorrenteadm.data.BankServiceAPI;
import com.example.contacorrenteadm.interfaces_contract.BankStatementContract;
import com.example.contacorrenteadm.model.BankStatement;
import java.util.List;

public class BankStatementPresenter implements BankStatementContract.UserActionBankStatement {

    private final BankStatementContract.ViewBankStatement viewBankStatement;
    private final BankServiceAPI mApi;

    public BankStatementPresenter(BankStatementContract.ViewBankStatement viewBankStatement, BankServiceAPI mApi) {
        this.viewBankStatement = viewBankStatement;
        this.mApi = mApi;
    }

    @Override
    public void openBankStatement(Integer idUser) {
        mApi.getBankService(idUser, new BankServiceAPI.BankServiceCallBack<List<BankStatement>>() {
            @Override
            public void onLoaded(List<BankStatement> clientData) {
                viewBankStatement.showBankStatement(clientData);
            }

            @Override
            public void onError() {
                viewBankStatement.onError();
            }
        });
    }
}
