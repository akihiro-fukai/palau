package com.example.akihiro.palau.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.akihiro.palau.net.response.item.NovelDetail;

import java.util.ArrayList;
import java.util.List;

import narou4j.entities.NovelBody;

import static android.provider.BaseColumns._ID;
import static com.example.akihiro.palau.database.NarouContract.Novel.COLUMN_NAME_ALL_HYOKA_CNT;
import static com.example.akihiro.palau.database.NarouContract.Novel.COLUMN_NAME_ALL_POINT;
import static com.example.akihiro.palau.database.NarouContract.Novel.COLUMN_NAME_BIG_GENRE;
import static com.example.akihiro.palau.database.NarouContract.Novel.COLUMN_NAME_END;
import static com.example.akihiro.palau.database.NarouContract.Novel.COLUMN_NAME_FAV_NOVEL_CNT;
import static com.example.akihiro.palau.database.NarouContract.Novel.COLUMN_NAME_GENERAL_ALL_NO;
import static com.example.akihiro.palau.database.NarouContract.Novel.COLUMN_NAME_GENERAL_FIRSTUP;
import static com.example.akihiro.palau.database.NarouContract.Novel.COLUMN_NAME_GENERAL_LASTUP;
import static com.example.akihiro.palau.database.NarouContract.Novel.COLUMN_NAME_GENRE;
import static com.example.akihiro.palau.database.NarouContract.Novel.COLUMN_NAME_GLOBAL_POINT;
import static com.example.akihiro.palau.database.NarouContract.Novel.COLUMN_NAME_IS_BL;
import static com.example.akihiro.palau.database.NarouContract.Novel.COLUMN_NAME_IS_GL;
import static com.example.akihiro.palau.database.NarouContract.Novel.COLUMN_NAME_IS_R15;
import static com.example.akihiro.palau.database.NarouContract.Novel.COLUMN_NAME_IS_STOP;
import static com.example.akihiro.palau.database.NarouContract.Novel.COLUMN_NAME_IS_TENI;
import static com.example.akihiro.palau.database.NarouContract.Novel.COLUMN_NAME_IS_TENSEI;
import static com.example.akihiro.palau.database.NarouContract.Novel.COLUMN_NAME_IS_ZANKOKU;
import static com.example.akihiro.palau.database.NarouContract.Novel.COLUMN_NAME_KAIWARITU;
import static com.example.akihiro.palau.database.NarouContract.Novel.COLUMN_NAME_KEYWORD;
import static com.example.akihiro.palau.database.NarouContract.Novel.COLUMN_NAME_LENGTH;
import static com.example.akihiro.palau.database.NarouContract.Novel.COLUMN_NAME_NOVEL_TYPE;
import static com.example.akihiro.palau.database.NarouContract.Novel.COLUMN_NAME_NOVEL_UPDATED_AT;
import static com.example.akihiro.palau.database.NarouContract.Novel.COLUMN_NAME_N_CODE;
import static com.example.akihiro.palau.database.NarouContract.Novel.COLUMN_NAME_PC_OR_K;
import static com.example.akihiro.palau.database.NarouContract.Novel.COLUMN_NAME_RANK;
import static com.example.akihiro.palau.database.NarouContract.Novel.COLUMN_NAME_REVIEW_CNT;
import static com.example.akihiro.palau.database.NarouContract.Novel.COLUMN_NAME_SASIE_CNT;
import static com.example.akihiro.palau.database.NarouContract.Novel.COLUMN_NAME_STORY;
import static com.example.akihiro.palau.database.NarouContract.Novel.COLUMN_NAME_TIME;
import static com.example.akihiro.palau.database.NarouContract.Novel.COLUMN_NAME_TITLE;
import static com.example.akihiro.palau.database.NarouContract.Novel.COLUMN_NAME_UPDATED_AT;
import static com.example.akihiro.palau.database.NarouContract.Novel.COLUMN_NAME_USER_ID;
import static com.example.akihiro.palau.database.NarouContract.Novel.COLUMN_NAME_WRITER;
import static com.example.akihiro.palau.database.NarouContract.Novel.TABLE_NAME;

public class NarouDao {

    private NarouDBOpenHelper mHelper;

    public NarouDao(Context context) {

        mHelper = new NarouDBOpenHelper(context);
    }

