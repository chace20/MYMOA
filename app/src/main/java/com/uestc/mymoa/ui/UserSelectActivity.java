package com.uestc.mymoa.ui;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.event.OnClick;
import com.uestc.mymoa.R;
import com.uestc.mymoa.common.util.UserUtil;
import com.uestc.mymoa.constant.BroadCastAction;
import com.uestc.mymoa.constant.Id;

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
    protected void onCreate(Bundle savedInstanceState) {
        //如果已登录查询UserId保存到userId变量中
        String uid = UserUtil.getUserId(this);
        if(!uid.equals("-1")){
            Id.userId = uid;
        }
        //如果已登录直接跳转到MainActivity
        if(UserUtil.getLoginState(this)){
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
            finish();
        }
        super.onCreate(savedInstanceState);
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
        IntentFilter filter = new IntentFilter();
        filter.addAction(BroadCastAction.ACTION_FINISH);
        registerReceiver(receiver,filter);
    }

    @Override
    protected int setRootView() {
        return R.layout.layout_user_select;
    }

    BroadcastReceiver receiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            unregisterReceiver(receiver);
            finish();
        }
    };

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterReceiver(receiver);
    }
}
