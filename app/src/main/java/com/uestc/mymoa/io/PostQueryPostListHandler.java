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

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by nothisboy on 2015/7/28.
 */
public class PostQueryPostListHandler extends IOHandler {

    protected IOCallback callback;

    @Override
    public void process(RequestParams params, IOCallback ioCallback) {
        this.callback = ioCallback;
        HttpUtils http = new HttpUtils();
        http.send(HttpRequest.HttpMethod.GET, Api.Post.queryPostList, params, new RequestCallBack<String>() {
            @Override
            public void onStart() {
                callback.onStart();
            }

            @Override
            public void onLoading(long total, long current, boolean isUploading) {
            }

            @Override
            public void onSuccess(ResponseInfo<String> responseInfo) {
                List<PostContent> list = new ArrayList<PostContent>();
                String result = String.valueOf(responseInfo);
                if (result.indexOf("[") != -1) {
                    try {
                        JSONArray jsonArray = new JSONArray(responseInfo);
                        for (int i = 0; i < jsonArray.length(); i++) {
                            JSONObject jsonObject = jsonArray.getJSONObject(i);
                            PostContent postContent = new PostContent();
                            postContent.postid = (String) jsonObject.get("postid");
                            postContent.title = (String) jsonObject.get("title");
                            postContent.uname = (String) jsonObject.get("uname");
                            postContent.starttime = (String) jsonObject.get("starttime");
                            postContent.endtime = (String) jsonObject.get("endtime");
                            list.add(postContent);
                        }
                        callback.onSuccess(list);
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
