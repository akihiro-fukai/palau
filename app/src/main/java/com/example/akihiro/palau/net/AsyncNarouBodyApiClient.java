package com.example.akihiro.palau.net;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Environment;

import com.example.akihiro.palau.database.NarouDao;
import com.example.akihiro.palau.net.response.item.NovelDetail;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.util.List;

import narou4j.Narou;
import narou4j.entities.NovelBody;

public class AsyncNarouBodyApiClient extends AsyncTask<String, Void, Void> {

    private static final String DIR_NAME = "/NarouReader";

    // コンテキスト
    private Context mContext;
    // 小説情報
    private NovelDetail mNovelDetail;

    /**
     * コンストラクタ
     *
     * @param context コンテキスト
     */
    public AsyncNarouBodyApiClient(Context context, NovelDetail novelDetail) {

        mContext = context;
        mNovelDetail = novelDetail;
    }

    @Override
    protected Void doInBackground(String... nCodes) {

        // Nコード
        String nCode = nCodes[0];

        // 本文取得
        Narou narou = new Narou();
        List<NovelBody> novelBodies = narou.getNovelBodyAll(nCode);

        NarouDao dao = new NarouDao(mContext);
        dao.insert(mNovelDetail);
        dao.insert(novelBodies, mNovelDetail.getNCode());

//        if (!isExternalStorageWritable()) {
//
//            // TODO 外部ストレージ書き込み不可状態
//            return null;
//        }
//
//        String externalFilesDir = mExternalFilesDir + DIR_NAME;
//        File dir = new File(externalFilesDir, nCode);
//        if (!dir.exists() || !dir.isDirectory()) {
//
//            // Nコード名のファイルが存在していない場合、
//            // またはNコード名のディレクトリが存在しない場合はNコード名のディレクトリを作成
//            if (!dir.mkdirs()) {
//
//                // TODO ディレクトリ作成失敗した場合
//                return null;
//            }
//        }
//
//        // 本文書き込み
//        for (NovelBody novelBody : novelBodies) {
//
//            int page = novelBody.getPage();
//            String title = novelBody.getTitle();
//            String body = novelBodies.get(0).getBody();
//
//            String fileName = String.valueOf(page) + "_" + title;
//            File file = new File(dir, fileName);
//
//            FileOutputStream fileOutputStream;
//            try {
//
//                fileOutputStream = new FileOutputStream(file, false);
//                OutputStreamWriter outputStreamWriter = new OutputStreamWriter(fileOutputStream, "UTF-8");
//                BufferedWriter bufferedWriter = new BufferedWriter(outputStreamWriter);
//                bufferedWriter.write(body);
//                bufferedWriter.flush();
//                bufferedWriter.close();
//            } catch (Exception e) {
//
//                // TODO ファイル書き込みエラー
//                return null;
//            }
//        }
        return null;
    }

    /**
     * 外部ストレージの書き込み可否状態を返却します。
     *
     * @return true: 書き込み可能、false: 書き込み不可
     */
    public boolean isExternalStorageWritable() {

        String state = Environment.getExternalStorageState();
        if (Environment.MEDIA_MOUNTED.equals(state)) {
            return true;
        }
        return false;
    }
}
