package com.example.akihiro.palau.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.example.akihiro.palau.fragment.FragmentNovelBody;

import java.util.List;

import narou4j.entities.NovelBody;

/**
 * NovelPagePagerAdapterクラスは小説本文を表示する機能を提供します。
 */
public class NovelPagePagerAdapter extends FragmentPagerAdapter {

    // 小説詳細情報
    private List<NovelBody> mNovelBodies;

    /**
     * コンストラクタ
     *
     * @param fragmentManager フラグメントマネージャー
     * @param novelBodies     小説詳細情報
     */
    public NovelPagePagerAdapter(FragmentManager fragmentManager, List<NovelBody> novelBodies) {
        super(fragmentManager);

        mNovelBodies = novelBodies;
    }

    @Override
    public Fragment getItem(int position) {

        FragmentNovelBody fragmentNovelBody = new FragmentNovelBody();
        fragmentNovelBody.setNovelBody(mNovelBodies.get(position));

        return fragmentNovelBody;
    }

    @Override
    public CharSequence getPageTitle(int position) {

        if (null != mNovelBodies && mNovelBodies.size() > 0) {

            mNovelBodies.get(position).getTitle();
        }
        return null;
    }

    @Override
    public int getCount() {


        if (null != mNovelBodies) {

            return mNovelBodies.size();
        }
        return 0;
    }
}
