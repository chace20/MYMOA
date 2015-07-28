package com.uestc.mymoa;

import android.app.Application;
import android.content.Intent;

import com.uestc.mymoa.common.util.UserUtil;
import com.uestc.mymoa.constant.Id;
import com.uestc.mymoa.ui.MainActivity;

/**
 * Created by chao on 2015/7/27.
 */
public class App extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        //如果已登录直接跳转到MainActivity
        if(UserUtil.getLoginState(this)){
            Intent intent = new Intent(this, MainActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
        }
        //如果已登录查询UserId保存到userId变量中
        String uid = UserUtil.getUserId(this);
        if(!uid.equals("-1")){
            Id.userId = uid;
        }
    }
}
