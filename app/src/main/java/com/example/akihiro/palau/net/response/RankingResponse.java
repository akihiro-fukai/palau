package com.example.akihiro.palau.net.response;

import com.example.akihiro.palau.net.response.item.Ranking;

import java.util.ArrayList;
import java.util.List;

public class RankingResponse {

    private List<Ranking> rankinItems = new ArrayList<>();

    public List<Ranking> getRankinItems() {
        return rankinItems;
    }

    public void setRankinItem(Ranking rankinItem) {
        this.rankinItems.add(rankinItem);
    }
}
