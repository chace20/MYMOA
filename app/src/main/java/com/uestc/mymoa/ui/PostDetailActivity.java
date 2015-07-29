package com.uestc.mymoa.ui;

import android.content.Intent;
import android.widget.TextView;
import android.widget.Toast;

import com.lidroid.xutils.http.RequestParams;
import com.uestc.mymoa.R;
import com.uestc.mymoa.io.IOCallback;
import com.uestc.mymoa.io.PostQueryPostContentHandler;

import java.util.HashMap;
import java.util.List;

/**
 * Created by nothisboy on 2015/7/29.
 */
public class PostDetailActivity extends BaseActivity {

    private TextView postTitleText;
    private TextView postUnameText;
    private TextView postStartTimeText;
    private TextView postEndTimeText;
    private TextView postContentText;

    @Override
    protected void initLayout() {
        postTitleText = (TextView) findViewById(R.id.postTitleText);
        postUnameText = (TextView) findViewById(R.id.postUnameText);
        postStartTimeText = (TextView) findViewById(R.id.postStartTimeText);
        postEndTimeText = (TextView) findViewById(R.id.postEndTimeText);
        postContentText = (TextView) findViewById(R.id.postContentText);
    }

    @Override
    protected void initListener() {
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Override
    protected void initValue() {
        getData();
    }

    @Override
    protected int setRootView() {
        return R.layout.layout_post_content;
    }

    private void getData() {
        Intent intent = getIntent();
        RequestParams params = new RequestParams();
        params.addQueryStringParameter("postid", intent.getStringExtra("postid"));
        new PostQueryPostContentHandler().process(params, new IOCallback() {
            @Override
            public void onStart() {
            }

            @Override
            public void onSuccess(List result) {
            }

            @Override
            public void onSuccess(Object result) {
                HashMap<String, String> map = (HashMap<String, String>) result;
                if (map.get("code") != null) {
                    Toast.makeText(PostDetailActivity.this,
                            "由于某种原因，没有找到公告", Toast.LENGTH_SHORT).show();
                } else {
                    postTitleText.setText(map.get("title"));
                    postUnameText.setText(map.get("uname"));
                    postStartTimeText.setText(map.get("starttime"));
                    postEndTimeText.setText(map.get("endtime"));
                    postContentText.setText(map.get("contemt"));
                }
            }

            @Override
            public void onFailure(String error) {

            }
        });
    }
}
