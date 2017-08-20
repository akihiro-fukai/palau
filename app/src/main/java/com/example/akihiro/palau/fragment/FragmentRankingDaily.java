package com.example.akihiro.palau.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.format.DateFormat;

import com.example.akihiro.palau.R;
import com.example.akihiro.palau.net.NetConfig;

import java.util.Calendar;
import java.util.HashMap;

import static com.example.akihiro.palau.common.UICommon.BUNDLE_PAGER_PAGE;

public class FragmentRankingDaily extends FragmentBase {

    public static FragmentRankingDaily newInstance() {

        FragmentRankingDaily fragment = new FragmentRankingDaily();

        return fragment;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        final String inFormat = "yyyyMMdd";
        String date = DateFormat.format(inFormat, Calendar.getInstance()).toString();

        HashMap<String, String> params = new HashMap<>();
        params.put("gzip", "5");
        params.put("rtype", date + "-d");

        httpRequest(NetConfig.RANK_HOST, params);
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
