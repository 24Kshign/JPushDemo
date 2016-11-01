package com.share.jack.jpushdemo.callback;

/**
 * Created by Jack on 16/10/28.
 */
public interface HttpCallback<T> {

    void onSuccess(T data);

    void onFailure(String s);

}