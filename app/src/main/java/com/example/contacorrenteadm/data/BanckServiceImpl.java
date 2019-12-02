package com.example.contacorrenteadm.data;

import com.example.contacorrenteadm.model.AuthenticationTransfer;
import com.example.contacorrenteadm.model.Login;
import com.example.contacorrenteadm.model.Client;
import com.example.contacorrenteadm.model.BankStatementResult;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class BanckServiceImpl implements BankServiceAPI {
    RetrofitEndpoint mRetrofit;

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
            }
        });
    }

    @Override
    public void getTransfer(Integer idUserFrom, Integer idUserTo, Double valueTranfer, final BankServiceCallBack<AuthenticationTransfer> callBack) {
        Call<AuthenticationTransfer> callTransfer = mRetrofit.transferCheck(idUserFrom,idUserTo,valueTranfer);
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
            }
        });
    }

    @Override
    public void getBankService(Integer idUser, final BankServiceCallBack<BankStatementResult> callBack) {
        Call<BankStatementResult> callExtract = mRetrofit.bankStatement(idUser);
        callExtract.enqueue(new Callback<BankStatementResult>() {
            public void onResponse(Call<BankStatementResult> call, Response<BankStatementResult> response) {
                try {
                    if (response.code() == 200) {
                        BankStatementResult bankStatementResult = response.body();
                        callBack.onLoaded(bankStatementResult);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<BankStatementResult> call, Throwable t) {
            }
        });
    }
}
