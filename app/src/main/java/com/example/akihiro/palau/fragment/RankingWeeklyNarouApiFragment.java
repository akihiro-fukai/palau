package com.example.akihiro.palau.fragment;

import com.example.akihiro.palau.R;

import narou4j.enums.RankingType;

public class RankingWeeklyNarouApiFragment extends RankingBaseNarouApiFragment {

    private static RankingWeeklyNarouApiFragment sSelf;

    public static RankingWeeklyNarouApiFragment getInstance() {

        if (null == sSelf) {

            sSelf = new RankingWeeklyNarouApiFragment();
        }
        return sSelf;
    }

    // ------------------------------
    // 通信要求
    // ------------------------------

    @Override
    protected RankingType getRType() {

        return RankingType.WEEKLY;
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
