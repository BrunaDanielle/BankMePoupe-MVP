package com.example.contacorrenteadm.interfaces_contract;

import com.example.contacorrenteadm.base.BackButtonSupportFragment;
import com.example.contacorrenteadm.model.Client;

public class TransferContract extends HomeContract {
    public interface ViewTransfer extends BackButtonSupportFragment {
        void showTransfer(Boolean authentication);
        void getIdUser(Client client);
    }

    public interface UserActionTransfer {
        void doTransfer(Integer idUserFrom, Integer idUserTo, double value);
        void putEmailUserTo(String email);
    }
}
