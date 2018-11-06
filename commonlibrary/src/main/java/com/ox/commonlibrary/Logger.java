package com.ox.commonlibrary;

import android.text.TextUtils;

public class Logger {

    public static void sout(String s) {
        if (!TextUtils.isEmpty(s)) {
            System.out.println("www.baidu.com____________" + s);
        }
    }

}
