package com.example.akihiro.palau.net;

import android.os.AsyncTask;

import java.util.List;

import narou4j.Ranking;
import narou4j.entities.NovelRank;
import narou4j.enums.RankingType;

public class AsyncNarouRankingApiClient extends AsyncTask<RankingType, Void, List<NovelRank>> {

    // ランキングAPIの実行結果を通知するインタフェース
    private OnNarouRankingApiListener mListener;

    @Override
    protected List<NovelRank> doInBackground(RankingType... rankingTypes) {

        Ranking ranking = new Ranking();
        return ranking.getRanking(rankingTypes[0]);
    }

    @Override
    protected void onPostExecute(List<NovelRank> novelRanks) {

        if (null != novelRanks) {

            if (null != mListener) {

                mListener.onSuccess(novelRanks);
            }
        } else {

            mListener.onConnectedError();
        }
    }

    /**
     * ランキングAPIの実行結果を受診します。
     *
     * @param listener ランキングAPIの実行結果を通知するインタフェース
     */
    public void setOnNarouRankingApiListener(OnNarouRankingApiListener listener) {

        mListener = listener;
    }

    /**
     * ランキングAPIの実行結果を通知するインタフェースです。
     */
    public interface OnNarouRankingApiListener {

        void onSuccess(List<NovelRank> novelRanks);
        void onConnectedError();
    }
}
