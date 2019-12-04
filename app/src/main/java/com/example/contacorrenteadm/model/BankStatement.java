package com.example.contacorrenteadm.model;

import com.google.gson.annotations.SerializedName;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class BankStatement {
    @SerializedName("id")
    public int idTransaction;

    @SerializedName("id_from")
    public int id_from;

    @SerializedName("id_to")
    public int id_to;

    @SerializedName("value")
    public double valueTransfer;

    @SerializedName("data")
    public String dateTransaction;

    public Date convertDate(String dateTransaction) throws ParseException {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        return formatter.parse(dateTransaction);
    }
}
