package com.uestc.mymoa.io;

import com.google.gson.Gson;
import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.RequestParams;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest;
import com.uestc.mymoa.constant.Api;
import com.uestc.mymoa.io.model.PostContent;
import com.uestc.mymoa.io.model.RequestStatus;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by nothisboy on 2015/7/28.
 */
public class PostQueryPostContentHandler extends IOHandler {
    protected IOCallback callback;

    @Override
    public void process(RequestParams params, IOCallback ioCallback) {
        this.callback = ioCallback;
        HttpUtils http = new HttpUtils();
        http.send(HttpRequest.HttpMethod.GET, Api.Post.queryPostContent, params, new RequestCallBack<String>() {

            @Override
            public void onStart() {
                callback.onStart();
            }

            @Override
            public void onLoading(long total, long current, boolean isUploading) {
            }

            @Override
            public void onSuccess(ResponseInfo<String> responseInfo) {
                String result = String.valueOf(responseInfo);
                if (result.indexOf("title") != -1) {
                    try {
                        JSONObject jsonObject = new JSONObject(result);
                        PostContent postContent = new PostContent();
                        postContent.title = (String) jsonObject.get("title");
                        postContent.uname = (String) jsonObject.get("uname");
                        postContent.content = (String) jsonObject.get("content");
                        postContent.starttime = (String) jsonObject.get("starttime");
                        postContent.endtime = (String) jsonObject.get("endtime");
                        callback.onSuccess(postContent);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
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
