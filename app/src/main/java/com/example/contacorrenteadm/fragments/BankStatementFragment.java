package com.example.contacorrenteadm.fragments;

import android.os.Bundle;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.contacorrenteadm.R;
import com.example.contacorrenteadm.base.BackButtonSupportFragment;
import com.example.contacorrenteadm.base.BaseFragment;

public class BankStatementFragment extends BaseFragment implements BackButtonSupportFragment {

    private boolean consumingBackPress = true;

    public static BaseFragment newInstance() {
        return new BankStatementFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_bank_statement, container, false);
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
}
