package com.example.contacorrenteadm.fragments;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.contacorrenteadm.MainActivity;
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
    private ProgressBar progressBar;
    private TextView balanceAccount;
    private TextView real;
    private String nameUser;

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
        final View root = inflater.inflate(R.layout.fragment_home, container, false);
        tvBalance = root.findViewById(R.id.value_available);

        if (getArguments() != null) {
            emailUser = getArguments().getString("EmailSent");
            idUser = getArguments().getInt("idUserSent");
        }
        add(HomeFragment.newInstance(), idUser, balance,emailUser, null);

        ((MainActivity) getActivity()).lockDrawerLayout(false);

        actionHome.putDataUser(this.emailUser);
        Button btnBankStatement = root.findViewById(R.id.btn_extract);
        Button btnTransfer = root.findViewById(R.id.btn_transfer);
        Button btnLogout = root.findViewById(R.id.btn_logout);
        Button btnUpdateBalance = root.findViewById(R.id.refreshValue);
        progressBar = root.findViewById(R.id.progressBar2);
        balanceAccount = root.findViewById(R.id.balanceAccount);
        real = root.findViewById(R.id.realSymbol);


        btnBankStatement.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                add(BankStatementFragment.newInstance(), idUser, balance, null,null);
            }
        });

        btnTransfer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                add(TransferFragment.newInstance(), idUser, balance, nameUser,null);
            }
        });
        btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), LoginActivity.class);
                startActivity(intent);
                if (getActivity() != null) {
                    getActivity().finish();
                }

            }
        });

        btnUpdateBalance.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadProgressBar(root);
                actionHome.putDataUser(emailUser);
            }
        });

        return root;
    }

    public void loadProgressBar(final View view) {
        balanceAccount.setVisibility(View.GONE);
        real.setVisibility(View.GONE);
        tvBalance.setVisibility(View.GONE);
        progressBar.setVisibility(View.VISIBLE);

        new Thread(new Runnable() {
            @Override
            public void run() {
                final int progress = 100;
                for (int i = 0; i <= progress; i++) {
                    final int progressLoading = i;

                    if (getActivity() != null) {
                        getActivity().runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                if (progressLoading == progress) {
                                    progressBar.setVisibility(View.GONE);
                                    balanceAccount.setVisibility(View.VISIBLE);
                                    real.setVisibility(View.VISIBLE);
                                    tvBalance.setVisibility(View.VISIBLE);
                                }
                            }
                        });
                    }
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
        return "Minha Conta";
    }

    @Override
    public void getDataUser(Client client) {
        tvBalance.setText(String.format(client.balance.toString(), Locale.getDefault()));
        balance =Double.parseDouble(tvBalance.getText().toString());
        nameUser = client.nameClient;
    }

    @Override
    public void onError() {
        Toast.makeText(getContext(), getContext().getString(R.string.dataUserErrorMsg), Toast.LENGTH_SHORT).show();
    }
}
