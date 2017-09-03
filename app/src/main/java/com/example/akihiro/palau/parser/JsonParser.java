package com.example.akihiro.palau.parser;

import com.example.akihiro.palau.net.response.RankingDetailResponse;
import com.example.akihiro.palau.net.response.RankingResponse;
import com.example.akihiro.palau.net.response.item.Ranking;
import com.example.akihiro.palau.net.response.item.NovelDetail;

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

                NovelDetail novelDetail = new NovelDetail();
                novelDetail.setTitle(data.getString("title"));
                novelDetail.setNCode(data.getString("ncode"));
                novelDetail.setUserId(data.getInt("userid"));
                novelDetail.setWriter(data.getString("writer"));
                novelDetail.setStory(data.getString("story"));
                novelDetail.setBigGenre(data.getInt("biggenre"));
                novelDetail.setGenre(data.getInt("genre"));
                novelDetail.setGensaku(data.getString("gensaku"));
                novelDetail.setKeyword(data.getString("keyword"));
                novelDetail.setGeneralFirstup(data.getString("general_firstup"));
                novelDetail.setGeneralLastup(data.getString("general_lastup"));
                novelDetail.setNovelType(data.getInt("novel_type"));
                novelDetail.setEnd(data.getInt("end"));
                novelDetail.setGeneralAllNo(data.getInt("general_all_no"));
                novelDetail.setLength(data.getInt("length"));
                novelDetail.setTime(data.getInt("time"));
                novelDetail.setIsStop(data.getInt("isstop"));
                novelDetail.setIsR15(data.getInt("isr15"));
                novelDetail.setIsBL(data.getInt("isbl"));
                novelDetail.setIsGL(data.getInt("isgl"));
                novelDetail.setIsZankoku(data.getInt("iszankoku"));
                novelDetail.setIsTensei(data.getInt("istensei"));
                novelDetail.setIsTeni(data.getInt("istenni"));
                novelDetail.setPcOrK(data.getInt("pc_or_k"));
                novelDetail.setGlobalPoint(data.getInt("global_point"));
                novelDetail.setFavNovelCnt(data.getInt("fav_novel_cnt"));
                novelDetail.setReviewCnt(data.getInt("review_cnt"));
                novelDetail.setAllPoint(data.getInt("all_point"));
                novelDetail.setAllHyokaCnt(data.getInt("all_hyoka_cnt"));
                novelDetail.setSasieCnt(data.getInt("sasie_cnt"));
                novelDetail.setKaiwaritu(data.getInt("kaiwaritu"));
                novelDetail.setNovelUpdatedAt(data.getString("novelupdated_at"));
                novelDetail.setUpdatedAt(data.getString("updated_at"));

                rankingDetailResponse.setNovelDetails(novelDetail);
            }
        } catch (JSONException e) {

            e.printStackTrace();
        }
        return rankingDetailResponse;
    }
}
