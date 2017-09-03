package com.example.akihiro.palau.net.response;

import com.example.akihiro.palau.net.response.item.NovelDetail;

import java.util.ArrayList;
import java.util.List;

public class RankingDetailResponse {

    private List<NovelDetail> novelDetails = new ArrayList<>();

    public List<NovelDetail> getNovelDetails() {
        return novelDetails;
    }

    public void setNovelDetails(NovelDetail novelDetail) {
        this.novelDetails.add(novelDetail);
    }
}
