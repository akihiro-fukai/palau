package com.example.akihiro.palau.net.common;

import com.example.akihiro.palau.R;

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
     * ノンジャンル〔ノンジャンル〕
     */
    NON_GENRE(9801),
    /**
     * その他〔その他〕
     */
    OTHER(9999);

    private final int mBigGenre;

    Genre(int bigGenre) {

        mBigGenre = bigGenre;
    }

    public int toInt() {

        return mBigGenre;
    }

    public static int getGenreTextId(int genreId) {

        int genreTextId = R.string.genre_other;

        if (DIFFERENT_DIMENSION_WORLD.toInt() == genreId) {

            genreTextId = R.string.genre_different_dimension_world;
        }
        else if (REAL_WORLD.toInt() == genreId) {

            genreTextId = R.string.genre_real_world;
        }
        else if (HIGH_FANTASY.toInt() == genreId) {

            genreTextId = R.string.genre_high_fantasy;
        }
        else if (HIGH_FANTASY.toInt() == genreId) {

            genreTextId = R.string.genre_high_fantasy;
        }
        else if (LOW_FANTASY.toInt() == genreId) {

            genreTextId = R.string.genre_low_fantasy;
        }
        else if (PURE_LITERATURE.toInt() == genreId) {

            genreTextId = R.string.genre_pure_literature;
        }
        else if (HUMAN_DRAMA.toInt() == genreId) {

            genreTextId = R.string.genre_human_drama;
        }
        else if (HISTORY.toInt() == genreId) {

            genreTextId = R.string.genre_history;
        }
        else if (REASONING.toInt() == genreId) {

            genreTextId = R.string.genre_reasoning;
        }
        else if (HORROR.toInt() == genreId) {

            genreTextId = R.string.genre_horror;
        }
        else if (ACTION.toInt() == genreId) {

            genreTextId = R.string.genre_action;
        }
        else if (COMEDY.toInt() == genreId) {

            genreTextId = R.string.genre_comedy;
        }
        else if (VR_GAME.toInt() == genreId) {

            genreTextId = R.string.genre_vr_game;
        }
        else if (SPACE.toInt() == genreId) {

            genreTextId = R.string.genre_space;
        }
        else if (SCIENCE_OF_FANTASY.toInt() == genreId) {

            genreTextId = R.string.genre_science_of_fantasy;
        }
        else if (PANIC.toInt() == genreId) {

            genreTextId = R.string.genre_panic;
        }
        else if (NURSERY_TALE.toInt() == genreId) {

            genreTextId = R.string.genre_nursery_tale;
        }
        else if (POEM.toInt() == genreId) {

            genreTextId = R.string.genre_poem;
        }
        else if (ESSAY.toInt() == genreId) {

            genreTextId = R.string.genre_essay;
        }
        else if (REPLAY.toInt() == genreId) {

            genreTextId = R.string.genre_replay;
        }
        else if (NON_GENRE.toInt() == genreId) {

            genreTextId = R.string.genre_non;
        }
        else if (OTHER.toInt() == genreId) {

            genreTextId = R.string.genre_other;
        }
        return genreTextId;
    }
}
