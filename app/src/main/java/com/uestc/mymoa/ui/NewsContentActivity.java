package com.uestc.mymoa.ui;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.lidroid.xutils.http.RequestParams;
import com.uestc.mymoa.R;
import com.uestc.mymoa.common.view.InputDialog;
import com.uestc.mymoa.constant.Id;
import com.uestc.mymoa.io.IOCallback;
import com.uestc.mymoa.io.NewsAddCommentHandler;
import com.uestc.mymoa.io.NewsQueryCommentListHandler;
import com.uestc.mymoa.io.NewsQueryNewsContent;
import com.uestc.mymoa.io.model.RequestStatus;

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
                    map = result;

                    newsContentTitleText.setText(map.get("title").toString());
                    newsContentAuthorText.setText(map.get("uname").toString());
                    newsContentNewsText.setText(map.get("content").toString());
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
        newsCommentList.setAdapter(new SimpleAdapter(NewsContentActivity.this,
                list, R.layout.item_news_comment,
                new String[]{"uname", "content"},
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
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_news_comment, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId()==R.id.action_comment){
            final InputDialog inputDialog = new InputDialog(NewsContentActivity.this);
            inputDialog.setInputEditHint("输入评论");
            inputDialog.buider
                    .setTitle("评论")
                    .setPositiveButton("发送", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            String comment = inputDialog.getInputEditText();
                            if(comment==null||"".equals(comment)){
                                Toast.makeText(NewsContentActivity.this,"请输入内容",Toast.LENGTH_SHORT).show();
                            }else{
                                addComment(comment);
                            }
                        }
                    })
                    .setNegativeButton("取消", null)
                    .show();
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected int setRootView() {
        return R.layout.layout_news_content;
    }

    private void addComment(String comment){

        RequestParams params = new RequestParams();

        params.addBodyParameter("newsid", newsid);
        params.addBodyParameter("uid", Id.userId);
        params.addBodyParameter("content", comment);

        new NewsAddCommentHandler().process(params, new IOCallback() {
            @Override
            public void onStart() {

            }

            @Override
            public void onSuccess(List result) {

            }

            @Override
            public void onSuccess(Object result) {
                RequestStatus status = (RequestStatus)result;
                if(status.code==200){
                    Toast.makeText(NewsContentActivity.this,"评论成功",Toast.LENGTH_SHORT).show();
                    getNewsContentAndComment();
                }else{
                    Toast.makeText(NewsContentActivity.this,"评论失败",Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(String error) {
                Toast.makeText(NewsContentActivity.this,"网络错误."+error,Toast.LENGTH_SHORT).show();
            }
        });
    }
}
