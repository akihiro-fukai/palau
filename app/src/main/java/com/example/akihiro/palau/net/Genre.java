package com.example.akihiro.palau.net;

public enum Genre {

    /**
     * 異世界〔恋愛〕
     */
    DIFFERENT_DIMENSION_WORLD(101),
    /**
     * 現実世界〔恋愛〕
     */
    REAL_WORLD(102),
    /**
     * ハイファンタジー〔ファンタジー〕
     */
    HIGH_FANTASY(201),
    /**
     * ローファンタジー〔ファンタジー〕
     */
    LOW_FANTASY(202),
    /**
     * 純文学〔文芸〕
     */
    PURE_LITERATURE(301),
    /**
     * ヒューマンドラマ〔文芸〕
     */
    HUMAN_DRAMA(302),
    /**
     * 歴史〔文芸〕
     */
    HISTORY(303),
    /**
     * 推理〔文芸〕
     */
    REASONING(304),
    /**
     * ホラー〔文芸〕
     */
    HORROR(305),
    /**
     * アクション〔文芸〕
     */
    ACTION(306),
    /**
     * コメディー〔文芸〕
     */
    COMEDY(307),
    /**
     * VRゲーム〔SF〕
     */
    VR_GAME(401),
    /**
     * 宇宙〔SF〕
     */
    SPACE(402),
    /**
     * 空想科学〔SF〕
     */
    SCIENCE_OF_FANTASY(403),
    /**
     * パニック〔SF〕
     */
    PANIC(404),
    /**
     * 童話〔その他〕
     */
    NURSERY_TALE(9901),
    /**
     * 詩〔その他〕
     */
    POEM(9902),
    /**
     * エッセイ〔その他〕
     */
    ESSAY(9903),
    /**
     * リプレイ〔その他〕
     */
    REPLAY(9904),
    /**
     * ノンジャンル
     */
    NON_GENRE(9801),
    /**
     * その他
     */
    OTHER(9999);

    private final int mBigGenre;

    Genre(int bigGenre) {

        mBigGenre = bigGenre;
    }

    public int toInt() {

        return mBigGenre;
    }
}
