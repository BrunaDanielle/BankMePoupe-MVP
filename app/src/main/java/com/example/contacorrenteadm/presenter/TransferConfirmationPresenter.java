package com.example.contacorrenteadm.presenter;

import com.example.contacorrenteadm.data.BanckServiceImpl;
import com.example.contacorrenteadm.fragments.TransferFragment;
import com.example.contacorrenteadm.interfaces_contract.TransferConformationContract;

public class TransferConfirmationPresenter implements TransferConformationContract.appAction {
    private TransferConformationContract.ViewTransferConfirmation viewTransferConfirmation;

    public TransferConfirmationPresenter(TransferFragment transferFragment, BanckServiceImpl banckService) {
    }

    @Override
    public void sendDataUser(String nameUserTo, String nameUserFrom, int value) {
        viewTransferConfirmation.getDataUser(nameUserTo, nameUserFrom, value);
    }
}
