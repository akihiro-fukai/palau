package com.example.akihiro.palau.fragment;

import com.example.akihiro.palau.R;

import narou4j.enums.RankingType;

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
