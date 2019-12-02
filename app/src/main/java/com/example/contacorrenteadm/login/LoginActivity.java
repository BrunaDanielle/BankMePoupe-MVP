package com.example.contacorrenteadm.login;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.contacorrenteadm.MainActivity;
import com.example.contacorrenteadm.R;
import com.example.contacorrenteadm.data.BanckServiceImpl;
import com.example.contacorrenteadm.fragments.HomeFragment;
import com.example.contacorrenteadm.interfaces_contract.LoginContract;
import com.example.contacorrenteadm.model.Client;
import com.example.contacorrenteadm.presenter.LoginPresenter;

public class LoginActivity extends AppCompatActivity implements LoginContract.ViewLogin {
    private Button btnLogin;
    private Button btnForgotPass;
    private EditText email;
    private EditText password;

    private LoginContract.UserActionLogin actionLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        actionLogin = new LoginPresenter(new BanckServiceImpl(), this);

        btnLogin = findViewById(R.id.btn_login);
        email = findViewById(R.id.email);
        password = findViewById(R.id.password);
        btnForgotPass = findViewById(R.id.btn_reset_password);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                actionLogin.putDataLogin(email.getText().toString(), password.getText().toString());
            }
        });
    }

    @Override
    public void showLogin(boolean authenticationAPI) {
        if (authenticationAPI) {
            actionLogin.putDataUser(email.getText().toString());
        } else {
            Toast.makeText(getApplication(), R.string.msg_email_fail, Toast.LENGTH_LONG).show();
        }
    }

    @Override
    public void getDataUser(Client client) {
        Intent i = new Intent(LoginActivity.this, MainActivity.class);
        i.putExtra("NameUser",client.nameClient);
        i.putExtra("PhotoUser",client.photoProfileClient);
        startActivity(i);

    }
}
