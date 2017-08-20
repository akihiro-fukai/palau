package com.example.akihiro.palau.fragment;

import android.os.Bundle;

import com.example.akihiro.palau.R;

import static com.example.akihiro.palau.common.UICommon.BUNDLE_PAGER_PAGE;

public class FragmentRankingWeekly extends FragmentBase {

    public static FragmentRankingWeekly newInstance() {

        FragmentRankingWeekly fragment = new FragmentRankingWeekly();

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
