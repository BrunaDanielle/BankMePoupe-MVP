package com.example.contacorrenteadm.presenter;

import com.example.contacorrenteadm.data.BankServiceAPI;
import com.example.contacorrenteadm.interfaces_contract.BankStatementContract;
import com.example.contacorrenteadm.model.BankStatementResult;

public class BankStatementPresenter implements BankStatementContract.UserActionBankStatement {

    private final BankStatementContract.ViewBankStatement viewBankStatement;
    private final BankServiceAPI mApi;

    public BankStatementPresenter(BankStatementContract.ViewBankStatement viewBankStatement, BankServiceAPI mApi) {
        this.viewBankStatement = viewBankStatement;
        this.mApi = mApi;
    }

    @Override
    public void openBankStatement(Integer idUser) {
        mApi.getBankService(idUser, new BankServiceAPI.BankServiceCallBack<BankStatementResult>() {
            @Override
            public void onLoaded(BankStatementResult clientData) {
                viewBankStatement.showBankStatement(clientData.bankStatementList);
            }
        });
    }
}
