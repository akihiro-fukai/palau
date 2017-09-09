package com.example.akihiro.palau.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.akihiro.palau.adapter.RankingListRecyclerAdapter;
import com.example.akihiro.palau.net.AsyncNarouRankingApiClient;
import com.example.akihiro.palau.net.NetConfig;
import com.example.akihiro.palau.net.common.RequestParam;
import com.example.akihiro.palau.net.common.RequestType;
import com.example.akihiro.palau.net.response.RankingDetailResponse;
import com.example.akihiro.palau.net.response.item.NovelDetail;
import com.example.akihiro.palau.parser.JsonParser;

import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;

import narou4j.entities.NovelRank;
import narou4j.enums.RankingType;

import static com.example.akihiro.palau.net.common.RequestParam.GZIP;
import static com.example.akihiro.palau.net.common.RequestParam.LIMIT;
import static com.example.akihiro.palau.net.common.RequestParam.NCODE;
import static com.example.akihiro.palau.net.common.RequestType.RANKING_DETAIL;

public abstract class RankingBaseNarouApiFragment extends NarouApiBaseFragment implements AsyncNarouRankingApiClient.OnNarouRankingApiListener {

    private HashMap<String, Integer> mRankingMap = new HashMap<>();
    private List<NovelDetail> mNovelDetails;

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        if (null != mNovelDetails) {

            setRankingDetailView(mNovelDetails);
        } else {

            requestRanking(getRType());
        }
    }

    // ------------------------------
    // 通信要求
    // ------------------------------

    // ------------------------------
    // ランキング取得
    // ------------------------------

    /**
     * ランキングを取得します。
     *
     * @param rType ランキングの項目
     */
    private void requestRanking(RankingType rType) {

        AsyncNarouRankingApiClient client = new AsyncNarouRankingApiClient();
        client.setOnNarouRankingApiListener(this);
        client.execute(rType);
    }

    @Override
    public void onSuccess(List<NovelRank> novelRanks) {

        for (NovelRank novelRank : novelRanks) {

            mRankingMap.put(novelRank.getNcode(), novelRank.getRank());
        }

        requestRankingDetail(novelRanks);
    }

    @Override
    public void onConnectedError() {

    }

    // ------------------------------
    // ランキング詳細取得
    // ------------------------------

    /**
     * ランキングのNコードを指定して詳細情報を取得します。
     *
     * @param novelRanks ランキング情報
     */
    private void requestRankingDetail(List<NovelRank> novelRanks) {

        HashMap<RequestParam, String> params = new HashMap<>();
        params.put(GZIP, "5");
        params.put(LIMIT, String.valueOf(novelRanks.size()));

        StringBuilder stringBuilder = new StringBuilder("");
        for (NovelRank novelRank : novelRanks) {

            String nCode = novelRank.getNcode();
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

            case RANKING_DETAIL:

                handleRankingDetail(result);
                break;
            default:
                break;
        }
    }

    // ------------------------------
    // 初期化
    // ------------------------------

    private void handleRankingDetail(String result) {

        RankingDetailResponse rankingDetailResponse = JsonParser.parseRankingDetail(result);
        if (null != rankingDetailResponse) {

            List<NovelDetail> novelDetails = rankingDetailResponse.getNovelDetails();

            for (NovelDetail novelDetail : novelDetails) {

                if (mRankingMap.containsKey(novelDetail.getNCode())) {

                    novelDetail.setRank(mRankingMap.get(novelDetail.getNCode()));
                }
            }
            sortAsc(novelDetails);
            mNovelDetails = novelDetails;

            setRankingDetailView(novelDetails);
        } else {

            // TODO json解析失敗：エラーハンドリング
        }
    }

    private void setRankingDetailView(List<NovelDetail> novelDetails) {

        RankingListRecyclerAdapter adapter = new RankingListRecyclerAdapter(
                getContext(),
                getActivity().getSupportFragmentManager(),
                novelDetails);
        RecyclerView recyclerView = (RecyclerView) getActivity().findViewById(getRankingDetailViewId());
        if (null != recyclerView) {

            recyclerView.setHasFixedSize(true);

            RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity());
            recyclerView.setLayoutManager(layoutManager);
            recyclerView.setAdapter(adapter);
        }
    }

    protected static void sortAsc(List<NovelDetail> novelDetails) {

        Collections.sort(novelDetails, new Comparator<NovelDetail>() {

            public int compare(NovelDetail o1, NovelDetail o2) {

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

    protected abstract RankingType getRType();
}
