package com.liuzhe.mvpdemo;

import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;

import com.liuzhe.mvpdemo.presenter.LoginPresenter;
import com.liuzhe.mvpdemo.view.ILoginView;

public class MainActivity extends AppCompatActivity implements ILoginView, View.OnClickListener {

    ProgressBar progressBar;
    EditText usernameEt;
    EditText passwordEt;
    Button loginBtn;
    Button clearBtn;
    LoginPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }

    private void initView() {
        presenter = new LoginPresenter(this);
        progressBar = (ProgressBar) findViewById(R.id.id_pb_loading);
        usernameEt = (EditText) findViewById(R.id.editText1);
        passwordEt = (EditText) findViewById(R.id.editText2);
        loginBtn = (Button) findViewById(R.id.button1);
        loginBtn.setOnClickListener(this);
        clearBtn = (Button) findViewById(R.id.button2);
        clearBtn.setOnClickListener(this);
    }

    @Override
    public String getUsername() {
        return usernameEt.getText().toString();
    }

    @Override
    public String getPassword() {
        return passwordEt.getText().toString();
    }

    @Override
    public Context getContext() {
        return this;
    }

    @Override
    public void clearUsername() {
        usernameEt.setText(null);
    }

    @Override
    public void clearPassword() {
        passwordEt.setText(null);
    }

    @Override
    public void showProgress() {
        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void dismissProgress() {
        progressBar.setVisibility(View.GONE);
    }

    @Override
    public void showSnack(String text) {
        Snackbar.make(findViewById(R.id.home), text, 1500).show();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.button1:
                presenter.login();
                break;
            case R.id.button2:
                presenter.clear();
                break;
        }
    }
}
