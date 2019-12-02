package com.example.contacorrenteadm.model;


import java.util.List;

public class BankStatementResult {

    public List<BankStatement> bankStatementList;

    public BankStatementResult(){}

    public BankStatementResult(List<BankStatement> bankStatements){
        this.bankStatementList = bankStatements;
    }
}

