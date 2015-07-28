package com.uestc.mymoa.io;

import com.google.gson.Gson;
import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.RequestParams;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest;
import com.uestc.mymoa.constant.Api;
import com.uestc.mymoa.io.model.FileneedStatus;
import com.uestc.mymoa.io.model.RequestStatus;

/**
 * Created by hui on 2015/7/28.
 */
public class FileManageHandler extends IOHandler{
    protected IOCallback<RequestStatus> filemaincallback;
    public void process(RequestParams params,IOCallback ioCallback){
        this.filemaincallback=ioCallback;
        HttpUtils http = new HttpUtils();
        http.send(HttpRequest.HttpMethod.POST,
                Api.Doc.queryDocList,
                params,
                new RequestCallBack<String>() {
                    public void onStart(){
                        filemaincallback.onStart();
                    }
                    public void onLoading(long total, long current, boolean isUploading){

                    }
                    @Override
                    public void onSuccess(ResponseInfo<String> responseInfo) {
                        Gson gson = new Gson();
                        FileneedStatus status = gson.fromJson(responseInfo.result,  FileneedStatus.class);
                        filemaincallback.onSuccess(status);
                    }

                    @Override
                    public void onFailure(HttpException e, String s) {
                        filemaincallback.onFailure(s);
                    }
                });
    }
}
