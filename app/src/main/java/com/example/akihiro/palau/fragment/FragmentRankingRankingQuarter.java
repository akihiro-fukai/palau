package com.example.akihiro.palau.fragment;

import android.text.format.DateFormat;

import com.example.akihiro.palau.R;

import java.util.Calendar;

public class FragmentRankingRankingQuarter extends FragmentRankingBase {

    private static FragmentRankingRankingQuarter sSelf;

    public static FragmentRankingRankingQuarter getInstance() {

        if (null == sSelf) {

            sSelf = new FragmentRankingRankingQuarter();
        }
        return sSelf;
    }

    // ------------------------------
    // 通信要求
    // ------------------------------

    @Override
    protected String getRType() {

        final String inFormat = "yyyyMM";

        return DateFormat.format(inFormat, Calendar.getInstance()).toString() + "01-q";
    }

    @Override
    protected String getRetryRType() {

        return null;
    }

    @Override
    protected void onRTypeError() {

        // TODO
    }

    // ------------------------------
    // 初期化
    // ------------------------------

    @Override
    protected int getLayoutResId() {

        return R.layout.fragment_ranking_quarter;
    }

    @Override
    protected int getRankingDetailViewId() {

        return R.id.ranking_quarter_recycler_view;
    }
}
