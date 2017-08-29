package com.example.akihiro.palau.fragment;

import android.text.format.DateFormat;

import com.example.akihiro.palau.R;

import java.util.Calendar;

public class FragmentRankingRankingWeekly extends FragmentRankingBase {

    private static FragmentRankingRankingWeekly sSelf;

    public static FragmentRankingRankingWeekly getInstance() {

        if (null == sSelf) {

            sSelf = new FragmentRankingRankingWeekly();
        }
        return sSelf;
    }

    // ------------------------------
    // 通信要求
    // ------------------------------

    @Override
    protected String getRType() {

        final String inFormat = "yyyyMMdd";

        return DateFormat.format(inFormat, Calendar.getInstance()).toString() + "-w";
    }

    @Override
    protected String getRetryRType() {

        if (mIsRetryRTypeRequest) {

            return null;
        }
        mIsRetryRTypeRequest = true;

        final String inFormat = "yyyyMMdd";

        Calendar calendar = Calendar.getInstance();
        int week = calendar.get(Calendar.DAY_OF_WEEK);
        int lastWeek = 0;
        if (3 == week) {

            lastWeek = 7;
        } else if (3 < week) {

            lastWeek = week - 3;
        } else if (3 > week) {

            lastWeek = 7 - (3 - week);
        }
        calendar.add(Calendar.DAY_OF_MONTH, -lastWeek);

        return DateFormat.format(inFormat, calendar).toString() + "-w";
    }

    @Override
    protected void onRTypeError() {

    }

    // ------------------------------
    // 初期化
    // ------------------------------

    @Override
    protected int getLayoutResId() {

        return R.layout.fragment_ranking_weekly;
    }

    @Override
    protected int getRankingDetailViewId() {

        return R.id.ranking_weekly_recycler_view;
    }
}
