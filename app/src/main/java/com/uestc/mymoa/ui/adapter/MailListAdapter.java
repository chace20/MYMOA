package com.uestc.mymoa.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.lidroid.xutils.http.RequestParams;
import com.uestc.mymoa.R;
import com.uestc.mymoa.constant.Id;
import com.uestc.mymoa.io.IOCallback;
import com.uestc.mymoa.io.UserQueryUserHandler;
import com.uestc.mymoa.ui.BaseActivity;

import java.util.HashMap;
import java.util.List;

/**
 * Created by nothisboy on 2015/7/29.
 */
public class MailListAdapter extends BaseAdapter {

    private Context context;
    private List<HashMap<String, Object>> list;

    public MailListAdapter(Context context, List<HashMap<String, Object>> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = LayoutInflater.from(context);
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.item_mail_history, null);
        }

        TextView tagText = (TextView) convertView.findViewById(R.id.tagText);
        final TextView mailUnameText = (TextView) convertView.findViewById(R.id.mailUnameText);
        TextView mailContentText = (TextView) convertView.findViewById(R.id.mailContentText);
        TextView mailTimeText = (TextView) convertView.findViewById(R.id.mailTimeText);

        if (list.get(position).get("fromuid").equals(Id.userId)) {
            tagText.setText("发给");

            mailTimeText.setText((CharSequence) list.get(position).get("time"));
            mailContentText.setText((CharSequence) list.get(position).get("content"));
            RequestParams params = new RequestParams();
            params.addQueryStringParameter("id", (String) list.get(position).get("touid"));
            new UserQueryUserHandler().process(params, new IOCallback() {
                @Override
                public void onStart() {
                }

                @Override
                public void onSuccess(List result) {
                }

                @Override
                public void onSuccess(Object result) {
                    mailUnameText.setText((CharSequence) ((HashMap<String, Object>) result).get("uname"));
                }

                @Override
                public void onFailure(String error) {

                }
            });
        } else {
            tagText.setText("来自");
            mailTimeText.setText((CharSequence) list.get(position).get("time"));
            mailContentText.setText((CharSequence) list.get(position).get("content"));
            RequestParams params = new RequestParams();
            params.addQueryStringParameter("id", (String) list.get(position).get("fromuid"));
            new UserQueryUserHandler().process(params, new IOCallback() {
                @Override
                public void onStart() {
                }

                @Override
                public void onSuccess(List result) {
                }

                @Override
                public void onSuccess(Object result) {
                    mailUnameText.setText((CharSequence) ((HashMap<String, Object>) result).get("uname"));
                }

                @Override
                public void onFailure(String error) {

                }
            });
            return convertView;
        }


        return convertView;
    }
}
