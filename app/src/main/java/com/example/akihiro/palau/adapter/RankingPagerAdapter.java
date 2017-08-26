package com.example.akihiro.palau.adapter;

import android.content.Context;
import android.content.res.Resources;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.example.akihiro.palau.R;
import com.example.akihiro.palau.fragment.FragmentRankingDaily;
import com.example.akihiro.palau.fragment.FragmentRankingMonthly;
import com.example.akihiro.palau.fragment.FragmentRankingQuarter;
import com.example.akihiro.palau.fragment.FragmentRankingWeekly;

public class RankingPagerAdapter extends FragmentPagerAdapter {

    private static int NUM_ITEMS = 4;

    private Context mContext;

    public RankingPagerAdapter(Context context, FragmentManager fm) {

        super(fm);

        mContext = context;
    }

    @Override
    public Fragment getItem(int position) {

        switch (position) {

            case 0:

                return FragmentRankingDaily.getInstance();
            case 1:

                return FragmentRankingWeekly.newInstance();
            case 2:

                return FragmentRankingMonthly.newInstance();
            case 3:

                return FragmentRankingQuarter.newInstance();
            default:
                return null;
        }
    }

    @Override
    public CharSequence getPageTitle(int position) {

        Resources r = mContext.getResources();
        switch (position) {

            case 0:

                return r.getString(R.string.ranking_daily);
            case 1:

                return r.getString(R.string.ranking_weekly);
            case 2:

                return r.getString(R.string.ranking_monthly);
            case 3:

                return r.getString(R.string.ranking_quarter);
            default:
                break;
        }
        return null;
    }

    @Override
    public int getCount() {

        return NUM_ITEMS;
    }
}
