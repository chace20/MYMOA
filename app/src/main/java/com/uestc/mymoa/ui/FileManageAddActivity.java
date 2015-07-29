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
import com.uestc.mymoa.io.model.RequestStatus;

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
        actionbar.setDisplayHomeAsUpEnabled(true);
        btn_cre_save=(Button)findViewById(R.id.saveButton);
        file_add_title=(EditText)findViewById(R.id.titleEdit);
        file_add_text=(EditText)findViewById(R.id.contentEdit);

    }

    @Override
    protected void initListener() {
        btn_cre_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(checkInput()){
                    toSave();
                }else{
                    Toast.makeText(FileManageAddActivity.this, "请输入内容", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    @Override
    protected void initValue() {

    }

    @Override
    protected int setRootView() {
        return R.layout.layout_filemanage_createnew;
    }
    private void toSave(){
        RequestParams params=new RequestParams();
        final String title=file_add_title.getText().toString();
        String text=file_add_text.getText().toString();
        params.addBodyParameter("title", title);
        params.addBodyParameter("content", text);
        params.addBodyParameter("uid", Id.userId);
        params.addBodyParameter("altertime", ToolUtil.getCurrentTime(ToolUtil.TIME_COMMON));
        new DocAddDocHandler().process(params, new IOCallback() {
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
                    Toast.makeText(FileManageAddActivity.this, "文档已添加", Toast.LENGTH_SHORT).show();
                    finish();
                }else{
                    Toast.makeText(FileManageAddActivity.this, "添加失败", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(String error) {
                Toast.makeText(FileManageAddActivity.this, "网络错误", Toast.LENGTH_SHORT).show();
            }
        });
    }


    private boolean checkInput(){
        String title = file_add_title.getText().toString();
        String content = file_add_text.getText().toString();
        if(title == null || content == null || "".equals(title) || "".equals(content)){
            return false;
        }
        return true;
    }
}
