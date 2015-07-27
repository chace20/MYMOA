package com.uestc.mymoa.ui;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.http.RequestParams;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.lidroid.xutils.view.annotation.event.OnClick;
import com.uestc.mymoa.R;
import com.uestc.mymoa.io.IOCallback;
import com.uestc.mymoa.io.LoginHandler;
import com.uestc.mymoa.io.model.PostReqStatus;

import java.util.List;

/**
 * Created by chao on 2015/7/27.
 */
public class LoginActivity extends BaseActivity{

    @ViewInject(R.id.phoneEdit)
    private EditText phoneEdit;
    @ViewInject(R.id.passEdit)
    private EditText passEdit;

    @OnClick(R.id.loginButton)
    private void loginButtonClick(View v){
        login();
    }

    @Override
    protected void initLayout() {
        ViewUtils.inject(this);
    }

    @Override
    protected void initListener() {

    }

    @Override
    protected void initValue() {

    }

    @Override
    protected int setRootView() {
        return R.layout.layout_login;
    }

    private void login(){
        RequestParams params = new RequestParams();

        String phone = phoneEdit.getText().toString();
        String pass = passEdit.getText().toString();
        params.addQueryStringParameter("phone", phone);
        params.addQueryStringParameter("password",pass);

        new LoginHandler().process(params, new IOCallback<PostReqStatus>() {
            @Override
            public void onStart() {
                Toast.makeText(LoginActivity.this,"start",Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onSuccess(List<PostReqStatus> result) {

            }

            @Override
            public void onSuccess(PostReqStatus result) {
                if(result.code==200){
                    Toast.makeText(LoginActivity.this,"success",Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(LoginActivity.this,"password or phone error",Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(String error) {
                Toast.makeText(LoginActivity.this,"internet error",Toast.LENGTH_SHORT).show();
            }
        });
    }
}
