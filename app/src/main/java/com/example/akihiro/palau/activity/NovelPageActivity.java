package com.example.akihiro.palau.activity;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;

import com.example.akihiro.palau.R;
import com.example.akihiro.palau.adapter.RankingPagerAdapter;
import com.example.akihiro.palau.fragment.FragmentNovelPage;
import com.example.akihiro.palau.fragment.FragmentTopPage;

import static com.example.akihiro.palau.common.UICommonUtil.NOVEL_PAGE_NCODE;

public class NovelPageActivity extends AppCompatActivity implements ViewPager.OnPageChangeListener {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_novel_page);

        ActionBar actionBar = getSupportActionBar();
        if (null != actionBar) {

            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setHomeButtonEnabled(true);
        }

        // --------------------
        // フラグメント初期化
        // --------------------

        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        FragmentNovelPage fragmentTopPage = new FragmentNovelPage();
        fragmentTopPage.setArguments(getIntent().getExtras());
        fragmentTransaction.add(R.id.fragment_container, fragmentTopPage);
        fragmentTransaction.commit();
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
