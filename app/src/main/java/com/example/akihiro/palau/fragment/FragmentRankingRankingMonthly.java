package com.example.akihiro.palau.fragment;

import android.text.format.DateFormat;

import com.example.akihiro.palau.R;

import java.util.Calendar;

public class FragmentRankingRankingMonthly extends FragmentRankingBase {

    private static FragmentRankingRankingMonthly sSelf;

    public static FragmentRankingRankingMonthly getInstance() {

        if (null == sSelf) {

            sSelf = new FragmentRankingRankingMonthly();
        }
        return sSelf;
    }

    // ------------------------------
    // 通信要求
    // ------------------------------

    @Override
    protected String getRType() {

        final String inFormat = "yyyyMM";

        return DateFormat.format(inFormat, Calendar.getInstance()).toString() + "01-m";
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

        return R.layout.fragment_ranking_monthly;
    }

    @Override
    protected int getRankingDetailViewId() {

        return R.id.ranking_monthly_recycler_view;
    }
}
