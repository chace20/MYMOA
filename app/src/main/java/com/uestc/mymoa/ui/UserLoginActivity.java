package com.uestc.mymoa.ui;

import android.content.Intent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.http.RequestParams;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.lidroid.xutils.view.annotation.event.OnClick;
import com.uestc.mymoa.R;
import com.uestc.mymoa.common.util.UserUtil;
import com.uestc.mymoa.common.view.LoadingDialog;
import com.uestc.mymoa.constant.Id;
import com.uestc.mymoa.io.IOCallback;
import com.uestc.mymoa.io.LoginHandler;
import com.uestc.mymoa.io.model.RequestStatus;

import java.util.List;

/**
 * Created by chao on 2015/7/27.
 */
public class UserLoginActivity extends BaseActivity{
    private LoginHandler handler;
    private LoadingDialog dialog;

    @ViewInject(R.id.phoneEdit)
    private EditText phoneEdit;
    @ViewInject(R.id.passEdit)
    private EditText passEdit;

    @OnClick(R.id.loginButton)
    private void loginButtonClick(View v){
        if(checkInput()){
            login();
        }else{
            Toast.makeText(UserLoginActivity.this, "请输入内容", Toast.LENGTH_SHORT).show();
        }
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
        actionbar.setDisplayHomeAsUpEnabled(true);
        dialog = new LoadingDialog(this);
        handler=new LoginHandler();
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_user_login,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId() == R.id.menu_siginin){
            startActivity(new Intent(this,UserSigninActivity.class));
            finish();
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected int setRootView() {
        return R.layout.layout_user_login;
    }

    private boolean checkInput(){
        String phone = phoneEdit.getText().toString();
        String pass = passEdit.getText().toString();
        if(phone == null || pass == null || "".equals(phone) || "".equals(pass)){
            return false;
        }
        return true;
    }

    private void login(){
        RequestParams params = new RequestParams();

        final String phone = phoneEdit.getText().toString();
        String pass = passEdit.getText().toString();
        params.addBodyParameter("phone", phone);
        params.addBodyParameter("password",pass);

        handler.process(params, new IOCallback<RequestStatus>() {
            @Override
            public void onStart() {
                dialog.show();
            }

            @Override
            public void onSuccess(List<RequestStatus> result) {

            }

            @Override
            public void onSuccess(RequestStatus result) {
                dialog.dismiss();
                if (result.code == 200) {
                    //设置登录信息
                    UserUtil.setLoginState(UserLoginActivity.this, true);
                    UserUtil.setUserId(UserLoginActivity.this, phone);
                    Id.userId =phone;

                    startActivity(new Intent(UserLoginActivity.this, MainActivity.class));
                    finish();
                } else {
                    Toast.makeText(UserLoginActivity.this, "手机号或者密码错误,请重新输入", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(String error) {
                dialog.dismiss();
                Toast.makeText(UserLoginActivity.this, "网络错误,请重试", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
