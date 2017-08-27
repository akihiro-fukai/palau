package com.example.akihiro.palau.net.common;

public enum BigGenre {

    /**
     * 恋愛
     */
    LOVE(1),
    /**
     * ファンタジー
     */
    FANTASY(2),
    /**
     * 文芸
     */
    ITERATURE(3),
    /**
     * SF
     */
    SF(4),
    /**
     * ノンジャンル
     */
    NON_GENRE(98),
    /**
     * その他
     */
    OTHER(99);

    private final int mBigGenre;

    BigGenre(int bigGenre) {

        mBigGenre = bigGenre;
    }

    public int toInt() {

        return mBigGenre;
    }
}
