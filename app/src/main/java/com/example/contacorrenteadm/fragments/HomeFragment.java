package com.example.contacorrenteadm.fragments;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import com.example.contacorrenteadm.R;
import com.example.contacorrenteadm.base.BaseFragment;
import com.example.contacorrenteadm.data.BanckServiceImpl;
import com.example.contacorrenteadm.interfaces_contract.HomeContract;
import com.example.contacorrenteadm.login.LoginActivity;
import com.example.contacorrenteadm.model.Client;
import com.example.contacorrenteadm.presenter.HomePresenter;

import java.util.Locale;

public class HomeFragment extends BaseFragment implements HomeContract.ViewHome {
    private TextView tvBalance;
    private HomeContract.UserActionHome actionHome;
    private Button btnBankStatement;
    private Button btnTransfer;
    private Button btnLogout;
    private Button btnUpdateBalance;

    public static BaseFragment newInstance() {
        return new HomeFragment();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        actionHome = new HomePresenter(new BanckServiceImpl(), this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_home, container, false);
        tvBalance = root.findViewById(R.id.value_available);

        actionHome.putDataUser("bruna.silva@evosystems.com.br");

        add(HomeFragment.newInstance());

        btnBankStatement = root.findViewById(R.id.btn_extract);
        btnTransfer = root.findViewById(R.id.btn_transfer);
        btnLogout = root.findViewById(R.id.btn_logout);
        btnUpdateBalance = root.findViewById(R.id.refreshValue);

        btnBankStatement.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                add(BankStatementFragment.newInstance());
            }
        });

        btnTransfer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                add(TransferFragment.newInstance());
            }
        });
        btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), LoginActivity.class);
                startActivity(intent);
                getActivity().finish();
            }
        });

        btnUpdateBalance.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                actionHome.putDataUser("bruna.silva@evosystems.com.br");
            }
        });

        return root;
    }

    @Override
    protected String getTitle() {
        return "Minha Conta";
    }

    @Override
    public void getDataUser(Client client) {
        tvBalance.setText(String.format(client.balance.toString(), Locale.getDefault()));
    }
}
