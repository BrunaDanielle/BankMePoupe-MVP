package com.example.contacorrenteadm.interfaces_contract;

import com.example.contacorrenteadm.model.Client;

public class HomeContract {
    public interface ViewHome {
        void getDataUser(Client client);
        void onError();
    }

    public interface UserActionHome{
        void putDataUser(String email);
    }
}
