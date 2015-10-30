package com.liuzhe.mvpdemo.presenter;

import android.os.Handler;
import android.text.TextUtils;

import com.liuzhe.mvpdemo.R;
import com.liuzhe.mvpdemo.view.ILoginView;


/**
 * 登录接口
 * Created by liuzhe on 2015/10/30.
 */
public class LoginPresenter {

    ILoginView iLoginView;
    Handler handler;

    public LoginPresenter(ILoginView iLoginView) {
        this.iLoginView = iLoginView;
        handler = new Handler(iLoginView.getContext().getMainLooper());
    }

    public void login() {
        String username = iLoginView.getUsername();
        String password = iLoginView.getPassword();
        if (TextUtils.isEmpty(username)) {
            iLoginView.showSnack(iLoginView.getContext().getString(R.string.empty_username));
            return;
        }
        if (TextUtils.isEmpty(password)) {
            iLoginView.showSnack(iLoginView.getContext().getString(R.string.empty_password));
            return;
        }

        iLoginView.showProgress();

        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                iLoginView.dismissProgress();
                if (iLoginView.getUsername().equals(iLoginView.getPassword())) {
                    iLoginView.showSnack(iLoginView.getContext().getString(R.string.login_success));
                } else {
                    iLoginView.showSnack(iLoginView.getContext().getString(R.string.login_failed));
                }
            }
        }, 2000);

    }

    public void clear() {
        iLoginView.clearUsername();
        iLoginView.clearPassword();
    }

}
