package com.share.jack.jpushdemo.http;

import com.loopj.android.http.RequestParams;
import com.share.jack.jpushdemo.basehttp.BaseHttpPost;
import com.share.jack.jpushdemo.basehttp.NetCallBack;
import com.share.jack.jpushdemo.basehttp.RequestUtils;
import com.share.jack.jpushdemo.callback.HttpCallback;
import com.share.jack.jpushdemo.fastjson.FastJSON;

/**
 * Created by Jack on 16/10/28.
 */
public class HttpRequestLogin extends BaseHttpPost {
    @Override
    protected String getUri() {
        return "/LoginServlet";
    }

    private static class SingletonHolder {
        private static HttpRequestLogin instance = new HttpRequestLogin();
    }

    public static HttpRequestLogin getInstance() {
        return SingletonHolder.instance;
    }

    public void execute(String username, String password, final HttpCallback<LoginData> callback) {
        RequestParams params = new RequestParams();
        params.put("username", username);
        params.put("password", password);
        RequestUtils.ClientPost(getUrl(), params, new NetCallBack() {
            @Override
            public void onMySuccess(byte[] response) {
                callback.onSuccess(FastJSON.jsonStrToJavaObj(new String(response), LoginData.class));
            }

            @Override
            public void onMyFailure(byte[] response, Throwable throwable) {
                callback.onFailure(new String(response));
            }
        });
    }
}
