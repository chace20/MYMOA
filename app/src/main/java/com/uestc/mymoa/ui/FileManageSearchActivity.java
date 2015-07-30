package com.uestc.mymoa.ui;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.SimpleCursorAdapter;
import android.widget.Toast;

import com.lidroid.xutils.http.RequestParams;
import com.uestc.mymoa.R;
import com.uestc.mymoa.io.DocSearchDocHandler;
import com.uestc.mymoa.io.IOCallback;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by hui on 2015/7/26.
 */
public class FileManageSearchActivity extends BaseActivity {
    private Button searchbtn;
    private EditText seachword;
    private ListView searchList;
    private BaseAdapter listnoteAdapter;
    private List<Map<String, Object>> mylistNotes = null;


    @Override
    protected void initLayout() {
        actionbar.setDisplayHomeAsUpEnabled(true);
        searchbtn = (Button) findViewById(R.id.file_btn_sea_search);
        searchList = (ListView) findViewById(R.id.lv_filemanage_searchfile);
        seachword = (EditText) findViewById(R.id.file_sea_et_word);
    }

    @Override
    protected void initListener() {
        searchbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                postWord();
            }

        });

        searchList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(FileManageSearchActivity.this, FileManageDetailActivity.class);

                int docid = (int)((double)mylistNotes.get(position).get("docid")+0.5);
                intent.putExtra("docid", docid);
                startActivity(intent);
            }
        });

    }

    @Override
    protected void initValue() {

    }

    @Override
    protected int setRootView() {
        return R.layout.layout_filemanage_search;
    }

    private BaseAdapter getAdapter(List<Map<String, Object>> list) {
        listnoteAdapter = new SimpleAdapter(this, list, R.layout.layout_filemanage_search_item, new String[]{"title"}, new int[]{R.id.file_lv_tv_search});
        return listnoteAdapter;
    }

    private void postWord() {
        final String word = seachword.getText().toString();
        RequestParams params = new RequestParams();
        params.addQueryStringParameter("keyword", word);
        new DocSearchDocHandler().process(params, new IOCallback<Map<String, Object>>() {
            @Override
            public void onStart() {

            }

            @Override
            public void onSuccess(List<Map<String, Object>> result) {
                Toast.makeText(FileManageSearchActivity.this, "查询完成", Toast.LENGTH_SHORT).show();
                mylistNotes = result;
                searchList.setAdapter(getAdapter(mylistNotes));
            }

            @Override
            public void onSuccess(Map<String, Object> result) {
            }

            @Override
            public void onFailure(String error) {

            }
        });
    }

}
