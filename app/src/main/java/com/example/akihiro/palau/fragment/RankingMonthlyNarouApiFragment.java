package com.example.akihiro.palau.fragment;

import com.example.akihiro.palau.R;

import narou4j.enums.RankingType;

public class RankingMonthlyNarouApiFragment extends RankingBaseNarouApiFragment {

    private static RankingMonthlyNarouApiFragment sSelf;

    public static RankingMonthlyNarouApiFragment getInstance() {

        if (null == sSelf) {

            sSelf = new RankingMonthlyNarouApiFragment();
        }
        return sSelf;
    }

    // ------------------------------
    // 通信要求
    // ------------------------------

    @Override
    protected RankingType getRType() {

        return RankingType.MONTHLY;
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
