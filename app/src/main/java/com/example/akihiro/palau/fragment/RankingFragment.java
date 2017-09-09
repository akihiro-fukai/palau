package com.example.akihiro.palau.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.akihiro.palau.R;
import com.example.akihiro.palau.adapter.RankingPagerAdapter;

/**
 * RankingFragmentクラスは小説のランキング一覧を表示する機能を提供します。
 */
public class RankingFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fragment_ranking, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        TabLayout tabLayout = (TabLayout) getActivity().findViewById(R.id.tabs);
        ViewPager viewPager = (ViewPager) getActivity().findViewById(R.id.pager);

        RankingPagerAdapter rankingPagerAdapter = new RankingPagerAdapter(getContext(), getActivity().getSupportFragmentManager());
        viewPager.setAdapter(rankingPagerAdapter);
        tabLayout.setupWithViewPager(viewPager);
    }
}
