package com.example.contacorrenteadm.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
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

import static android.view.View.VISIBLE;


public class BankStatementFragment extends BaseFragment implements BankStatementContract.ViewBankStatement {
    private BankStatementAdapter bankStatementAdapter;
    private boolean consumingBackPress = true;
    private BankStatementContract.UserActionBankStatement userActionBankStatement;
    private int idUser;
    private ProgressBar progressBarStatement;
    RecyclerView recyclerView;

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
        recyclerView = root.findViewById(R.id.rv_extract);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(bankStatementAdapter);

        recyclerView.addItemDecoration(
                new DividerItemDecoration(getContext(), DividerItemDecoration.VERTICAL));

        TextView balanceAvailable = root.findViewById(R.id.balance);
        progressBarStatement = root.findViewById(R.id.progressBarStatement);
        if (getArguments() != null) {
            double balance = getArguments().getDouble("getBalance");
            idUser = getArguments().getInt("idUserSent");
            balanceAvailable.setText(String.valueOf(balance));
        }

        userActionBankStatement.openBankStatement(idUser);
        showBankStatementProgressBar(root);
        return root;
    }

    public void showBankStatementProgressBar(View view){
        recyclerView.setVisibility(View.GONE);
        progressBarStatement.setVisibility(View.VISIBLE);
        new Thread(new Runnable() {
            @Override
            public void run() {
                final int progress = 100;
                for (int i = 0; i <= progress; i++) {
                    final int progressLoading = i;

                    getActivity().runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            if (progressLoading == progress) {
                                recyclerView.setVisibility(VISIBLE);
                                progressBarStatement.setVisibility(View.GONE);
                            }
                        }
                    });

                    try {
                        Thread.sleep(10);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();
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
        Toast.makeText(getContext(), getContext().getString(R.string.dataUserErrorMsg), Toast.LENGTH_SHORT).show();
    }
}
