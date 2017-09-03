package com.example.akihiro.palau.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;

/**
 * Created by akihiro on 2017/09/02.
 */

public class NarouDBOpenHelper extends SQLiteOpenHelper {

    private static final String DB_NAME = "Narou";
    private static final int DB_VERSION = 1;

    private Context mContext;

    /**
     * コンストラクタ
     *
     * @param context コンテキスト
     */
    public NarouDBOpenHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);

        mContext = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        execFileSQL(db, "create_novel_table.sql");
        execFileSQL(db, "create_novel_body_table.sql");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        // TODO データベース更新
    }

    /**
     * assetsフォルダのSQLファイルを実行する
     *
     * @param db       データベース
     * @param fileName ファイル名
     */
    private void execFileSQL(SQLiteDatabase db, String fileName) {

        InputStream in = null;
        InputStreamReader inReader = null;
        BufferedReader reader = null;
        try {
            // 文字コード(UTF-8)を指定して、ファイルを読み込み
            in = mContext.getAssets().open(fileName);
            inReader = new InputStreamReader(in, "UTF-8");
            reader = new BufferedReader(inReader);

            // ファイル内の全ての行を処理
            String s;
            while ((s = reader.readLine()) != null) {
                // 先頭と末尾の空白除去
                s = s.trim();

                // 文字が存在する場合（空白行は処理しない）
                if (0 < s.length()) {
                    // SQL実行
                    db.execSQL(s);
                }
            }

        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {

            if (in != null) {
                try {
                    in.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            if (inReader != null) {
                try {
                    inReader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
