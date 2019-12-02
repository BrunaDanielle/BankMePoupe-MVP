package com.example.contacorrenteadm.model;

import com.google.gson.annotations.SerializedName;

public class Client {
    @SerializedName("id")
    public Integer id;

    @SerializedName("name")
    public String nameClient;

    @SerializedName("email")
    public String emailClient;

    @SerializedName("profile")
    public String photoProfileClient;

    @SerializedName("balance")
    public Double balance;
}
