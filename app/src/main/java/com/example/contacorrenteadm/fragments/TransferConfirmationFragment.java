package com.example.contacorrenteadm.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.example.contacorrenteadm.R;
import com.example.contacorrenteadm.base.BaseFragment;
import com.example.contacorrenteadm.interfaces_contract.TransferConformationContract;
import com.example.contacorrenteadm.presenter.TransferConfirmationPresenter;

public class TransferConfirmationFragment extends BaseFragment implements TransferConformationContract.ViewTransferConfirmation {
    private TextView txtNameFrom;
    private TextView txtNameTo;
    private TextView txtValueSent;
    private TransferConformationContract.appAction appAction;
    private boolean consumingBackPress = true;

    public static BaseFragment newInstance() {
        return new TransferConfirmationFragment();
    }

    public TransferConfirmationFragment() {
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        appAction = new TransferConfirmationPresenter(this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_transfer_confirmation, container, false);

        txtNameFrom = root.findViewById(R.id.nameClientFrom);
        txtNameTo = root.findViewById(R.id.nameClientTo);
        txtValueSent = root.findViewById(R.id.valueSent);

        appAction.sendDataUser();

        return root;
    }

    @Override
    public void getDataUser() {
        if (getArguments() != null) {
            txtNameFrom.setText(getArguments().getString("EmailSent"));
            txtNameTo.setText(getArguments().getString("NameUser"));
            txtValueSent.setText(String.valueOf(getArguments().getDouble("getBalance")));
        }
    }

    @Override
    protected String getTitle() {
        return "Comprovante Transferencia";
    }

    @Override
    public boolean onBackPressed() {
        if (consumingBackPress) {
            consumingBackPress = false;
            return true;
        }
        return false;
    }
}
