package com.uestc.mymoa.io;

import com.lidroid.xutils.http.RequestParams;

/**
 * Created by chao on 2015/7/27.
 */
public abstract class IOHandler {
    public abstract void process(RequestParams params,IOCallback ioCallback);
}
