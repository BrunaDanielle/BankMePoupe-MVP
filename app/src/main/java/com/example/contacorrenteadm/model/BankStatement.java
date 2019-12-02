package com.example.contacorrenteadm.model;

import com.google.gson.annotations.SerializedName;

import java.util.Date;

public class BankStatement {
    @SerializedName("id")
    public Integer idTransaction;

    @SerializedName("id_from")
    public Integer id_from;

    @SerializedName("id_to")
    public Integer id_to;

    @SerializedName("value")
    public double valueTransfer;

    @SerializedName("data")
    public Date dateTransaction;
}
