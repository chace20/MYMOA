package com.uestc.mymoa.io;

import java.util.List;

/**
 * Created by chao on 2015/7/27.
 */
public interface IOCallback<T> {
    void onStart();

    void onSuccess(List<T> result);

    void onSuccess(T result);

    void onFailure(String error);
}
