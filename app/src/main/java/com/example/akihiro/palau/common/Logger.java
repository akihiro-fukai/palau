package com.example.akihiro.palau.common;

import android.util.Log;

/**
 * Loggerクラスはログ出力の機能を提供します。
 */
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

        StackTraceElement stack = Thread.currentThread().getStackTrace()[3];
        Log.d(TAG, "------------------------------");
        Log.d(TAG, String.format("class  = %s", stack.getClassName()));
        Log.d(TAG, String.format("method = %s", stack.getMethodName()));
        Log.d(TAG, String.format("line   = %d", stack.getLineNumber()));
        Log.d(TAG, String.format("msg    = %s", msg));
    }
}
