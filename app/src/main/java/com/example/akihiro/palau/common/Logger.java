package com.example.akihiro.palau.common;

import android.util.Log;

public class Logger {

    /**
     * ログのタグ
     */
    private static final String TAG = "parau";

    /**
     * デバッグレベルのログを出力します。
     *
     * @param msg ログ出力メッセージ
     */
    public static void d(String msg) {

        Log.d(TAG, msg);
    }
}
