package com.example.contacorrenteadm.base;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public abstract class BaseFragment extends Fragment {
    private AddFragmentHandler fragmentHandler;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        fragmentHandler = new AddFragmentHandler(getActivity().getSupportFragmentManager());
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onResume() {
        super.onResume();
        getActivity().setTitle(getTitle());
    }

    protected abstract String getTitle();

    protected void add(BaseFragment fragment, int id, double balance, String email, String name) {
        fragmentHandler.add(fragment,id, balance,email, name);
    }

}
