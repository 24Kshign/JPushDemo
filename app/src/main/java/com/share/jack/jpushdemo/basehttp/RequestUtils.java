package com.share.jack.jpushdemo.basehttp;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.RequestParams;

/**
 * Author:Created by JackCheng
 */

//封装AsyncHttp类
public class RequestUtils {

    public static AsyncHttpClient client = new AsyncHttpClient();

    public static void ClientGet(String url, RequestParams params, NetCallBack ncb) {
        client.get(url, params, ncb);
        client.setTimeout(5000);
    }

    public static void ClientPost(String url, RequestParams params, NetCallBack ncb) {
        client.post(url, params, ncb);
        client.setTimeout(5000);
    }
}
