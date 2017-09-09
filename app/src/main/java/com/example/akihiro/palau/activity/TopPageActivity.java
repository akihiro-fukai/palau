package com.example.akihiro.palau.activity;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

import com.example.akihiro.palau.R;
import com.example.akihiro.palau.fragment.DownloadListFragment;
import com.example.akihiro.palau.fragment.RankingFragment;

/**
 * TopPageActivityクラスは第一階層の画面を管理する機能を提供します。
 */
public class TopPageActivity extends NavigationActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        /*
         * ダウンロード一覧画面を表示
         */
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        DownloadListFragment downloadListFragment = new DownloadListFragment();
        fragmentTransaction.add(R.id.fragment_container, downloadListFragment);
        fragmentTransaction.commit();
    }

    // ------------------------------
    // コールバック
    // ------------------------------

    @Override
    protected int getScreenLayoutId() {

        return R.layout.activity_main;
    }

    @Override
    protected int getNavigationLayoutId() {

        return R.id.drawer_layout;
    }

    @Override
    protected void onNavigationItemSelected(int id) {

        switch (id) {

            // トップ
            case R.id.nav_novel_top:

                onClickHome();
                break;
            // ランキング
            case R.id.nav_novel_rank:

                onClickRanking();
                break;
            default:
                break;
        }
    }

    // ------------------------------
    // クリックイベント
    // ------------------------------

    /**
     * トップを表示します。
     */
    private void onClickHome() {

        DownloadListFragment downloadListFragment = new DownloadListFragment();
        replace(downloadListFragment);
    }

    /**
     * ランキングを表示します。
     */
    private void onClickRanking() {

        RankingFragment rankingFragment = new RankingFragment();
        replace(rankingFragment);
    }
}
