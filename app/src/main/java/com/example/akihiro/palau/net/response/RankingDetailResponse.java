package com.example.akihiro.palau.net.response;

import com.example.akihiro.palau.net.response.item.RankingDetail;

import java.util.ArrayList;
import java.util.List;

public class RankingDetailResponse {

    private List<RankingDetail> rankingDetails = new ArrayList<>();

    public List<RankingDetail> getRankingDetails() {
        return rankingDetails;
    }

    public void setRankingDetails(RankingDetail rankingDetail) {
        this.rankingDetails.add(rankingDetail);
    }
}
