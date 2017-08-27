package com.example.akihiro.palau.net.common;

public enum RequestType {

    RANKING_DAILY(0),
    RANKING_WEEKLY(1),
    RANKING_DETAIL(2),
    ;

    private final int mRequestType;

    RequestType(final int requestTypeuestType) {

        mRequestType = requestTypeuestType;
    }

    public int toInt() {

        return mRequestType;
    }
}