    /**
     * 小説の基本情報を登録します。
     *
     * @param novelDetail 小説の基本情報
     */
    public synchronized long insert(NovelDetail novelDetail) {

        int novelId = getExistByNCode(novelDetail.getNCode());
        if (-1 != novelId) {

            // TODO 登録済みのNコード
            return novelId;
        }

        SQLiteDatabase db = mHelper.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(COLUMN_NAME_TITLE, novelDetail.getTitle());
        values.put(COLUMN_NAME_N_CODE, novelDetail.getNCode());
        values.put(COLUMN_NAME_USER_ID, novelDetail.getUserId());
        values.put(COLUMN_NAME_WRITER, novelDetail.getWriter());
        values.put(COLUMN_NAME_STORY, novelDetail.getStory());
        values.put(COLUMN_NAME_BIG_GENRE, novelDetail.getBigGenre());
        values.put(COLUMN_NAME_GENRE, novelDetail.getGenre());
        values.put(COLUMN_NAME_KEYWORD, novelDetail.getKeyword());
        values.put(COLUMN_NAME_GENERAL_FIRSTUP, novelDetail.getGeneralFirstup());
        values.put(COLUMN_NAME_GENERAL_LASTUP, novelDetail.getGeneralLastup());
        values.put(COLUMN_NAME_NOVEL_TYPE, novelDetail.getNovelType());
        values.put(COLUMN_NAME_END, novelDetail.getEnd());
        values.put(COLUMN_NAME_LENGTH, novelDetail.getLength());
        values.put(COLUMN_NAME_TIME, novelDetail.getTime());
        values.put(COLUMN_NAME_IS_STOP, novelDetail.getIsStop());
        values.put(COLUMN_NAME_IS_R15, novelDetail.getIsR15());
        values.put(COLUMN_NAME_IS_BL, novelDetail.getIsBL());
        values.put(COLUMN_NAME_IS_GL, novelDetail.getIsGL());
        values.put(COLUMN_NAME_IS_TENSEI, novelDetail.getIsTensei());
        values.put(COLUMN_NAME_IS_TENI, novelDetail.getIsTeni());
        values.put(COLUMN_NAME_IS_ZANKOKU, novelDetail.getIsZankoku());
        values.put(COLUMN_NAME_PC_OR_K, novelDetail.getPcOrK());
        values.put(COLUMN_NAME_GLOBAL_POINT, novelDetail.getGlobalPoint());
        values.put(COLUMN_NAME_FAV_NOVEL_CNT, novelDetail.getFavNovelCnt());
        values.put(COLUMN_NAME_GENERAL_ALL_NO, novelDetail.getGeneralAllNo());
        values.put(COLUMN_NAME_REVIEW_CNT, novelDetail.getReviewCnt());
        values.put(COLUMN_NAME_ALL_HYOKA_CNT, novelDetail.getAllHyokaCnt());
        values.put(COLUMN_NAME_ALL_POINT, novelDetail.getAllPoint());
        values.put(COLUMN_NAME_SASIE_CNT, novelDetail.getSasieCnt());
        values.put(COLUMN_NAME_KAIWARITU, novelDetail.getKaiwaritu());
        values.put(COLUMN_NAME_NOVEL_UPDATED_AT, novelDetail.getNovelUpdatedAt());
        values.put(COLUMN_NAME_UPDATED_AT, novelDetail.getUpdatedAt());
        values.put(COLUMN_NAME_RANK, novelDetail.getRank());

        long newRowId = db.insert(TABLE_NAME, null, values);

        db.close();

        return newRowId;
    }

    /**
     * 小説本文を登録します。
     *
     * @param novelBodies 小説の本文情報
     * @param nCode       Nコード
     */
    public synchronized void insert(List<NovelBody> novelBodies, String nCode) {

        int novelId = getExistByNCode(nCode);
        if (-1 == novelId) {

            return;
        }

        SQLiteDatabase db = mHelper.getWritableDatabase();
        for (NovelBody novelBody : novelBodies) {

            ContentValues values = new ContentValues();
            values.put(NarouContract.NovelBody.COLUMN_NAME_NOVEL_ID, novelId);
            values.put(NarouContract.NovelBody.COLUMN_NAME_TITLE, novelBody.getTitle());
            values.put(NarouContract.NovelBody.COLUMN_NAME_PAGE, novelBody.getPage());
            values.put(NarouContract.NovelBody.COLUMN_NAME_NOVEL_BODY, novelBody.getBody());

            long newRowId = db.insert(NarouContract.NovelBody.TABLE_NAME, null, values);
            if (-1 == newRowId) {

                // TODO 登録済みのレコード、もしくは登録失敗
            }
        }

        db.close();
    }

