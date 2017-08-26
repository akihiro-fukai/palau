package com.example.akihiro.palau.fragment;

import com.example.akihiro.palau.R;
import com.example.akihiro.palau.net.NetConfig;
import com.example.akihiro.palau.net.RequestType;

public class FragmentRankingWeekly extends FragmentBase {

    public static FragmentRankingWeekly newInstance() {

        FragmentRankingWeekly fragment = new FragmentRankingWeekly();

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

        return R.layout.fragment_ranking_weekly;
    }
}
