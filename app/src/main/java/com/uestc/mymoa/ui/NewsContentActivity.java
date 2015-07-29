package com.uestc.mymoa.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import com.lidroid.xutils.http.RequestParams;
import com.uestc.mymoa.R;
import com.uestc.mymoa.io.ContactHandler;
import com.uestc.mymoa.io.IOCallback;
import com.uestc.mymoa.io.NewsQueryCommentListHandler;
import com.uestc.mymoa.io.NewsQueryNewsContent;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by DBJ_MAC on 2015/7/27.
 */
public class NewsContentActivity extends BaseActivity {


    private TextView newsContentTitleText;
    private TextView newsContentAuthorText;
    private TextView newsContentNewsText;
    private ListView newsCommentList;

    private String newsid;
    private HashMap<String, Object> map = new HashMap<String, Object>();
    private List<HashMap<String, Object>> list = new ArrayList<HashMap<String, Object>>();


    private void getNewsContentAndComment(){

        RequestParams params = new RequestParams();

        params.addQueryStringParameter("newsid", newsid);

        new NewsQueryNewsContent().process(params, new IOCallback<HashMap<String, Object>>() {
            @Override
            public void onStart() {

            }

            @Override
            public void onSuccess(List<HashMap<String, Object>> result) {

            }

            @Override
            public void onSuccess(HashMap<String, Object> result) {
                if(!result.get("code").equals("1")) {

                    map = result;

                    newsContentTitleText.setText(map.get("title").toString());
                    newsContentAuthorText.setText(map.get("uname").toString());
                    newsContentNewsText.setText(map.get("content").toString());
                }
            }

            @Override
            public void onFailure(String error) {

            }
        });

        new NewsQueryCommentListHandler().process(params, new IOCallback<HashMap<String, Object>>() {
            @Override
            public void onStart() {

            }

            @Override
            public void onSuccess(List<HashMap<String, Object>> result) {
                if(!result.get(0).get("code").equals("1")) {

                    list = result;
                    refreshAdapter();
                }
            }

            @Override
            public void onSuccess(HashMap<String, Object> result) {

            }

            @Override
            public void onFailure(String error) {

            }
        });
    }

    private void refreshAdapter(){
        newsCommentList.setAdapter(new SimpleAdapter(NewsContentActivity.this,
                list, R.layout.item_news_comment,
                new String[]{"uanme", "content"},
                new int[]{R.id.newsCommentAuthorText, R.id.newsCommentContentText}));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Intent intent = getIntent();
        newsid = intent.getStringExtra("newsid");

        newsContentTitleText = (TextView) findViewById(R.id.newsContentTitleText);
        newsContentAuthorText = (TextView) findViewById(R.id.newsContentAuthorText);
        newsContentNewsText = (TextView) findViewById(R.id.newsContentNewsText);
        newsCommentList = (ListView) findViewById(R.id.newsCommentList);

        getNewsContentAndComment();
    }

    @Override
    protected void initLayout() {

    }

    @Override
    protected void initListener() {

    }

    @Override
    protected void initValue() {
        actionbar.setDisplayHomeAsUpEnabled(true);
    }

    @Override
    protected int setRootView() {
        return R.layout.layout_news_content;
    }
}