    /**
     * Nコードが登録済みの場合はnovelテーブルのIDを返却します。未登録の場合は-1を返却します。
     *
     * @param ncode Nコード
     * @return novelテーブルのID
     */
    private synchronized int getExistByNCode(String ncode) {

        SQLiteDatabase db = mHelper.getReadableDatabase();

        String[] projection = {
                NarouContract.Novel._ID
        };

        String selection = COLUMN_NAME_N_CODE + " = ?";
        String[] selectionArgs = {ncode};
        Cursor cursor = db.query(
                NarouContract.Novel.TABLE_NAME,           // The table to query
                projection,                               // The columns to return
                selection,                                // The columns for the WHERE clause
                selectionArgs,                            // The values for the WHERE clause
                null,                             // don't group the rows
                null,                              // don't filter by row groups
                null                              // The sort order
        );

        int novelId = -1;
        if (null != cursor && cursor.getCount() > 0) {

            cursor.moveToFirst();
            novelId = cursor.getInt(cursor.getColumnIndex(_ID));
            cursor.close();
        }
        db.close();

        return novelId;
    }

    /**
     * 小説の基本情報を取得します。
     *
     * @param nCode Nコード
     * @return 小説の基本情報
     */
    public synchronized NovelDetail findNovelDetailByNCode(String nCode) {

        SQLiteDatabase db = mHelper.getReadableDatabase();

        String selection = COLUMN_NAME_N_CODE + " = ?";
        String[] selectionArgs = {nCode};
        Cursor cursor = db.query(
                NarouContract.Novel.TABLE_NAME,           // The table to query
                null,                             // The columns to return
                selection,                                // The columns for the WHERE clause
                selectionArgs,                            // The values for the WHERE clause
                null,                            // don't group the rows
                null,                             // don't filter by row groups
                null                             // The sort order
        );

        NovelDetail novelDetail = null;
        if (null != cursor && cursor.getCount() > 0) {

            novelDetail = new NovelDetail();

            cursor.moveToFirst();
            novelDetail.setTitle(cursor.getString(cursor.getColumnIndex(COLUMN_NAME_TITLE)));
            novelDetail.setNCode(cursor.getString(cursor.getColumnIndex(COLUMN_NAME_N_CODE)));
            novelDetail.setUserId(cursor.getInt(cursor.getColumnIndex(COLUMN_NAME_USER_ID)));
            novelDetail.setWriter(cursor.getString(cursor.getColumnIndex(COLUMN_NAME_WRITER)));
            novelDetail.setStory(cursor.getString(cursor.getColumnIndex(COLUMN_NAME_STORY)));
            novelDetail.setBigGenre(cursor.getInt(cursor.getColumnIndex(COLUMN_NAME_BIG_GENRE)));
            novelDetail.setGenre(cursor.getInt(cursor.getColumnIndex(COLUMN_NAME_GENRE)));
            novelDetail.setKeyword(cursor.getString(cursor.getColumnIndex(COLUMN_NAME_KEYWORD)));
            novelDetail.setGeneralFirstup(cursor.getString(cursor.getColumnIndex(COLUMN_NAME_GENERAL_FIRSTUP)));
            novelDetail.setGeneralLastup(cursor.getString(cursor.getColumnIndex(COLUMN_NAME_GENERAL_LASTUP)));
            novelDetail.setNovelType(cursor.getInt(cursor.getColumnIndex(COLUMN_NAME_NOVEL_TYPE)));
            novelDetail.setEnd(cursor.getInt(cursor.getColumnIndex(COLUMN_NAME_END)));
            novelDetail.setLength(cursor.getInt(cursor.getColumnIndex(COLUMN_NAME_LENGTH)));
            novelDetail.setTime(cursor.getInt(cursor.getColumnIndex(COLUMN_NAME_TIME)));
            novelDetail.setIsStop(cursor.getInt(cursor.getColumnIndex(COLUMN_NAME_IS_STOP)));
            novelDetail.setIsR15(cursor.getInt(cursor.getColumnIndex(COLUMN_NAME_IS_R15)));
            novelDetail.setIsBL(cursor.getInt(cursor.getColumnIndex(COLUMN_NAME_IS_BL)));
            novelDetail.setIsGL(cursor.getInt(cursor.getColumnIndex(COLUMN_NAME_IS_GL)));
            novelDetail.setIsZankoku(cursor.getInt(cursor.getColumnIndex(COLUMN_NAME_IS_ZANKOKU)));
            novelDetail.setIsTensei(cursor.getInt(cursor.getColumnIndex(COLUMN_NAME_IS_TENSEI)));
            novelDetail.setIsTeni(cursor.getInt(cursor.getColumnIndex(COLUMN_NAME_IS_TENI)));
            novelDetail.setPcOrK(cursor.getInt(cursor.getColumnIndex(COLUMN_NAME_PC_OR_K)));
            novelDetail.setGlobalPoint(cursor.getInt(cursor.getColumnIndex(COLUMN_NAME_GLOBAL_POINT)));
            novelDetail.setFavNovelCnt(cursor.getInt(cursor.getColumnIndex(COLUMN_NAME_FAV_NOVEL_CNT)));
            novelDetail.setGeneralAllNo(cursor.getInt(cursor.getColumnIndex(COLUMN_NAME_GENERAL_ALL_NO)));
            novelDetail.setReviewCnt(cursor.getInt(cursor.getColumnIndex(COLUMN_NAME_REVIEW_CNT)));
            novelDetail.setAllPoint(cursor.getInt(cursor.getColumnIndex(COLUMN_NAME_ALL_POINT)));
            novelDetail.setAllHyokaCnt(cursor.getInt(cursor.getColumnIndex(COLUMN_NAME_ALL_HYOKA_CNT)));
            novelDetail.setSasieCnt(cursor.getInt(cursor.getColumnIndex(COLUMN_NAME_SASIE_CNT)));
            novelDetail.setKaiwaritu(cursor.getInt(cursor.getColumnIndex(COLUMN_NAME_KAIWARITU)));
            novelDetail.setNovelUpdatedAt(cursor.getString(cursor.getColumnIndex(COLUMN_NAME_NOVEL_UPDATED_AT)));
            novelDetail.setUpdatedAt(cursor.getString(cursor.getColumnIndex(COLUMN_NAME_UPDATED_AT)));
            novelDetail.setRank(cursor.getInt(cursor.getColumnIndex(COLUMN_NAME_RANK)));
            cursor.close();
        }
        db.close();

        return novelDetail;
    }

