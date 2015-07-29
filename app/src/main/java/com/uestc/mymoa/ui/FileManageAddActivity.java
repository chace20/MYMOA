package com.uestc.mymoa.ui;

import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.lidroid.xutils.http.RequestParams;
import com.uestc.mymoa.R;
import com.uestc.mymoa.common.util.ToolUtil;
import com.uestc.mymoa.constant.Id;
import com.uestc.mymoa.io.DocAddDocHandler;
import com.uestc.mymoa.io.IOCallback;

import java.util.List;

/**
 * Created by hui on 2015/7/26.
 */
public class FileManageAddActivity extends BaseActivity{
    private Button btn_cre_save;
    private EditText file_add_title;
    private EditText file_add_text;

    @Override
    protected void initLayout() {
        btn_cre_save=(Button)findViewById(R.id.file_btn_cre_save);
        file_add_title=(EditText)findViewById(R.id.file_cre_et_title);
        file_add_text=(EditText)findViewById(R.id.file_cre_et_text);

    }

    @Override
    protected void initListener() {
        btn_cre_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                /**
                 * 保存操作
                 * **/
                toSave();
                Intent intent=new Intent(FileManageAddActivity.this,FileManageActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    protected void initValue() {

    }

    @Override
    protected int setRootView() {
        return 0;
    }
    private void toSave(){
        RequestParams params=new RequestParams();
        final String title=file_add_title.getText().toString();
        String text=file_add_text.getText().toString();
        params.addQueryStringParameter("title",title);
        params.addQueryStringParameter("content",text);
        params.addQueryStringParameter("uid", Id.userId);
        params.addQueryStringParameter("altertime", ToolUtil.getCurrentTime(ToolUtil.TIME_COMMON));
        new DocAddDocHandler().process(params, new IOCallback() {
            @Override
            public void onStart() {

            }

            @Override
            public void onSuccess(List result) {

            }

            @Override
            public void onSuccess(Object result) {
                Toast.makeText(FileManageAddActivity.this, "执行中", Toast.LENGTH_SHORT).show();

            }

            @Override
            public void onFailure(String error) {

            }
        });
    }
}
