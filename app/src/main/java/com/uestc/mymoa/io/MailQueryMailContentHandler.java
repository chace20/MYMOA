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

import java.lang.reflect.Type;
import java.util.HashMap;

/**
 * Created by nothisboy on 2015/7/28.
 */
public class MailQueryMailContentHandler extends IOHandler {
    protected IOCallback callback;

    @Override
    public void process(RequestParams params, IOCallback ioCallback) {
        this.callback = ioCallback;
        HttpUtils http = new HttpUtils();
        http.send(HttpRequest.HttpMethod.GET, Api.Mail.queryMailContent, params, new RequestCallBack<String>() {
            @Override
            public void onStart() {
                callback.onStart();
            }

            @Override
            public void onLoading(long total, long current, boolean isUploading) {
            }

            @Override
            public void onSuccess(ResponseInfo<String> responseInfo) {
                Type type = new TypeToken<HashMap<String, String>>() {
                }.getType();
                Gson gson = new Gson();
                HashMap<String, Object> map = gson.fromJson(responseInfo.result, type);
                callback.onSuccess(map);
            }

            @Override
            public void onFailure(HttpException e, String s) {
                callback.onFailure(s);
            }
        });
    }
}
