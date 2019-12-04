package com.example.contacorrenteadm.fragments;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

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
    private String emailUser;
    private Integer idUser;
    private double balance;

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

        if (getArguments() != null) {
            emailUser = getArguments().getString("EmailSent");
            idUser = getArguments().getInt("idUserSent");
            balance = getArguments().getDouble("getBalance");
        }
        add(HomeFragment.newInstance(), emailUser,idUser, balance);

        actionHome.putDataUser(this.emailUser);
        Button btnBankStatement = root.findViewById(R.id.btn_extract);
        Button btnTransfer = root.findViewById(R.id.btn_transfer);
        Button btnLogout = root.findViewById(R.id.btn_logout);
        Button btnUpdateBalance = root.findViewById(R.id.refreshValue);

        btnBankStatement.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                add(BankStatementFragment.newInstance(),null,idUser,balance);
            }
        });

        btnTransfer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                add(TransferFragment.newInstance(), null,idUser, balance);
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
                actionHome.putDataUser(emailUser);
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

    @Override
    public void onError() {
        Toast.makeText(getContext(),getContext().getString(R.string.dataUserErrorMsg),Toast.LENGTH_SHORT).show();
    }
}
