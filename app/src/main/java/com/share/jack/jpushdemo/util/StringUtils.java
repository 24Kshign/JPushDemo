package com.share.jack.jpushdemo.util;

import android.widget.EditText;

/**
 * Created by Jack on 16/10/28.
 */
public class StringUtils {

    public static final String EMPTY = "";

    public static String getContent(EditText editText) {
        if (editText == null) {
            return EMPTY;
        }
        return editText.getText().toString();
    }
}