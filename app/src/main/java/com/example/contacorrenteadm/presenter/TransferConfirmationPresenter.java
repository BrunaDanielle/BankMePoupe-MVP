package com.example.contacorrenteadm.presenter;

import com.example.contacorrenteadm.interfaces_contract.TransferConformationContract;

public class TransferConfirmationPresenter implements TransferConformationContract.appAction {
    private TransferConformationContract.ViewTransferConfirmation viewTransferConfirmation;

    public TransferConfirmationPresenter(TransferConformationContract.ViewTransferConfirmation viewTransferConfirmation) {

        this.viewTransferConfirmation = viewTransferConfirmation;
    }

    @Override
    public void sendDataUser() {
        viewTransferConfirmation.getDataUser();
    }
}
