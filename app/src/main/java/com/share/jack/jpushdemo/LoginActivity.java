package com.share.jack.jpushdemo;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.share.jack.jpushdemo.callback.HttpCallback;
import com.share.jack.jpushdemo.http.HttpRequestLogin;
import com.share.jack.jpushdemo.http.LoginData;
import com.share.jack.jpushdemo.util.StringUtils;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import butterknife.Bind;
import butterknife.ButterKnife;
import cn.jpush.android.api.JPushInterface;
import cn.jpush.android.api.TagAliasCallback;

/**
 * Created by Jack on 16/10/28.
 */
public class LoginActivity extends Activity {


    @Bind(R.id.al_et_username)
    EditText alEtUsername;
    @Bind(R.id.al_et_pwd)
    EditText alEtPwd;
    @Bind(R.id.al_btn_login)
    Button alBtnLogin;
    private ProgressDialog pd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
        pd=new ProgressDialog(this);

        alBtnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = StringUtils.getContent(alEtUsername);
                String password = StringUtils.getContent(alEtPwd);
                if (!username.isEmpty() && !password.isEmpty()) {
                    pd.setMessage("加载中,请稍后...");
                    pd.setCanceledOnTouchOutside(false);
                    pd.show();
                    HttpRequestLogin.getInstance().execute(username, password, new HttpCallback<LoginData>() {
                        @Override
                        public void onSuccess(LoginData data) {
                            setJPush(data);
                        }

                        @Override
                        public void onFailure(String s) {
                            if (pd!=null && pd.isShowing()){
                                pd.dismiss();
                            }
                            Toast.makeText(LoginActivity.this, "登录失败", Toast.LENGTH_SHORT).show();
                        }
                    });
                } else {
                    Toast.makeText(LoginActivity.this, "用户名或密码不能为空", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    //设置极光推送
    private void setJPush(LoginData data) {
        String alias = data.getAlias();
        String tag = data.getTag();
        //标签可能有多个
        String[] tags = tag.split(",");
        Set<String> tagSet = new HashSet<>(Arrays.asList(tags));

        //给极光推送设置标签和别名
        JPushInterface.setAliasAndTags(LoginActivity.this, alias, tagSet, tagAliasCallback);
    }

    //极光服务器设置别名是否成功的回调
    private final TagAliasCallback tagAliasCallback = new TagAliasCallback() {
        @Override
        public void gotResult(int code, String alias, Set<String> tagSet) {
            switch (code) {
                case 0:
                    Log.i("LoginActivity", "设置别名成功");
                    if (pd!=null && pd.isShowing()){
                        pd.dismiss();
                    }
                    startActivity(new Intent(LoginActivity.this, MainActivity.class));
                    break;
                default:
                    Log.i("LoginActivity", "设置别名失败");
                    break;
            }
        }
    };

    @Override
    protected void onPause() {
        super.onPause();
        JPushInterface.onPause(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        JPushInterface.onResume(this);
    }
}
