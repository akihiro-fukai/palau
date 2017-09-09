package com.example.akihiro.palau.fragment;

import com.example.akihiro.palau.R;

import narou4j.enums.RankingType;

public class RankingDailyNarouApiFragment extends RankingBaseNarouApiFragment {

    private static RankingDailyNarouApiFragment sSelf;

    public static RankingDailyNarouApiFragment getInstance() {

        if (null == sSelf) {

            sSelf = new RankingDailyNarouApiFragment();
        }
        return sSelf;
    }

    // ------------------------------
    // 通信要求
    // ------------------------------

    @Override
    protected RankingType getRType() {

        return RankingType.DAILY;
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
