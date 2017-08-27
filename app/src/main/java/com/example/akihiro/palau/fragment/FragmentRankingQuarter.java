package com.example.akihiro.palau.fragment;

import com.example.akihiro.palau.R;
import com.example.akihiro.palau.net.common.RequestType;

public class FragmentRankingQuarter extends FragmentBase {

    public static FragmentRankingQuarter newInstance() {

        FragmentRankingQuarter fragment = new FragmentRankingQuarter();

        return fragment;
    }

    @Override
    protected void onSuccess(RequestType type, String result) {

    }

    @Override
    protected int getTitleResId() {

        return 0;
    }

    @Override
    protected int getLayoutResId() {

        return R.layout.fragment_ranking_quarter;
    }
}
