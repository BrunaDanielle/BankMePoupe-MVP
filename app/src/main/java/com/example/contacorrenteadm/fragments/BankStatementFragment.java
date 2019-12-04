package com.example.contacorrenteadm.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.contacorrenteadm.R;
import com.example.contacorrenteadm.adapter.BankStatementAdapter;
import com.example.contacorrenteadm.base.BaseFragment;
import com.example.contacorrenteadm.data.BanckServiceImpl;
import com.example.contacorrenteadm.interfaces_contract.BankStatementContract;
import com.example.contacorrenteadm.model.BankStatement;
import com.example.contacorrenteadm.presenter.BankStatementPresenter;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;


public class BankStatementFragment extends BaseFragment implements BankStatementContract.ViewBankStatement {
    private BankStatementAdapter bankStatementAdapter;
    private boolean consumingBackPress = true;
    private BankStatementContract.UserActionBankStatement userActionBankStatement;
    private int idUser;

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
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(bankStatementAdapter);

        recyclerView.addItemDecoration(
                new DividerItemDecoration(getContext(), DividerItemDecoration.VERTICAL));

        TextView balanceAvailable = root.findViewById(R.id.balance);
        if (getArguments() != null) {
            double balance = getArguments().getDouble("getBalance");
            idUser = getArguments().getInt("idUserSent");
            balanceAvailable.setText(String.valueOf(balance));
        }
        userActionBankStatement.openBankStatement(idUser);

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

    @Override
    public void onError() {
        Toast.makeText(getContext(),getContext().getString(R.string.dataUserErrorMsg),Toast.LENGTH_SHORT).show();
    }
}
