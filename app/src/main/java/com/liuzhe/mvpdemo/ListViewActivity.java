package com.liuzhe.mvpdemo;

import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.liuzhe.mvpdemo.model.ImageItem;
import com.liuzhe.mvpdemo.presenter.ListPresenter;
import com.liuzhe.mvpdemo.view.IListView;

import java.util.ArrayList;
import java.util.List;

public class ListViewActivity extends AppCompatActivity implements IListView {

    List<ImageItem> list;
    ListView listView;
    BaseAdapter adapter;
    ListPresenter listPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_view);
        init();
    }

    private void init() {
        listPresenter = new ListPresenter(this);
        listView = (ListView) findViewById(android.R.id.list);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                listPresenter.onItemClick(position, "changed" + position);
            }
        });
        list = new ArrayList<>();
        adapter = new BaseAdapter() {
            @Override
            public int getCount() {
                return list.size();
            }

            @Override
            public ImageItem getItem(int position) {
                return list.get(position);
            }

            @Override
            public long getItemId(int position) {
                return position;
            }

            @Override
            public View getView(int position, View convertView, ViewGroup parent) {
                ViewHolder holder;
                ImageItem item = getItem(position);
                if(null == convertView) {
                    convertView = View.inflate(getContext(), R.layout.list_item, null);
                    holder = new ViewHolder();
                    holder.imageView = (ImageView) convertView.findViewById(android.R.id.icon);
                    holder.textView1 = (TextView) convertView.findViewById(android.R.id.text1);
                    holder.textView2 = (TextView) convertView.findViewById(android.R.id.text2);
                    convertView.setTag(holder);
                } else {
                    holder = (ViewHolder) convertView.getTag();
                }

                holder.imageView.setImageResource(item.getImageId());
                holder.textView1.setText(item.getName());
                holder.textView2.setText(item.getIntro());
                return convertView;
            }

            class ViewHolder {
                ImageView imageView;
                TextView textView1;
                TextView textView2;
            }

        };
        listView.setAdapter(adapter);
        listPresenter.getItems();
    }

    @Override
    public void showProgress() {
        findViewById(R.id.id_pb_loading).setVisibility(View.VISIBLE);
    }

    @Override
    public void dismissProgress() {
        findViewById(R.id.id_pb_loading).setVisibility(View.GONE);
    }

    @Override
    public void loadSuccess() {
        notifyDataSetChanged();
    }

    @Override
    public void loadFailed() {
        Snackbar.make(findViewById(android.R.id.home), getString(R.string.load_failed), 1500).show();
    }

    @Override
    public void changeName(int position, String name) {
        list.get(position).setName(name);
    }

    @Override
    public void notifyDataSetChanged() {
        adapter.notifyDataSetChanged();
    }

    @Override
    public List<ImageItem> getData() {
        return list;
    }

    @Override
    public Context getContext() {
        return this;
    }
}
