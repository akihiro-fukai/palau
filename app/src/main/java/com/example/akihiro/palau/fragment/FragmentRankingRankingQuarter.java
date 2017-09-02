package com.example.akihiro.palau.fragment;

import com.example.akihiro.palau.R;

import narou4j.enums.RankingType;

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
    protected RankingType getRType() {

        return RankingType.QUARTET;
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
