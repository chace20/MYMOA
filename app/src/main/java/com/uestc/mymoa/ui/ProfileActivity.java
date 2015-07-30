package com.uestc.mymoa.ui;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.lidroid.xutils.http.RequestParams;
import com.uestc.mymoa.R;
import com.uestc.mymoa.common.util.UserUtil;
import com.uestc.mymoa.common.view.InputDialog;
import com.uestc.mymoa.constant.Id;
import com.uestc.mymoa.io.ContactHandler;
import com.uestc.mymoa.io.IOCallback;
import com.uestc.mymoa.io.model.RequestStatus;

import java.util.HashMap;
import java.util.List;

/**
 * Created by SinLapis on 2015/7/29.
 */
public class ProfileActivity extends BaseActivity{
    private TextView nameText;
    private TextView phonenumText;
    private Button logoutButton;
    private String uid;
    private HashMap<String, Object> map = new HashMap<String, Object>();

    private void getContactDetail(){

        RequestParams params = new RequestParams();

        params.addQueryStringParameter("uid", uid);

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
                nameText.setText(map.get("uname").toString());
                phonenumText.setText(map.get("uid").toString());
            }

            @Override
            public void onFailure(String error) {

            }
        });
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        uid = Id.userId;
        nameText = (TextView) findViewById(R.id.unameText);
        phonenumText = (TextView) findViewById(R.id.phonenumText);
        logoutButton = (Button) findViewById(R.id.delcontactButton);
        logoutButton.setText("注销");

        logoutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                UserUtil.setUserId(ProfileActivity.this, "-1");
                UserUtil.setLoginState(ProfileActivity.this, false);
            }
        });
        getContactDetail();
    }

    @Override
    protected void initLayout() {
        actionbar.setDisplayHomeAsUpEnabled(true);
    }

    @Override
    protected void initListener() {

    }

    @Override
    protected void initValue() {

    }

    @Override
    protected int setRootView() {
        return R.layout.layout_contactdetail;
    }
}
