package com.example.akihiro.palau.fragment;

import com.example.akihiro.palau.R;

public class FragmentRankingMonthly extends FragmentBase {

    public static FragmentRankingMonthly newInstance() {

        FragmentRankingMonthly fragment = new FragmentRankingMonthly();

        return fragment;
    }

    @Override
    protected int getTitleResId() {

        return 0;
    }

    @Override
    protected int getLayoutResId() {

        return R.layout.fragment_ranking;
    }
}
