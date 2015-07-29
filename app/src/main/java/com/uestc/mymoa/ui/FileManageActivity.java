package com.uestc.mymoa.ui;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.SimpleCursorAdapter;
import android.widget.Toast;

import com.lidroid.xutils.http.RequestParams;
import com.uestc.mymoa.R;
import com.uestc.mymoa.io.DocDelDocHanlder;
import com.uestc.mymoa.io.DocQueryDocListHandler;
import com.uestc.mymoa.io.IOCallback;
import com.uestc.mymoa.io.model.DocContent;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;

/**
 * Created by hui on 2015/7/26.
 */
public class FileManageActivity extends BaseActivity{

    private Button but_list_add;
    private Button but_list_search;
    private ListView noteList;
    private int file_note_id;
    private String file_note_title;
    private List<Map<String,Object>> fileListListmap=new ArrayList<>();
    private BaseAdapter filelistAdapter;
    private void resume(){
        RequestParams params=new RequestParams();

        new DocQueryDocListHandler().process(params, new IOCallback<Map<String,Object>>() {
            @Override
            public void onStart() {

            }

            @Override
            public void onSuccess(List<Map<String,Object>> result) {
                fileListListmap=result;
                getAdapter(fileListListmap);
                /**reflash*/
                reFlash();
            }

            @Override
            public void onSuccess(Map<String,Object> result) {

            }

            @Override
            public void onFailure(String error) {

            }
        });
    }

    @Override
    protected void initLayout() {
        noteList=(ListView)findViewById(R.id.lv_filemanage_file);
    }
    @Override
    protected void initListener() {
        /**
         * 点击进入详情页
         * */
        noteList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
/**
 * 获取文档id
 * */
                Intent intent=new Intent(FileManageActivity.this,FileManageDetailActivity.class);
                intent.putExtra("docid",(String)fileListListmap.get(position).get("docid"));
                startActivity(intent);
            }
        });

        /**
         * 长按删除
         * */
        noteList.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {

                new AlertDialog.Builder(FileManageActivity.this)
                        .setTitle("delete?")
                        .setPositiveButton("Y", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                /**
                                 * 删除操作
                                 * */
                                RequestParams params=new RequestParams();
                                params.addBodyParameter("docid",(String)fileListListmap.get(position).get("docid"));
                                new DocDelDocHanlder().process(params, new IOCallback() {
                                    @Override
                                    public void onStart() {

                                    }

                                    @Override
                                    public void onSuccess(List result) {

                                    }

                                    @Override
                                    public void onSuccess(Object result) {

                                            Toast.makeText(FileManageActivity.this,"执行中",Toast.LENGTH_SHORT).show();
                                        resume();
                                    }

                                    @Override
                                    public void onFailure(String error) {

                                    }
                                });
                            }
                        })
                        .setNegativeButton("N", null).create()
                        .show();
                return true;
            }
        });
    }

    private BaseAdapter getAdapter(List<Map<String,Object>> list){
        filelistAdapter=new SimpleAdapter(this,list,R.layout.layout_filemanage_list_item,new String[]{"docContent.title"},new int[]{R.id.file_lv_tv_text});
        return filelistAdapter;
    }
    @Override
    protected void initValue() {
        resume();
    }
    @Override
    protected int setRootView() {
        return R.layout.layout_filemanage_list;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_filemanage_list,menu);
        return super.onCreateOptionsMenu(menu);
    }

    /**
     * action bar操作
     * */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.action_addnew:
                Intent intent_add=new Intent(FileManageActivity.this,FileManageAddActivity.class);
                startActivity(intent_add);
                break;
            case R.id.action_search:
                Intent intent_search=new Intent(FileManageActivity.this,FileManageSearchActivity.class);
                startActivity(intent_search);
                break;
        }
        return super.onOptionsItemSelected(item);
    }
    public void reFlash(){
        noteList.setAdapter(getAdapter(fileListListmap));

    }

}
