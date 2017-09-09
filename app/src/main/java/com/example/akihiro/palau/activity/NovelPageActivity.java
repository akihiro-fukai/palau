package com.example.akihiro.palau.activity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;

import com.example.akihiro.palau.R;
import com.example.akihiro.palau.adapter.NovelPagePagerAdapter;
import com.example.akihiro.palau.database.NarouDao;

import java.util.List;

import narou4j.entities.NovelBody;

import static com.example.akihiro.palau.common.UICommonUtil.NOVEL_NCODE;
import static com.example.akihiro.palau.common.UICommonUtil.NOVEL_PAGE;

public class NovelPageActivity extends AppCompatActivity implements ViewPager.OnPageChangeListener {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_novel_body);

        ActionBar actionBar = getSupportActionBar();
        if (null != actionBar) {

            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setHomeButtonEnabled(true);
        }

        // --------------------
        // フラグメント初期化
        // --------------------

        Bundle bundle = getIntent().getExtras();
        String nCode = bundle.getString(NOVEL_NCODE);
        int page = bundle.getInt(NOVEL_PAGE);
        AsyncNovelBodyLoadTask client = new AsyncNovelBodyLoadTask(nCode, page);
        client.execute();
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

    private class AsyncNovelBodyLoadTask extends AsyncTask<Void, Void, List<NovelBody>> {

        private String mNCode;
        private int mPage;

        AsyncNovelBodyLoadTask(String nCode, int page) {

            mNCode = nCode;
            mPage = page;
        }

        @Override
        protected List<NovelBody> doInBackground(Void... voids) {

            NarouDao dao = new NarouDao(getApplicationContext());
            return dao.findNovelBodyByNCode(mNCode);
        }

        @Override
        protected void onPostExecute(List<NovelBody> novelBodies) {

            setNovelBody(novelBodies);
        }

        /**
         * ダウンロードした小説の内容を表示します。
         *
         * @param novelBodies 小説の基本情報
         */
        private void setNovelBody(List<NovelBody> novelBodies) {

            ViewPager viewPager = (ViewPager) findViewById(R.id.pager);
            NovelPagePagerAdapter rankingPagerAdapter = new NovelPagePagerAdapter(getSupportFragmentManager(), novelBodies);
            viewPager.setAdapter(rankingPagerAdapter);
            viewPager.setCurrentItem(mPage);
        }
    }
}
