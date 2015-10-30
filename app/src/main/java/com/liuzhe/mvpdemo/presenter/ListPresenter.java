package com.liuzhe.mvpdemo.presenter;

import android.os.Handler;

import com.liuzhe.mvpdemo.R;
import com.liuzhe.mvpdemo.model.ImageItem;
import com.liuzhe.mvpdemo.view.IListView;

import java.util.Random;


/**
 * Created by liuzhe on 2015/10/30.
 */
public class ListPresenter {

    private Random random;
    private IListView iListView;
    private Handler handler;

    public ListPresenter(IListView iListView) {
        random = new Random();
        this.iListView = iListView;
        handler = new Handler(iListView.getContext().getMainLooper());
    }

    public void getItems() {
        iListView.showProgress();
        new Thread(new Runnable() {
            @Override
            public void run() {
                if(random.nextBoolean()) {
                    for(int i = 0; i < 30; i++) {
                        iListView.getData().add(new ImageItem(R.mipmap.ic_launcher, "item " + i, "intro " + i));
                    }
                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            iListView.dismissProgress();
                                iListView.loadSuccess();

                        }
                    });
                } else {
                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            iListView.dismissProgress();
                            iListView.loadFailed();

                        }
                    });
                }




            }
        }).start();
    }

    public void onItemClick(int position, String name) {
        iListView.changeName(position, name);
        iListView.notifyDataSetChanged();
    }

}
