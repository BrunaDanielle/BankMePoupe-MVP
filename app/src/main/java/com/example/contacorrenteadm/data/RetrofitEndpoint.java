package com.example.contacorrenteadm.data;

import com.example.contacorrenteadm.model.AuthenticationTransfer;
import com.example.contacorrenteadm.model.Login;
import com.example.contacorrenteadm.model.Client;
import com.example.contacorrenteadm.model.BankStatementResult;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface RetrofitEndpoint {

    @FormUrlEncoded
    @POST("./check-login")
    Call<Login> checkLogin(@Field("email") String email, @Field("password") String password);

    @FormUrlEncoded
    @POST("./get-user")
    Call<Client> getUser(@Field("email") String email);

    @FormUrlEncoded
    @POST("./transfer")
    Call<AuthenticationTransfer> transferCheck(@Field("id_user_from") Integer idUserFrom, @Field("id_user_to") Integer idUserTo, @Field("value") double value);

    @FormUrlEncoded
    @POST("./get-bank-statement")
    Call<BankStatementResult> bankStatement(@Field("id_user") Integer idUser);
}
