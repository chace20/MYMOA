package com.uestc.mymoa.io;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.RequestParams;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest;
import com.uestc.mymoa.constant.Api;
import com.uestc.mymoa.io.model.RequestStatus;
import com.uestc.mymoa.io.model.User;

import java.lang.reflect.Type;
import java.util.HashMap;

/**
 * Created by nothisboy on 2015/7/29.
 */
public class UserQueryUserHandler extends IOHandler {
    protected IOCallback callback;

    @Override
    public void process(RequestParams params, IOCallback ioCallback) {
        this.callback = ioCallback;
        HttpUtils http = new HttpUtils();
        http.send(HttpRequest.HttpMethod.GET, Api.Users.query, params, new RequestCallBack<String>() {
            @Override
            public void onStart() {
                callback.onStart();
            }

            @Override
            public void onSuccess(ResponseInfo<String> responseInfo) {
                String result = String.valueOf(responseInfo.result);

                if (result.indexOf("uid") != -1) {
                    Type mapType = new TypeToken<HashMap<String, Object>>() {
                    }.getType();
                    Gson gson = new Gson();
                    HashMap<String, Object> map = gson.fromJson(responseInfo.result, mapType);
                    callback.onSuccess(map);
                } else {
                    Gson gson = new Gson();
                    RequestStatus status = gson.fromJson(responseInfo.result, RequestStatus.class);
                    callback.onSuccess(status);
                }
            }

            @Override
            public void onFailure(HttpException e, String s) {
            }
        });
    }
}