    /**
     * 登録済み小説の基本情報を全て取得します。
     *
     * @return 登録済み小説の基本情報
     */
    public synchronized List<NovelDetail> findAllNovelDetail() {

        SQLiteDatabase db = mHelper.getReadableDatabase();

        Cursor cursor = db.query(
                NarouContract.Novel.TABLE_NAME,           // The table to query
                null,                             // The columns to return
                null,                                // The columns for the WHERE clause
                null,                            // The values for the WHERE clause
                null,                            // don't group the rows
                null,                             // don't filter by row groups
                null                             // The sort order
        );

        List<NovelDetail> novelDetails = null;
        if (null != cursor && cursor.getCount() > 0) {

            novelDetails = new ArrayList<>();

            for (boolean next = cursor.moveToFirst(); next; next = cursor.moveToNext()) {

                NovelDetail novelDetail = new NovelDetail();

                novelDetail.setTitle(cursor.getString(cursor.getColumnIndex(COLUMN_NAME_TITLE)));
                novelDetail.setNCode(cursor.getString(cursor.getColumnIndex(COLUMN_NAME_N_CODE)));
                novelDetail.setUserId(cursor.getInt(cursor.getColumnIndex(COLUMN_NAME_USER_ID)));
                novelDetail.setWriter(cursor.getString(cursor.getColumnIndex(COLUMN_NAME_WRITER)));
                novelDetail.setStory(cursor.getString(cursor.getColumnIndex(COLUMN_NAME_STORY)));
                novelDetail.setBigGenre(cursor.getInt(cursor.getColumnIndex(COLUMN_NAME_BIG_GENRE)));
                novelDetail.setGenre(cursor.getInt(cursor.getColumnIndex(COLUMN_NAME_GENRE)));
                novelDetail.setKeyword(cursor.getString(cursor.getColumnIndex(COLUMN_NAME_KEYWORD)));
                novelDetail.setGeneralFirstup(cursor.getString(cursor.getColumnIndex(COLUMN_NAME_GENERAL_FIRSTUP)));
                novelDetail.setGeneralLastup(cursor.getString(cursor.getColumnIndex(COLUMN_NAME_GENERAL_LASTUP)));
                novelDetail.setNovelType(cursor.getInt(cursor.getColumnIndex(COLUMN_NAME_NOVEL_TYPE)));
                novelDetail.setEnd(cursor.getInt(cursor.getColumnIndex(COLUMN_NAME_END)));
                novelDetail.setLength(cursor.getInt(cursor.getColumnIndex(COLUMN_NAME_LENGTH)));
                novelDetail.setTime(cursor.getInt(cursor.getColumnIndex(COLUMN_NAME_TIME)));
                novelDetail.setIsStop(cursor.getInt(cursor.getColumnIndex(COLUMN_NAME_IS_STOP)));
                novelDetail.setIsR15(cursor.getInt(cursor.getColumnIndex(COLUMN_NAME_IS_R15)));
                novelDetail.setIsBL(cursor.getInt(cursor.getColumnIndex(COLUMN_NAME_IS_BL)));
                novelDetail.setIsGL(cursor.getInt(cursor.getColumnIndex(COLUMN_NAME_IS_GL)));
                novelDetail.setIsZankoku(cursor.getInt(cursor.getColumnIndex(COLUMN_NAME_IS_ZANKOKU)));
                novelDetail.setIsTensei(cursor.getInt(cursor.getColumnIndex(COLUMN_NAME_IS_TENSEI)));
                novelDetail.setIsTeni(cursor.getInt(cursor.getColumnIndex(COLUMN_NAME_IS_TENI)));
                novelDetail.setPcOrK(cursor.getInt(cursor.getColumnIndex(COLUMN_NAME_PC_OR_K)));
                novelDetail.setGlobalPoint(cursor.getInt(cursor.getColumnIndex(COLUMN_NAME_GLOBAL_POINT)));
                novelDetail.setFavNovelCnt(cursor.getInt(cursor.getColumnIndex(COLUMN_NAME_FAV_NOVEL_CNT)));
                novelDetail.setGeneralAllNo(cursor.getInt(cursor.getColumnIndex(COLUMN_NAME_GENERAL_ALL_NO)));
                novelDetail.setReviewCnt(cursor.getInt(cursor.getColumnIndex(COLUMN_NAME_REVIEW_CNT)));
                novelDetail.setAllPoint(cursor.getInt(cursor.getColumnIndex(COLUMN_NAME_ALL_POINT)));
                novelDetail.setAllHyokaCnt(cursor.getInt(cursor.getColumnIndex(COLUMN_NAME_ALL_HYOKA_CNT)));
                novelDetail.setSasieCnt(cursor.getInt(cursor.getColumnIndex(COLUMN_NAME_SASIE_CNT)));
                novelDetail.setKaiwaritu(cursor.getInt(cursor.getColumnIndex(COLUMN_NAME_KAIWARITU)));
                novelDetail.setNovelUpdatedAt(cursor.getString(cursor.getColumnIndex(COLUMN_NAME_NOVEL_UPDATED_AT)));
                novelDetail.setUpdatedAt(cursor.getString(cursor.getColumnIndex(COLUMN_NAME_UPDATED_AT)));
                novelDetail.setRank(cursor.getInt(cursor.getColumnIndex(COLUMN_NAME_RANK)));

                novelDetails.add(novelDetail);
            }
            cursor.close();
        }
        db.close();

        return novelDetails;
    }

