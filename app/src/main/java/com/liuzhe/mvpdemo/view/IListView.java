package com.liuzhe.mvpdemo.view;

import com.liuzhe.mvpdemo.model.ImageItem;

import java.util.List;

/**
 * Created by liuzhe on 2015/10/30.
 */
public interface IListView extends IBaseView {
    void showProgress();
    void dismissProgress();
    void loadSuccess();
    void loadFailed();
    void changeName(int position, String name);
    void notifyDataSetChanged();
    List<ImageItem> getData();
}
