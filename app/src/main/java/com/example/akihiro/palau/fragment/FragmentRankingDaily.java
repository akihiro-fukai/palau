package com.example.akihiro.palau.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.format.DateFormat;

import com.example.akihiro.palau.R;
import com.example.akihiro.palau.adapter.RankingCardRecyclerAdapter;
import com.example.akihiro.palau.net.NetConfig;
import com.example.akihiro.palau.net.common.RequestParam;
import com.example.akihiro.palau.net.common.RequestType;
import com.example.akihiro.palau.net.response.RankingDetailResponse;
import com.example.akihiro.palau.net.response.RankingResponse;
import com.example.akihiro.palau.net.response.item.Ranking;
import com.example.akihiro.palau.net.response.item.RankingDetail;
import com.example.akihiro.palau.parser.JsonParser;

import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;

import static com.example.akihiro.palau.net.common.RequestType.RANKING_DAILY;
import static com.example.akihiro.palau.net.common.RequestType.RANKING_DETAIL;
import static com.example.akihiro.palau.net.common.RequestParam.GZIP;
import static com.example.akihiro.palau.net.common.RequestParam.LIMIT;
import static com.example.akihiro.palau.net.common.RequestParam.NCODE;
import static com.example.akihiro.palau.net.common.RequestParam.RTYPE;

public class FragmentRankingDaily extends FragmentBase {

    private static FragmentRankingDaily sSelf;

    private HashMap<String, Integer> mRankingMap = new HashMap<>();
    private List<RankingDetail> mRankingDetails;

    public static FragmentRankingDaily getInstance() {

        if (null == sSelf) {

            sSelf = new FragmentRankingDaily();
        }
        return sSelf;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        if (null != mRankingDetails) {

            setRankingDetailView(mRankingDetails);
        } else {

            final String inFormat = "yyyyMMdd";
            String date = DateFormat.format(inFormat, Calendar.getInstance()).toString();

            requestRankingDaily(date);
        }
    }

    // ------------------------------
    // 通信要求
    // ------------------------------

    private void requestRankingDaily(String date) {

        HashMap<RequestParam, String> params = new HashMap<>();
        params.put(GZIP, "5");
        params.put(RTYPE, date + "-d");

        httpRequest(NetConfig.RANK_HOST, params, RANKING_DAILY);
    }

    private void requestRankingDailyDetail(List<Ranking> rankings) {

        HashMap<RequestParam, String> params = new HashMap<>();
        params.put(GZIP, "5");
        params.put(LIMIT, String.valueOf(rankings.size()));

        StringBuilder stringBuilder = new StringBuilder("");
        for (Ranking ranking : rankings) {

            String nCode = ranking.getNCode();
            if (!stringBuilder.toString().isEmpty()) {

                stringBuilder.append("-");
            }
            stringBuilder.append(nCode);
        }
        params.put(NCODE, stringBuilder.toString());

        httpRequest(NetConfig.NOVEL_HOST, params, RANKING_DETAIL);
    }

    @Override
    protected void onSuccess(RequestType type, String result) {

        if (RANKING_DAILY == type) {

            handleRankingDaily(result);
        } else if (RANKING_DETAIL == type) {

            handleRankingDailyDetail(result);
        }
    }

    private void handleRankingDaily(String result) {

        if (null != result && !result.isEmpty()) {

            RankingResponse rankingResponse = JsonParser.parseRanking(result);

            List<Ranking> rankings = rankingResponse.getRankinItems();
            for (Ranking ranking : rankings) {

                mRankingMap.put(ranking.getNCode(), ranking.getRank());
            }

            requestRankingDailyDetail(rankings);
        } else {

            // 日間ランキングは1日3回更新されていますが、
            // このAPIでは午前4時～午前7時に作成した日間ランキングのみを蓄積しAPIで提供しています。
            // 深夜帯は当日のランキング取得エラーが発生するため前日のランキングを取得します。

            final String inFormat = "yyyyMMdd";
            Calendar calendar = Calendar.getInstance();
            calendar.add(Calendar.DAY_OF_MONTH, -1);
            String date = DateFormat.format(inFormat, calendar).toString();

            requestRankingDaily(date);
        }
    }

    private void handleRankingDailyDetail(String result) {

        RankingDetailResponse rankingDetailResponse = JsonParser.parseRankingDetail(result);
        if (null != rankingDetailResponse) {

            List<RankingDetail> rankingDetails = rankingDetailResponse.getRankingDetails();

            for (RankingDetail rankingDetail : rankingDetails) {

                if (mRankingMap.containsKey(rankingDetail.getNCode())) {

                    rankingDetail.setRank(mRankingMap.get(rankingDetail.getNCode()));
                }
            }
            sortAsc(rankingDetails);
            mRankingDetails = rankingDetails;

            setRankingDetailView(rankingDetails);
        }
    }

    private void setRankingDetailView(List<RankingDetail> rankingDetails) {

        RankingCardRecyclerAdapter adapter = new RankingCardRecyclerAdapter(getContext(), rankingDetails);
        RecyclerView recyclerView = (RecyclerView) getActivity().findViewById(R.id.ranking_daily_recycler_view);
        recyclerView.setHasFixedSize(true);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
    }

    @Override
    protected int getTitleResId() {

        return 0;
    }

    @Override
    protected int getLayoutResId() {

        return R.layout.fragment_ranking_daily;
    }
}
