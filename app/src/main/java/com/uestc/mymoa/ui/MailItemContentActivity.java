package com.uestc.mymoa.ui;

import android.content.Intent;
import android.widget.TextView;

import com.lidroid.xutils.http.RequestParams;
import com.uestc.mymoa.R;
import com.uestc.mymoa.constant.Id;
import com.uestc.mymoa.io.IOCallback;
import com.uestc.mymoa.io.MailQueryMailContentHandler;
import com.uestc.mymoa.io.UserQueryUserHandler;

import org.w3c.dom.Text;

import java.util.HashMap;
import java.util.List;
import java.util.Objects;

/**
 * Created by nothisboy on 2015/7/29.
 */
public class MailItemContentActivity extends BaseActivity {

    private MailQueryMailContentHandler handler;

    private TextView tagText;
    private TextView unameText;
    private TextView uidText;
    private TextView timeText;
    private TextView contentText;

    @Override
    protected void initLayout() {
        tagText = (TextView) findViewById(R.id.tagMailContentText);
        unameText = (TextView) findViewById(R.id.unameMailContentText);
        uidText = (TextView) findViewById(R.id.uidMailContentText);
        timeText = (TextView) findViewById(R.id.timeMailContentText);
        contentText = (TextView) findViewById(R.id.contentMailContentText);
    }

    @Override
    protected void initListener() {

    }

    @Override
    protected void initValue() {
        handler = new MailQueryMailContentHandler();
        getData();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Override
    protected int setRootView() {
        return R.layout.layout_mail_content;
    }

    private void getData() {

        Intent intent = getIntent();
        RequestParams params = new RequestParams();
        params.addQueryStringParameter("mailid", intent.getStringExtra("mailid"));
        handler.process(params, new IOCallback() {
            @Override
            public void onStart() {
            }

            @Override
            public void onSuccess(List result) {

            }

            @Override
            public void onSuccess(Object result) {
                HashMap<String, String> map = (HashMap<String, String>) result;
                initView(map);
            }

            @Override
            public void onFailure(String error) {
            }
        });

    }

    private void initView(HashMap<String, String> map) {
        if (map.get("fromuid").equals(Id.userId)) {
            tagText.setText("发给");

            uidText.setText(map.get("touid"));
            timeText.setText(map.get("time"));
            contentText.setText(map.get("content"));
            RequestParams params = new RequestParams();
            params.addQueryStringParameter("id", map.get("touid"));
            new UserQueryUserHandler().process(params, new IOCallback() {
                @Override
                public void onStart() {
                }

                @Override
                public void onSuccess(List result) {
                }

                @Override
                public void onSuccess(Object result) {
                    unameText.setText((CharSequence) ((HashMap<String, Object>) result).get("uname"));
                }

                @Override
                public void onFailure(String error) {

                }
            });
        } else {
            tagText.setText("来自");
            uidText.setText(map.get("fromuid"));
            timeText.setText(map.get("time"));
            contentText.setText(map.get("content"));
            RequestParams params = new RequestParams();
            params.addQueryStringParameter("id", map.get("fromuid"));
            new UserQueryUserHandler().process(params, new IOCallback() {
                @Override
                public void onStart() {
                }

                @Override
                public void onSuccess(List result) {
                }

                @Override
                public void onSuccess(Object result) {
                    unameText.setText((CharSequence) ((HashMap<String, Object>) result).get("uname"));
                }

                @Override
                public void onFailure(String error) {

                }
            });
        }

    }
}