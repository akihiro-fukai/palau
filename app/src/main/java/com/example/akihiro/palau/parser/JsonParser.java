package com.example.akihiro.palau.parser;

import com.example.akihiro.palau.net.response.RankingDetailResponse;
import com.example.akihiro.palau.net.response.RankingResponse;
import com.example.akihiro.palau.net.response.item.Ranking;
import com.example.akihiro.palau.net.response.item.RankingDetail;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class JsonParser {

    private static final String RANKING_JSON_HEADER = "naroucallback([";
    private static final String RANKING_JSON_REPLACE_HEADER = "{naroucallback:[";
    private static final String RANKING_JSON_FOOTER = "]);";
    private static final String RANKING_JSON_REPLACE_FOOTER = "]}";

    public static RankingResponse parseRanking(String jsonString) {

        RankingResponse rankingResponse = null;
        try {

            rankingResponse = new RankingResponse();

            jsonString = jsonString.replace(RANKING_JSON_HEADER, RANKING_JSON_REPLACE_HEADER);
            jsonString = jsonString.replace(RANKING_JSON_FOOTER, RANKING_JSON_REPLACE_FOOTER);

            JSONObject jsonObject = new JSONObject(jsonString);
            JSONArray jsonArray = jsonObject.getJSONArray("naroucallback");

            for (int i = 0; i < jsonArray.length(); i++) {

                JSONObject data = jsonArray.getJSONObject(i);

                Ranking ranking = new Ranking();

                ranking.setNCode(data.getString("ncode"));
                ranking.setPoint(data.getInt("pt"));
                ranking.setRank(data.getInt("rank"));

                rankingResponse.setRankinItem(ranking);
            }
        } catch (JSONException e) {

            e.printStackTrace();
        }
        return rankingResponse;
    }

    public static RankingDetailResponse parseRankingDetail(String jsonString) {

        RankingDetailResponse rankingDetailResponse = null;
        try {

            rankingDetailResponse = new RankingDetailResponse();

            jsonString = jsonString.replace(RANKING_JSON_HEADER, RANKING_JSON_REPLACE_HEADER);
            jsonString = jsonString.replace(RANKING_JSON_FOOTER, RANKING_JSON_REPLACE_FOOTER);

            JSONObject jsonObject = new JSONObject(jsonString);
            JSONArray jsonArray = jsonObject.getJSONArray("naroucallback");

            for (int i = 0; i < jsonArray.length(); i++) {

                JSONObject data = jsonArray.getJSONObject(i);

                boolean hasAllCount = data.has("allcount");
                if (hasAllCount) {

                    continue;
                }

                RankingDetail rankingDetail = new RankingDetail();
                rankingDetail.setTitle(data.getString("title"));
                rankingDetail.setNCode(data.getString("ncode"));
                rankingDetail.setUserId(data.getInt("userid"));
                rankingDetail.setWriter(data.getString("writer"));
                rankingDetail.setStory(data.getString("story"));
                rankingDetail.setBigGenre(data.getInt("biggenre"));
                rankingDetail.setGenre(data.getInt("genre"));
                rankingDetail.setGensaku(data.getString("gensaku"));
                rankingDetail.setKeyword(data.getString("keyword"));
                rankingDetail.setGeneralFirstup(data.getString("general_firstup"));
                rankingDetail.setGeneralLastup(data.getString("general_lastup"));
                rankingDetail.setNovelType(data.getInt("novel_type"));
                rankingDetail.setEnd(data.getInt("end"));
                rankingDetail.setGeneralAllNo(data.getInt("general_all_no"));
                rankingDetail.setLength(data.getInt("length"));
                rankingDetail.setTime(data.getInt("time"));
                rankingDetail.setIsStop(data.getInt("isstop"));
                rankingDetail.setIsR15(data.getInt("isr15"));
                rankingDetail.setIsBL(data.getInt("isbl"));
                rankingDetail.setIsGL(data.getInt("isgl"));
                rankingDetail.setIsZankoku(data.getInt("iszankoku"));
                rankingDetail.setIsTensei(data.getInt("istensei"));
                rankingDetail.setIsTenni(data.getInt("istenni"));
                rankingDetail.setPcOrK(data.getInt("pc_or_k"));
                rankingDetail.setGlobalPoint(data.getInt("global_point"));
                rankingDetail.setFavNovelCnt(data.getInt("fav_novel_cnt"));
                rankingDetail.setReviewCnt(data.getInt("review_cnt"));
                rankingDetail.setAllPoint(data.getInt("all_point"));
                rankingDetail.setAllHyokaCnt(data.getInt("all_hyoka_cnt"));
                rankingDetail.setSasieCnt(data.getInt("sasie_cnt"));
                rankingDetail.setKaiwaritu(data.getInt("kaiwaritu"));
                rankingDetail.setNovelUpdatedAt(data.getString("novelupdated_at"));
                rankingDetail.setUpdatedAt(data.getString("updated_at"));

                rankingDetailResponse.setRankingDetails(rankingDetail);
            }
        } catch (JSONException e) {

            e.printStackTrace();
        }
        return rankingDetailResponse;
    }
}
