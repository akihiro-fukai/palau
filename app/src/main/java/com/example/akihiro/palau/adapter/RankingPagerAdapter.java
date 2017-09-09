package com.example.akihiro.palau.adapter;

import android.content.Context;
import android.content.res.Resources;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.example.akihiro.palau.R;
import com.example.akihiro.palau.fragment.RankingDailyNarouApiFragment;
import com.example.akihiro.palau.fragment.RankingMonthlyNarouApiFragment;
import com.example.akihiro.palau.fragment.RankingQuarterNarouApiFragment;
import com.example.akihiro.palau.fragment.RankingWeeklyNarouApiFragment;

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

                return RankingDailyNarouApiFragment.getInstance();
            case 1:

                return RankingWeeklyNarouApiFragment.getInstance();
            case 2:

                return RankingMonthlyNarouApiFragment.getInstance();
            case 3:

                return RankingQuarterNarouApiFragment.getInstance();
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
