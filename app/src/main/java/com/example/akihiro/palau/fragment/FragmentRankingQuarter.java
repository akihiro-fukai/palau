package com.example.akihiro.palau.fragment;

import com.example.akihiro.palau.R;

public class FragmentRankingQuarter extends FragmentBase {

    public static FragmentRankingQuarter newInstance() {

        FragmentRankingQuarter fragment = new FragmentRankingQuarter();

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
