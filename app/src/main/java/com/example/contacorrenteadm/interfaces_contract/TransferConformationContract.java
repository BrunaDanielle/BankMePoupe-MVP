package com.example.contacorrenteadm.interfaces_contract;

import com.example.contacorrenteadm.base.BackButtonSupportFragment;

public class TransferConformationContract {
    public interface ViewTransferConfirmation extends BackButtonSupportFragment {
        void getDataUser(String nameUserTo,String nameUserFrom, int value);
    }

    public interface appAction{
        void sendDataUser(String nameUserTo,String nameUserFrom, int value);
    }
}
