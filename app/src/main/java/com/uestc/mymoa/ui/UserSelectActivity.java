package com.uestc.mymoa.ui;

import android.content.Intent;
import android.view.View;

import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.event.OnClick;
import com.uestc.mymoa.R;

/**
 * Created by chao on 2015/7/27.
 * 进去第一个页面选择登录还是注册
 */
public class UserSelectActivity extends BaseActivity {

    @OnClick(R.id.signinButton)
    private void siginClick(View v) {
        startActivity(new Intent(UserSelectActivity.this, UserSigninActivity.class));
    }

    @OnClick(R.id.loginButton)
    private void loginClick(View v) {
        startActivity(new Intent(UserSelectActivity.this, UserLoginActivity.class));
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
        return R.layout.layout_user_select;
    }
}
