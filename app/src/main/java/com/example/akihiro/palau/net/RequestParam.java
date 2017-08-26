package com.example.akihiro.palau.net;

public enum RequestParam {

    GZIP("gzip"),
    NCODE("ncode"),
    RTYPE("rtype"),
    LIMIT("lim");

    private final String mRequestParam;

    RequestParam(String requestParam) {

        mRequestParam = requestParam;
    }

    public String toString() {

        return mRequestParam;
    }
}
