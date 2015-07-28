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

import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.List;

/**
 * Created by SinLapis on 2015/7/27.
 */
public class ContactHandler extends IOHandler{


    @Override
    public void process(RequestParams params, IOCallback ioCallback) {
        //pass
    }

    public void getGroupList(IOCallback ioCallback){

        final IOCallback<HashMap<String, Object>> callback = ioCallback;

        HttpUtils http = new HttpUtils();
        http.send(HttpRequest.HttpMethod.GET,
                Api.Contact.queryGroupList,
                new RequestCallBack<String>() {
                    @Override
                    public void onLoading(long total, long current, boolean isUploading) {

                    }

                    @Override
                    public void onStart() {
                    }

                    @Override
                    public void onSuccess(ResponseInfo<String> responseInfo) {

                        Type listType = new TypeToken<List<HashMap<String, Object>>>() {
                        }
                                .getType();
                        Gson gson = new Gson();

                        List < HashMap < String, Object >> list =
                                gson.fromJson(responseInfo.result, listType);
                        callback.onSuccess(list);
                    }

                    @Override
                    public void onFailure(HttpException error, String msg) {

                        callback.onFailure(msg);
                    }
                });
    }
    public void getGroupDetailList(RequestParams params, IOCallback ioCallback){

        final IOCallback<HashMap<String,Object>> callback = ioCallback;

        HttpUtils http = new HttpUtils();
        http.send(HttpRequest.HttpMethod.POST,
                Api.Contact.queryGroupContactList,
                params,
                new RequestCallBack<String>() {
                    @Override
                    public void onSuccess(ResponseInfo<String> responseInfo) {
                        Type listType = new TypeToken<List<HashMap<String, Object>>>() {
                        }
                                .getType();
                        Gson gson = new Gson();

                        List < HashMap < String, Object >> list =
                                gson.fromJson(responseInfo.result, listType);
                        callback.onSuccess(list);
                    }

                    @Override
                    public void onFailure(HttpException error, String msg) {

                        callback.onFailure(msg);
                    }
                });

    }
    public void getContactDetail(RequestParams params, IOCallback ioCallback){

        final IOCallback<HashMap<String,Object>> callback = ioCallback;

        HttpUtils http = new HttpUtils();
        http.send(HttpRequest.HttpMethod.POST,
                Api.Contact.queryContactContent,
                params,
                new RequestCallBack<String>() {
                    @Override
                    public void onSuccess(ResponseInfo<String> responseInfo) {
                        Type mapType = new TypeToken<HashMap<String, Object>>() {

                        }.getType();
                        Gson gson = new Gson();

                        HashMap < String, Object > map =
                                gson.fromJson(responseInfo.result, mapType);
                        callback.onSuccess(map);
                    }

                    @Override
                    public void onFailure(HttpException error, String msg) {

                        callback.onFailure(msg);
                    }
                });
    }
    public void delContact(RequestParams params, IOCallback ioCallback){

        final IOCallback<RequestStatus> callback = ioCallback;

        HttpUtils http = new HttpUtils();
        http.send(HttpRequest.HttpMethod.POST,
                Api.Contact.delContact,
                params,
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
                        Gson gson = new Gson();
                        RequestStatus status = gson.fromJson(responseInfo.result, RequestStatus.class);
                        callback.onSuccess(status);
                    }

                    @Override
                    public void onFailure(HttpException error, String msg) {
                        callback.onFailure(msg);
                    }
                });
    }
    public void addGroup(RequestParams params, IOCallback ioCallback){

        final IOCallback<RequestStatus> callback = ioCallback;

        HttpUtils http = new HttpUtils();
        http.send(HttpRequest.HttpMethod.POST,
                Api.Contact.addContactGroup,
                params,
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
                        Gson gson = new Gson();
                        RequestStatus status = gson.fromJson(responseInfo.result, RequestStatus.class);
                        callback.onSuccess(status);
                    }

                    @Override
                    public void onFailure(HttpException error, String msg) {
                        callback.onFailure(msg);
                    }
                });
    }
    public void addContact(RequestParams params, IOCallback ioCallback){


    }
    public void addGroupContact(RequestParams params, IOCallback ioCallback) {

        final IOCallback<RequestStatus> callback = ioCallback;

        HttpUtils http = new HttpUtils();
        http.send(HttpRequest.HttpMethod.POST,
                Api.Contact.addContact,
                params,
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
                        Gson gson = new Gson();
                        RequestStatus status = gson.fromJson(responseInfo.result, RequestStatus.class);
                        callback.onSuccess(status);
                    }

                    @Override
                    public void onFailure(HttpException error, String msg) {
                        callback.onFailure(msg);
                    }
                });
    }
}
