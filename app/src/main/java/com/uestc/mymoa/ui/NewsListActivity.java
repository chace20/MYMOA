package com.uestc.mymoa.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import com.lidroid.xutils.http.RequestParams;
import com.uestc.mymoa.R;
import com.uestc.mymoa.io.IOCallback;
import com.uestc.mymoa.io.NewsQueryNewsList;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by DBJ_MAC on 2015/7/27.
 */
public class NewsListActivity extends BaseActivity {


    private List<HashMap<String, Object>> list = new ArrayList<HashMap<String,Object>>();
    private ListView newsList;
    private int typeid;

    private void getNewsList(){

        RequestParams params = new RequestParams();

        params.addQueryStringParameter("typeid", typeid + "");
        new NewsQueryNewsList().process(params, new IOCallback<HashMap<String, Object>>() {
            @Override
            public void onStart() {
                //TODO
            }

            @Override
            public void onSuccess(List<HashMap<String, Object>> result) {
                    list = result;
                    refreshAdapter();
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

        newsList.setAdapter(new SimpleAdapter(NewsListActivity.this,
                list, R.layout.item_news_list,
                new String[]{"title", "uname"}, new int[]{R.id.newsTitleText, R.id.newsAuthorText}));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Intent intent = getIntent();
        typeid = intent.getIntExtra("news_category",1);

        switch (typeid){
            case 1:
                actionbar.setTitle("娱乐");
                break;
            case 2:
                actionbar.setTitle("军事");
                break;
            case 3:
                actionbar.setTitle("科技");
                break;
            case 4:
                actionbar.setTitle("体育");
                break;
        }



        newsList = (ListView)this.findViewById(R.id.newsList);
        newsList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Intent intent = new Intent(NewsListActivity.this,
                        NewsContentActivity.class);

                intent.putExtra("newsid", list.get(position).get("newsid").toString());
                startActivity(intent);
            }
        });
        getNewsList();
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
        return R.layout.layout_news_list;
    }
}
