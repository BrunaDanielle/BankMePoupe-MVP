package com.example.contacorrenteadm.data;

import android.util.Log;

import com.example.contacorrenteadm.model.AuthenticationTransfer;
import com.example.contacorrenteadm.model.BankStatement;
import com.example.contacorrenteadm.model.BankStatementResult;
import com.example.contacorrenteadm.model.Login;
import com.example.contacorrenteadm.model.Client;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class BanckServiceImpl implements BankServiceAPI {
    private RetrofitEndpoint mRetrofit;

    public BanckServiceImpl() {
        mRetrofit = RetrofitClient.getClient().create(RetrofitEndpoint.class);
    }

    @Override
    public void getLogin(String email, String password, final BankServiceCallBack<Login> callBack) {
        Call<Login> callLogin = mRetrofit.checkLogin(email, password);
        callLogin.enqueue(new Callback<Login>() {
            public void onResponse(Call<Login> call, Response<Login> response) {

                try {
                    if (response.code() == 200) {
                        Login loginResponse = response.body();
                        callBack.onLoaded(loginResponse);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<Login> call, Throwable t) {
                Log.w("getLogin", t);
                callBack.onError();
            }
        });
    }

    @Override
    public void getUser(String email, final BankServiceCallBack<Client> callBack) {
        Call<Client> callClient = mRetrofit.getUser(email);
        callClient.enqueue(new Callback<Client>() {
            public void onResponse(Call<Client> call, Response<Client> response) {
                try {
                    if (response.code() == 200) {
                        Client client = response.body();
                        callBack.onLoaded(client);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<Client> call, Throwable t) {
                callBack.onError();
                Log.w("getUser", t);
            }
        });
    }

    @Override
    public void getTransfer(Integer idUserFrom, Integer idUserTo, Double valueTranfer, final BankServiceCallBack<AuthenticationTransfer> callBack) {
        Call<AuthenticationTransfer> callTransfer = mRetrofit.transferCheck(idUserFrom, idUserTo, valueTranfer);
        callTransfer.enqueue(new Callback<AuthenticationTransfer>() {
            public void onResponse(Call<AuthenticationTransfer> call, Response<AuthenticationTransfer> response) {
                try {
                    if (response.code() == 200) {
                        AuthenticationTransfer authenticationAPI = response.body();
                        callBack.onLoaded(authenticationAPI);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<AuthenticationTransfer> call, Throwable t) {
                Log.w("getTransfer", t);
                callBack.onError();
            }
        });
    }

    @Override
    public void getBankService(Integer idUser, final BankServiceCallBack<List<BankStatement>> callBack) {
        Call<List<BankStatement>> callExtract = mRetrofit.bankStatement(idUser);
        callExtract.enqueue(new Callback<List<BankStatement>>() {
            @Override
            public void onResponse(Call<List<BankStatement>> call, Response<List<BankStatement>> response) {
                try {
                    if (response.code() == 200) {
                        List<BankStatement> bankStatementResult = response.body();
                        callBack.onLoaded(bankStatementResult);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<List<BankStatement>> call, Throwable t) {
                Log.w("getBankService", t);
                callBack.onError();
            }
        });
    }
}
