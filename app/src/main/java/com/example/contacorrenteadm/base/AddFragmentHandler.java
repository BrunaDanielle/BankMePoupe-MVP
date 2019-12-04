package com.example.contacorrenteadm.base;

import android.os.Bundle;
import android.util.Log;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.contacorrenteadm.R;

public class AddFragmentHandler {
    private final FragmentManager fragmentManager;

    public AddFragmentHandler(FragmentManager fragmentManager) {
        this.fragmentManager = fragmentManager;
    }

    public void add(BaseFragment fragment, String data, int idUser, double balance) {
        //don't add a fragment of the same type on top of itself.
        BaseFragment currentFragment = getCurrentFragment();
        if (currentFragment != null) {
            if (currentFragment.getClass() == fragment.getClass()) {
                Log.w("Fragment Manager", "Tried to add a fragment of the same type to the backstack. This may be done on purpose in some circumstances but generally should be avoided.");
                return;
            }
        }
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.fragment_container, fragment, fragment.getTitle());
        fragmentTransaction.addToBackStack(fragment.getTitle());
        if(data != null || idUser > 0 || balance >0){
            Bundle bundle = new Bundle();
            bundle.putString("EmailSent", data);
            bundle.putInt("idUserSent", idUser);
            bundle.putDouble("getBalance", balance);
            fragment.setArguments(bundle);
        }
        fragmentTransaction.commit();
    }

    @Nullable
    public BaseFragment getCurrentFragment() {
        if (fragmentManager.getBackStackEntryCount() == 0) {
            return null;
        }
        FragmentManager.BackStackEntry currentEntry = fragmentManager.getBackStackEntryAt(fragmentManager.getBackStackEntryCount() - 1);

        String tag = currentEntry.getName();
        Fragment fragment = fragmentManager.findFragmentByTag(tag);
        return (BaseFragment) fragment;
    }
}