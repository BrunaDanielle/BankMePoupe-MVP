package com.example.contacorrenteadm.model;


import com.google.gson.annotations.SerializedName;
import java.util.List;

public class BankStatementResult {

    @SerializedName("id")
    public List<BankStatement> bankStatementList;

    public BankStatementResult(){}

    public BankStatementResult(List<BankStatement> bankStatements){
        this.bankStatementList = bankStatements;
    }
}

