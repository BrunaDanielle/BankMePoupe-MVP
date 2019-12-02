package com.example.contacorrenteadm.fragments;

import android.os.Bundle;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.contacorrenteadm.R;
import com.example.contacorrenteadm.adapter.BankStatementAdapter;
import com.example.contacorrenteadm.base.BackButtonSupportFragment;
import com.example.contacorrenteadm.base.BaseFragment;
import com.example.contacorrenteadm.data.BanckServiceImpl;
import com.example.contacorrenteadm.interfaces_contract.BankStatementContract;
import com.example.contacorrenteadm.model.BankStatement;
import com.example.contacorrenteadm.presenter.BankStatementPresenter;

import java.util.ArrayList;
import java.util.List;

public class BankStatementFragment extends BaseFragment implements BankStatementContract.ViewBankStatement {
    private BankStatementAdapter bankStatementAdapter;
    private boolean consumingBackPress = true;
    private BankStatementContract.UserActionBankStatement userActionBankStatement;

    public static BaseFragment newInstance() {
        return new BankStatementFragment();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        bankStatementAdapter = new BankStatementAdapter(new ArrayList<BankStatement>(0));
        userActionBankStatement = new BankStatementPresenter(this, new BanckServiceImpl());
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_bank_statement, container, false);
        RecyclerView recyclerView = root.findViewById(R.id.rv_extract);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(bankStatementAdapter);

        userActionBankStatement.openBankStatement(4);

        return root;
    }

    @Override
    protected String getTitle() {
        return "Extrato";
    }

    @Override
    public boolean onBackPressed() {
        if (consumingBackPress) {
            consumingBackPress = false;
            return true;
        }
        return false;
    }

    @Override
    public void showBankStatement(List<BankStatement> bankStatement) {
        bankStatementAdapter.replaceData(bankStatement);
    }
}
