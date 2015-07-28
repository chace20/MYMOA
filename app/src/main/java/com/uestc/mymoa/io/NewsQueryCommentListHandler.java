package com.uestc.mymoa.io;

import com.google.gson.Gson;
import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.RequestParams;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest;
import com.uestc.mymoa.constant.Api;
import com.uestc.mymoa.io.model.Comment;
import com.uestc.mymoa.io.model.RequestStatus;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by nothisboy on 2015/7/28.
 */
public class NewsQueryCommentListHandler extends IOHandler {

    protected IOCallback callback;

    @Override
    public void process(RequestParams params, IOCallback ioCallback) {
        this.callback = ioCallback;

        HttpUtils http = new HttpUtils();

        http.send(HttpRequest.HttpMethod.GET, Api.News.queryCommentList, params,
                new RequestCallBack<String>() {

                    @Override
                    public void onStart() {
                        callback.onStart();
                    }

                    @Override
                    public void onLoading(long total, long current, boolean isUploading) {
                    }

                    @Override
                    public void onSuccess(ResponseInfo<String> responseInfo) {
                        List<Comment> list = new ArrayList<>();
                        String result = String.valueOf(responseInfo);
                        if (result.indexOf("[") == 0) {
                            try {
                                JSONArray jsonArray = new JSONArray(responseInfo);
                                for (int i = 0; i < jsonArray.length(); i++) {
                                    Comment comment = new Comment();
                                    comment.newsid = (String) jsonArray.getJSONObject(i).get("newsid");
                                    comment.content = (String) jsonArray.getJSONObject(i).get("content");
                                    list.add(comment);
                                }

                                callback.onSuccess(list);
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }else{
                            Gson gson = new Gson();
                            RequestStatus status = gson.fromJson(responseInfo.result,RequestStatus.class);
                            callback.onSuccess(status);
                        }

                    }

                    @Override
                    public void onFailure(HttpException e, String s) {
                        callback.onFailure(s);
                    }

                });
    }
}
