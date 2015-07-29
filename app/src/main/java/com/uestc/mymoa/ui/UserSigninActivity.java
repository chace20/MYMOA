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
import com.uestc.mymoa.io.SigninHandler;
import com.uestc.mymoa.io.model.RequestStatus;

import java.util.List;

/**
 * Created by chao on 2015/7/27.
 */
public class UserSigninActivity extends BaseActivity {
    private SigninHandler handler;
    private LoadingDialog dialog;

    @ViewInject(R.id.nameEdit)
    private EditText nameEdit;
    @ViewInject(R.id.phoneEdit)
    private EditText phoneEdit;
    @ViewInject(R.id.passEdit)
    private EditText passEdit;
    @ViewInject(R.id.confirmPassEdit)
    private EditText confirmPassEdit;

    @OnClick(R.id.signinButton)
    private void signinButtonClick(View v){
        int status = checkInput();
        if(status==0){
            signin();
        }else if(status==1){
            Toast.makeText(UserSigninActivity.this, "请输入内容", Toast.LENGTH_SHORT).show();
        }else if(status==2){
            Toast.makeText(UserSigninActivity.this, "两次密码输入不一致", Toast.LENGTH_SHORT).show();
        }else if(status==3){
            Toast.makeText(UserSigninActivity.this, "请输入正确的手机号", Toast.LENGTH_SHORT).show();
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
        handler = new SigninHandler();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_user_signin,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId() == R.id.menu_login){
            startActivity(new Intent(this,UserLoginActivity.class));
            finish();
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected int setRootView() {
        return R.layout.layout_user_signin;
    }

    private int checkInput(){
        String phone = phoneEdit.getText().toString();
        String pass = passEdit.getText().toString();
        String confimPass = confirmPassEdit.getText().toString();
        String name = nameEdit.getText().toString();

        if(phone == null ||name ==null || pass == null || confimPass==null ||
                "".equals(phone) || "".equals(pass) || "".equals(confimPass) || "".equals(name)) {
            return 1;
        }else if(!pass.equals(confimPass)){
            return 2;
        }else if(phone.length()!=11){
            return 3;
        }
        return 0;
    }

    private void signin(){
        RequestParams params = new RequestParams();

        final String phone = phoneEdit.getText().toString();
        String name = nameEdit.getText().toString();
        String pass = passEdit.getText().toString();
        params.addBodyParameter("phone", phone);
        params.addBodyParameter("name",name);
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
                    UserUtil.setLoginState(UserSigninActivity.this, true);
                    UserUtil.setUserId(UserSigninActivity.this, phone);
                    Id.userId =phone;

                    startActivity(new Intent(UserSigninActivity.this, MainActivity.class));
                    finish();
                } else {
                    Toast.makeText(UserSigninActivity.this, "用户已存在", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(String error) {
                dialog.dismiss();
                Toast.makeText(UserSigninActivity.this, "网络错误,请重试", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