    /**
     * 登録済み小説の基本情報を全て取得します。
     *
     * @return 登録済み小説の基本情報
     */
    public synchronized List<NovelBody> findNovelBodyByNCode(String nCode) {

        int novelId = getExistByNCode(nCode);
        if (-1 == novelId) {

            return null;
        }

        SQLiteDatabase db = mHelper.getReadableDatabase();

        String selection = NarouContract.NovelBody.COLUMN_NAME_NOVEL_ID + " = ?";
        String[] selectionArgs = { String.valueOf(novelId) };
        Cursor cursor = db.query(
                NarouContract.NovelBody.TABLE_NAME,           // The table to query
                null,                             // The columns to return
                selection,                                // The columns for the WHERE clause
                selectionArgs,                            // The values for the WHERE clause
                null,                            // don't group the rows
                null,                             // don't filter by row groups
                null                             // The sort order
        );

        List<NovelBody> novelBodies = null;
        if (null != cursor && cursor.getCount() > 0) {

            novelBodies = new ArrayList<>();

            for (boolean next = cursor.moveToFirst(); next; next = cursor.moveToNext()) {

                NovelBody novelBody = new NovelBody();

                novelBody.setTitle(cursor.getString(cursor.getColumnIndex(NarouContract.NovelBody.COLUMN_NAME_TITLE)));
                novelBody.setPage(cursor.getInt(cursor.getColumnIndex(NarouContract.NovelBody.COLUMN_NAME_PAGE)));
                novelBody.setBody(cursor.getString(cursor.getColumnIndex(NarouContract.NovelBody.COLUMN_NAME_NOVEL_BODY)));

                novelBodies.add(novelBody);
            }
            cursor.close();
        }
        db.close();

        return novelBodies;
    }
}
