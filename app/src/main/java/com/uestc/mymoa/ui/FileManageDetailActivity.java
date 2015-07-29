package com.uestc.mymoa.ui;

import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.TextView;

import com.lidroid.xutils.http.RequestParams;
import com.uestc.mymoa.R;
import com.uestc.mymoa.io.DocQueryDocContentHandler;
import com.uestc.mymoa.io.IOCallback;
import com.uestc.mymoa.io.model.DocContent;

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
        actionbar.setDisplayHomeAsUpEnabled(true);
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

        new DocQueryDocContentHandler().process(params, new IOCallback() {
            @Override
            public void onStart() {

            }

            @Override
            public void onSuccess(List result) {


            }

            @Override
            public void onSuccess(Object result) {
                DocContent doc = (DocContent)result;
                actionbar.setTitle(doc.title);
                uname.setText(doc.uname);
                content.setText(doc.content);
                time.setText(doc.altertime);
                text=doc.content;
            }

            @Override
            public void onFailure(String error) {

            }
        });
    }

}
