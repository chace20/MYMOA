package com.uestc.mymoa.ui;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.lidroid.xutils.http.RequestParams;
import com.uestc.mymoa.R;
import com.uestc.mymoa.io.ContactHandler;
import com.uestc.mymoa.io.IOCallback;
import com.uestc.mymoa.io.model.RequestStatus;

import java.util.HashMap;
import java.util.List;

/**
 * Created by SinLapis on 2015/7/26.
 */
public class ContactDetailActivity extends Activity {

    private TextView nameText;
    private TextView phonenumText;
    private Button delcontactButton;
    private int uid;
    private HashMap<String, Object> map = new HashMap<String, Object>();

    private void getContactDetail(){

        RequestParams params = new RequestParams();

        params.addQueryStringParameter("uid", String.valueOf(uid));

        new ContactHandler().getContactDetail(params, new IOCallback<HashMap<String, Object>>() {
            @Override
            public void onStart() {

            }

            @Override
            public void onSuccess(List<HashMap<String, Object>> result) {

            }

            @Override
            public void onSuccess(HashMap<String, Object> result) {
                map = result;
            }

            @Override
            public void onFailure(String error) {

            }
        });
    }
    private void delContact(){

        RequestParams params = new RequestParams();

        params.addQueryStringParameter("uid", String.valueOf(uid));

        new ContactHandler().delContact(params, new IOCallback<RequestStatus>() {
            @Override
            public void onStart() {
            }

            @Override
            public void onSuccess(List<RequestStatus> result) {

            }

            @Override
            public void onSuccess(RequestStatus result) {
                if (result.code == 200) {
                    Toast.makeText(ContactDetailActivity.this, "删除联系人成功",
                            Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(ContactDetailActivity.this, "删除联系人失败",
                            Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(String error) {
            }
        });
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        Intent intent = getIntent();
        uid = intent.getIntExtra("uid", -1);
        setContentView(R.layout.layout_contactdetail);
        nameText = (TextView) findViewById(R.id.nameText);
        phonenumText = (TextView) findViewById(R.id.phonenumText);
        delcontactButton = (Button) findViewById(R.id.delcontactButton);

        delcontactButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                new AlertDialog.Builder(ContactDetailActivity.this)
                        .setTitle("删除联系人")
                        .setMessage("确定要删除该联系人吗？")
                        .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                delContact();
                            }
                        })
                        .setNegativeButton("取消", null)
                        .show();
            }
        });
    }
    @Override
    protected void onResume() {
        getContactDetail();
        if(Integer.parseInt(map.get("uid").toString()) != -1){
            nameText.setText(map.get("uname").toString());
            phonenumText.setText(map.get("phonenum").toString());
        }
    }
}
