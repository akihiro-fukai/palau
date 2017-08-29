package com.example.akihiro.palau.fragment;

import android.text.format.DateFormat;

import com.example.akihiro.palau.R;

import java.util.Calendar;

public class FragmentRankingRankingDaily extends FragmentRankingBase {

    private static FragmentRankingRankingDaily sSelf;

    public static FragmentRankingRankingDaily getInstance() {

        if (null == sSelf) {

            sSelf = new FragmentRankingRankingDaily();
        }
        return sSelf;
    }

    // ------------------------------
    // 通信要求
    // ------------------------------

    @Override
    protected String getRType() {

        final String inFormat = "yyyyMMdd";

        return DateFormat.format(inFormat, Calendar.getInstance()).toString() + "-d";
    }

    @Override
    protected String getRetryRType() {

        if (mIsRetryRTypeRequest) {

            return null;
        }
        mIsRetryRTypeRequest = true;

        // 日間ランキングは1日3回更新されていますが、
        // このAPIでは午前4時～午前7時に作成した日間ランキングのみを蓄積しAPIで提供しています。
        // 深夜帯は当日のランキング取得エラーが発生するため前日のランキングを取得します。

        final String inFormat = "yyyyMMdd";
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DAY_OF_MONTH, -1);

        return DateFormat.format(inFormat, calendar).toString() + "-d";
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

        return R.layout.fragment_ranking_daily;
    }

    @Override
    protected int getRankingDetailViewId() {

        return R.id.ranking_daily_recycler_view;
    }
}
