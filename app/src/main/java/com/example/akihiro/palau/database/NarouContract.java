package com.example.akihiro.palau.database;

import android.provider.BaseColumns;

/**
 * NarouContractクラスはデータベースのカラムを定義します。
 */
final class NarouContract {

    /**
     * Novelクラスはnovelテーブルのカラムを定義します。
     */
    static class Novel implements BaseColumns {

        static final String TABLE_NAME = "novel";
        static final String COLUMN_NAME_TITLE = "title";
        static final String COLUMN_NAME_N_CODE = "n_code";
        static final String COLUMN_NAME_USER_ID = "user_id";
        static final String COLUMN_NAME_WRITER = "writer";
        static final String COLUMN_NAME_STORY = "story";
        static final String COLUMN_NAME_BIG_GENRE = "big_genre";
        static final String COLUMN_NAME_GENRE = "genre";
        static final String COLUMN_NAME_KEYWORD = "keyword";
        static final String COLUMN_NAME_GENERAL_FIRSTUP = "general_firstup";
        static final String COLUMN_NAME_GENERAL_LASTUP = "general_lastup";
        static final String COLUMN_NAME_NOVEL_TYPE = "novel_type";
        static final String COLUMN_NAME_END = "end";
        static final String COLUMN_NAME_LENGTH = "length";
        static final String COLUMN_NAME_TIME = "time";
        static final String COLUMN_NAME_IS_STOP = "is_stop";
        static final String COLUMN_NAME_IS_R15 = "is_r15";
        static final String COLUMN_NAME_IS_BL = "is_bl";
        static final String COLUMN_NAME_IS_GL = "is_gl";
        static final String COLUMN_NAME_IS_ZANKOKU = "is_zankoku";
        static final String COLUMN_NAME_IS_TENSEI = "is_tensei";
        static final String COLUMN_NAME_IS_TENI = "is_teni";
        static final String COLUMN_NAME_PC_OR_K = "pc_or_k";
        static final String COLUMN_NAME_GLOBAL_POINT = "global_point";
        static final String COLUMN_NAME_FAV_NOVEL_CNT = "fav_novel_cnt";
        static final String COLUMN_NAME_GENERAL_ALL_NO = "general_all_no";
        static final String COLUMN_NAME_REVIEW_CNT = "review_cnt";
        static final String COLUMN_NAME_ALL_POINT = "all_point";
        static final String COLUMN_NAME_ALL_HYOKA_CNT = "all_hyoka_cnt";
        static final String COLUMN_NAME_SASIE_CNT = "sasie_cnt";
        static final String COLUMN_NAME_KAIWARITU = "kaiwaritu";
        static final String COLUMN_NAME_NOVEL_UPDATED_AT = "novel_updated_at";
        static final String COLUMN_NAME_UPDATED_AT = "updated_at";
        static final String COLUMN_NAME_RANK = "rank";
    }

    /**
     * NovelBodyクラスはnovel_bodyテーブルのカラムを定義します。
     */
    static class NovelBody implements BaseColumns {

        static final String TABLE_NAME = "novel_body";
        static final String COLUMN_NAME_NOVEL_ID = "novel_id";
        static final String COLUMN_NAME_TITLE = "title";
        static final String COLUMN_NAME_PAGE = "page";
        static final String COLUMN_NAME_NOVEL_BODY = "novel_body";
    }
}
