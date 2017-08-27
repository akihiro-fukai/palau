package com.example.akihiro.palau.net.common;

import com.example.akihiro.palau.R;

public enum Type {

    /**
     * 連載
     */
    SERIES(1),
    /**
     * 短編
     */
    SHORT_STORY(2);

    private final int mType;

    Type(int type) {

        mType = type;
    }

    public int toInt() {

        return mType;
    }
}
