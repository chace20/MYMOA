package com.uestc.mymoa.ui;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.lidroid.xutils.http.RequestParams;
import com.uestc.mymoa.R;
import com.uestc.mymoa.common.util.ToolUtil;
import com.uestc.mymoa.io.DocEditDocHandler;
import com.uestc.mymoa.io.IOCallback;

import java.util.List;

/**
 * Created by hui on 2015/7/26.
 */
public class FileManageEditActivity extends BaseActivity{
    private String file_edit_text;
    private int file_id;
    private EditText mycontent=(EditText)findViewById(R.id.file_edi_et_text);
    private Button edit_btn=(Button)findViewById(R.id.file_btn_edi_save);
    private EditText ediContent=(EditText)findViewById(R.id.file_edi_et_text);
    @Override
    protected void initLayout() {
/***/
        file_edit_text=getIntent().getStringExtra("content");
        file_id=getIntent().getIntExtra("docid", 0);
        mycontent.setText(file_edit_text);
    }

    @Override
    protected void initListener() {
        edit_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toSave();
                Intent intent = new Intent(FileManageEditActivity.this, FileManageActivity.class);
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
    public static Intent editIntent(Context context,int text_id,String content){
        Intent intent=new Intent();
        intent.setClass(context,FileManageEditActivity.class);
        intent.putExtra("content",content);
        intent.putExtra("docid",text_id);
        return intent;
    }

    /**
     *
     * */
    private void toSave(){
        RequestParams params=new RequestParams();
        final String editcontent=ediContent.getText().toString();
        int editid=file_id;
        String editTime= ToolUtil.getCurrentTime(ToolUtil.TIME_COMMON);

        params.addBodyParameter("docid",""+editid);
        params.addBodyParameter("content",editcontent);
        params.addBodyParameter("altertime",editTime);

        new DocEditDocHandler().process(params, new IOCallback() {
            @Override
            public void onStart() {

            }

            @Override
            public void onSuccess(List result) {

            }

            @Override
            public void onSuccess(Object result) {
                Toast.makeText(FileManageEditActivity.this, "执行中", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(String error) {

            }
        });
    }
}
