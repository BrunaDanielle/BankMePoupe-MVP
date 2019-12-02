package com.example.contacorrenteadm.interfaces_contract;

import com.example.contacorrenteadm.base.BackButtonSupportFragment;
import com.example.contacorrenteadm.model.BankStatement;

import java.util.List;

public class BankStatementContract {
    public interface ViewBankStatement  extends BackButtonSupportFragment  {
        void showBankStatement(List<BankStatement> bankStatement);
    }

    public interface UserActionBankStatement{
        void openBankStatement(Integer idUser);
    }
}
