package com.example.akihiro.palau.activity;

import android.content.res.Resources;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;

import com.example.akihiro.palau.R;
import com.example.akihiro.palau.fragment.FragmentRankingDaily;
import com.example.akihiro.palau.fragment.FragmentRankingMonthly;
import com.example.akihiro.palau.fragment.FragmentRankingQuarter;
import com.example.akihiro.palau.fragment.FragmentRankingWeekly;

public class RankingActivity extends AppCompatActivity implements ViewPager.OnPageChangeListener {

    private static final int TAB_COUNT = 4;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ranking);

        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);
        ViewPager viewPager = (ViewPager) findViewById(R.id.pager);

        ActionBar actionBar = getSupportActionBar();
        if (null != actionBar) {

            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setHomeButtonEnabled(true);
        }

        FragmentPagerAdapter adapter = new FragmentPagerAdapter(getSupportFragmentManager()) {

            @Override
            public Fragment getItem(int position) {

                switch (position) {

                    case 0:

                        return FragmentRankingDaily.newInstance();
                    case 1:

                        return FragmentRankingWeekly.newInstance();
                    case 2:

                        return FragmentRankingMonthly.newInstance();
                    case 3:

                        return FragmentRankingQuarter.newInstance();
                    default:
                        break;
                }
                return null;
            }

            @Override
            public CharSequence getPageTitle(int position) {

                Resources r = getResources();
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

                return TAB_COUNT;
            }
        };

        viewPager.setAdapter(adapter);
        viewPager.addOnPageChangeListener(this);

        tabLayout.setupWithViewPager(viewPager);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();
        switch (id) {

            case android.R.id.home:

                finish();
                break;
            default:
                break;
        }
        return true;
    }

    @Override
    public void onPageScrolled(int i, float v, int i1) {

    }

    @Override
    public void onPageSelected(int i) {

    }

    @Override
    public void onPageScrollStateChanged(int i) {

    }
}
