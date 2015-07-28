package com.uestc.mymoa.io;

import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.http.RequestParams;
import com.lidroid.xutils.http.client.HttpRequest;
import com.uestc.mymoa.constant.Api;

/**
 * Created by nothisboy on 2015/7/28.
 */
public class DocQueryDocContent extends IOHandler {
    protected IOCallback callback;

    @Override
    public void process(RequestParams params, IOCallback ioCallback) {
        this.callback = ioCallback;
        HttpUtils http = new HttpUtils();
//        http.send(HttpRequest.HttpMethod.GET, Api.Doc.queryDocContent,)
    }
}
