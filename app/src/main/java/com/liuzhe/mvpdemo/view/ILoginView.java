package com.liuzhe.mvpdemo.view;

/**
 * Created by liuzhe on 2015/10/30.
 */
public interface ILoginView extends IBaseView {

    String getUsername();
    String getPassword();
    void clearUsername();
    void clearPassword();
    void showProgress();
    void dismissProgress();
    void showSnack(String text);
}
