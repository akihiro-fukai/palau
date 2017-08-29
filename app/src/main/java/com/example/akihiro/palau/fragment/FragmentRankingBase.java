package com.example.akihiro.palau.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.akihiro.palau.adapter.RankingCardRecyclerAdapter;
import com.example.akihiro.palau.net.NetConfig;
import com.example.akihiro.palau.net.common.RequestParam;
import com.example.akihiro.palau.net.common.RequestType;
import com.example.akihiro.palau.net.response.RankingDetailResponse;
import com.example.akihiro.palau.net.response.RankingResponse;
import com.example.akihiro.palau.net.response.item.Ranking;
import com.example.akihiro.palau.net.response.item.RankingDetail;
import com.example.akihiro.palau.parser.JsonParser;

import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;

import static com.example.akihiro.palau.net.common.RequestParam.GZIP;
import static com.example.akihiro.palau.net.common.RequestParam.LIMIT;
import static com.example.akihiro.palau.net.common.RequestParam.NCODE;
import static com.example.akihiro.palau.net.common.RequestParam.RTYPE;
import static com.example.akihiro.palau.net.common.RequestType.RANKING_DETAIL;
import static com.example.akihiro.palau.net.common.RequestType.RANKING_MONTHLY;

public abstract class FragmentRankingBase extends FragmentBase {

    private HashMap<String, Integer> mRankingMap = new HashMap<>();
    private List<RankingDetail> mRankingDetails;
    protected boolean mIsRetryRTypeRequest;

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        if (null != mRankingDetails) {

            setRankingDetailView(mRankingDetails);
        } else {

            String rType = getRType();

            requestRanking(rType);
        }
    }

    // ------------------------------
    // 通信要求
    // ------------------------------

    protected void requestRanking(String rType) {

        HashMap<RequestParam, String> params = new HashMap<>();
        params.put(GZIP, "5");
        params.put(RTYPE, rType);

        httpRequest(NetConfig.RANK_HOST, params, RANKING_MONTHLY);
    }

    protected void requestRankingDailyDetail(List<Ranking> rankings) {

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

        switch (type) {

            case RANKING_DAILY:
            case RANKING_WEEKLY:
            case RANKING_MONTHLY:
            case RANKING_QUARTER:

                handleRanking(result);
                break;
            case RANKING_DETAIL:

                handleRankingDetail(result);
                break;
        }
    }

    // ------------------------------
    // 初期化
    // ------------------------------

    private void handleRanking(String result) {

        if (null != result && !result.isEmpty()) {

            RankingResponse rankingResponse = JsonParser.parseRanking(result);

            List<Ranking> rankings = rankingResponse.getRankinItems();
            for (Ranking ranking : rankings) {

                mRankingMap.put(ranking.getNCode(), ranking.getRank());
            }

            requestRankingDailyDetail(rankings);
        } else {

            String rType = getRetryRType();
            if (null == rType) {

                onRTypeError();

                return;
            }

            requestRanking(rType);
        }
    }

    private void handleRankingDetail(String result) {

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

        RankingCardRecyclerAdapter adapter = new RankingCardRecyclerAdapter(
                getContext(),
                getActivity().getSupportFragmentManager(),
                rankingDetails);
        RecyclerView recyclerView = (RecyclerView) getActivity().findViewById(getRankingDetailViewId());
        recyclerView.setHasFixedSize(true);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
    }

    protected static void sortAsc(List<RankingDetail> rankingDetails) {

        Collections.sort(rankingDetails, new Comparator<RankingDetail>() {

            public int compare(RankingDetail o1, RankingDetail o2) {

                if (o1.getRank() < o2.getRank()) {

                    return -1;
                }

                if (o1.getRank() > o2.getRank()) {

                    return 1;
                }
                return 0;
            }
        });
    }

    protected abstract int getLayoutResId();

    protected abstract int getRankingDetailViewId();

    protected abstract String getRType();

    protected abstract String getRetryRType();

    protected abstract void onRTypeError();
}
