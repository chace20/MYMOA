package com.uestc.mymoa.ui;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import com.lidroid.xutils.http.RequestParams;
import com.uestc.mymoa.R;
import com.uestc.mymoa.io.DocQueryDocContentHandler;
import com.uestc.mymoa.io.IOCallback;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by hui on 2015/7/26.
 */
public class FileManageDetailActivity extends BaseActivity{
    private Button but_detail_edit;
    private int myfileId;
    private List<Map<String,Object>> mylistmap=new ArrayList<>();
    private TextView time;
    private TextView uname;
    private TextView content;
    private String text;
    @Override
    protected void initLayout() {
        uname=(TextView)findViewById(R.id.file_det_tv_author);
        time=(TextView)findViewById(R.id.file_det_tv_time);
        content=(TextView)findViewById(R.id.file_det_tv_text);
    }

    @Override
    protected void initListener() {

    }

    @Override
    protected void initValue() {
        myfileId=getIntent().getIntExtra("docid",0);
        reflash(myfileId);
    }

    @Override
    protected int setRootView() {
        return R.layout.layout_filemanage_detail;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_filemanage_detail, menu);
        return super.onCreateOptionsMenu(menu);
    }

    /**
     * */

    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.action_edit:
            startActivity(FileManageEditActivity.editIntent(FileManageDetailActivity.this,myfileId,text));
            break;
        }
        return super.onOptionsItemSelected(item);
    }
    /***/
    public void reflash(int docid){
        RequestParams params=new RequestParams();
        params.addQueryStringParameter("docid",""+docid);

        new DocQueryDocContentHandler().process(params, new IOCallback<Map<String,Object>>() {
            @Override
            public void onStart() {

            }

            @Override
            public void onSuccess(List<Map<String,Object>> result) {


            }

            @Override
            public void onSuccess(Map<String,Object> result) {
                Log.e("activity result--","--"+result.get("uname"));
                uname.setText(result.get("uname").toString());
                content.setText(result.get("content").toString());
                time.setText(result.get("altertime").toString());
                text=result.get("content").toString();
            }

            @Override
            public void onFailure(String error) {

            }
        });
    }

}
