package com.example.contacorrenteadm.interfaces_contract;

import com.example.contacorrenteadm.base.BackButtonSupportFragment;

public class TransferConformationContract {
    public interface ViewTransferConfirmation extends BackButtonSupportFragment {
        void getDataUser();
    }

    public interface appAction{
        void sendDataUser();
    }
}
