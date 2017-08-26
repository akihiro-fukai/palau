package com.example.akihiro.palau.net.response.item;

public class RankingDetail {

    /**
     * 小説名
     */
    private String title;
    /**
     * Nコード
     */
    private String nCode;
    /**
     * 作者のユーザID(数値)
     */
    private int userId;
    /**
     * 作者名
     */
    private String writer;
    /**
     * 小説のあらすじ
     */
    private String story;
    /**
     * 大ジャンル
     */
    private int bigGenre;
    /**
     * ジャンル
     */
    private int genre;
    /**
     * 現在未使用項目(常に空文字列が返ります)
     */
    private String gensaku;
    /**
     * キーワード
     */
    private String keyword;
    /**
     * 初回掲載日(YYYY-MM-DD HH:MM:SSの形式)
     */
    private String generalFirstup;
    /**
     * 最終掲載日(YYYY-MM-DD HH:MM:SSの形式)
     */
    private String generalLastup;
    /**
     * 連載の場合は1、短編の場合は2
     */
    private int novelType;
    /**
     * 短編小説と完結済小説は0となっています。連載中は1です。
     */
    private int end;
    /**
     * 全掲載部分数です。短編の場合は1です。
     */
    private int generalAllNo;
    /**
     * 小説文字数です。スペースや改行は文字数としてカウントしません。
     */
    private int length;
    /**
     * 読了時間(分単位)です。読了時間は小説文字数÷500を切り上げした数値です。
     */
    private int time;
    /**
     * 長期連載中は1、それ以外は0です。
     */
    private int isStop;
    /**
     * 登録必須キーワードに「R15」が含まれる場合は1、それ以外は0です。
     */
    private int isR15;
    /**
     * 登録必須キーワードに「ボーイズラブ」が含まれる場合は1、それ以外は0です。
     */
    private int isBL;
    /**
     * 登録必須キーワードに「ガールズラブ」が含まれる場合は1、それ以外は0です。
     */
    private int isGL;
    /**
     * 登録必須キーワードに「残酷な描写あり」が含まれる場合は1、それ以外は0です。
     */
    private int isZankoku;
    /**
     * 登録必須キーワードに「異世界転生」が含まれる場合は1、それ以外は0です。
     */
    private int isTensei;
    /**
     * 登録必須キーワードに「異世界転移」が含まれる場合は1、それ以外は0です。
     */
    private int isTenni;
    /**
     * 1はケータイのみ、2はPCのみ、3はPCとケータイで投稿された作品です。<br/>
     * 対象は投稿と次話投稿時のみで、どの端末で執筆されたかを表すものではありません。
     */
    private int pcOrK;
    /**
     * 総合得点(=(ブックマーク数×2)+評価点)
     */
    private int globalPoint;
    /**
     * ブックマーク数
     */
    private int favNovelCnt;
    /**
     * レビュー数
     */
    private int reviewCnt;
    /**
     * 評価点
     */
    private int allPoint;
    /**
     * 評価者数
     */
    private int allHyokaCnt;
    /**
     * 挿絵の数
     */
    private int sasieCnt;
    /**
     * 会話率
     */
    private int kaiwaritu;
    /**
     * 小説の更新日時
     */
    private String novelUpdatedAt;
    /**
     * 最終更新日時<br/>
     * (注意：システム用で小説更新時とは関係ありません)
     */
    private String updatedAt;
    /**
     * ランキング
     */
    private int rank;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getNCode() {
        return nCode;
    }

    public void setNCode(String nCode) {
        this.nCode = nCode;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getWriter() {
        return writer;
    }

    public void setWriter(String writer) {
        this.writer = writer;
    }

    public String getStory() {
        return story;
    }

    public void setStory(String story) {
        this.story = story;
    }

    public int getBigGenre() {
        return bigGenre;
    }

    public void setBigGenre(int bigGenre) {
        this.bigGenre = bigGenre;
    }

    public int getGenre() {
        return genre;
    }

    public void setGenre(int genre) {
        this.genre = genre;
    }

    public String getGensaku() {
        return gensaku;
    }

    public void setGensaku(String gensaku) {
        this.gensaku = gensaku;
    }

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }

    public String getGeneralFirstup() {
        return generalFirstup;
    }

    public void setGeneralFirstup(String generalFirstup) {
        this.generalFirstup = generalFirstup;
    }

    public String getGeneralLastup() {
        return generalLastup;
    }

    public void setGeneralLastup(String generalLastup) {
        this.generalLastup = generalLastup;
    }

    public int getNovelType() {
        return novelType;
    }

    public void setNovelType(int novelType) {
        this.novelType = novelType;
    }

    public int getEnd() {
        return end;
    }

    public void setEnd(int end) {
        this.end = end;
    }

    public int getGeneralAllNo() {
        return generalAllNo;
    }

    public void setGeneralAllNo(int generalAllNo) {
        this.generalAllNo = generalAllNo;
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public int getTime() {
        return time;
    }

    public void setTime(int time) {
        this.time = time;
    }

    public int getIsStop() {
        return isStop;
    }

    public void setIsStop(int isStop) {
        this.isStop = isStop;
    }

    public int getIsR15() {
        return isR15;
    }

    public void setIsR15(int isR15) {
        this.isR15 = isR15;
    }

    public int getIsBL() {
        return isBL;
    }

    public void setIsBL(int isBL) {
        this.isBL = isBL;
    }

    public int getIsGL() {
        return isGL;
    }

    public void setIsGL(int isGL) {
        this.isGL = isGL;
    }

    public int getIsZankoku() {
        return isZankoku;
    }

    public void setIsZankoku(int isZankoku) {
        this.isZankoku = isZankoku;
    }

    public int getIsTensei() {
        return isTensei;
    }

    public void setIsTensei(int isTensei) {
        this.isTensei = isTensei;
    }

    public int getIsTenni() {
        return isTenni;
    }

    public void setIsTenni(int isTenni) {
        this.isTenni = isTenni;
    }

    public int getPcOrK() {
        return pcOrK;
    }

    public void setPcOrK(int pcOrK) {
        this.pcOrK = pcOrK;
    }

    public int getGlobalPoint() {
        return globalPoint;
    }

    public void setGlobalPoint(int globalPoint) {
        this.globalPoint = globalPoint;
    }

    public int getFavNovelCnt() {
        return favNovelCnt;
    }

    public void setFavNovelCnt(int favNovelCnt) {
        this.favNovelCnt = favNovelCnt;
    }

    public int getReviewCnt() {
        return reviewCnt;
    }

    public void setReviewCnt(int reviewCnt) {
        this.reviewCnt = reviewCnt;
    }

    public int getAllPoint() {
        return allPoint;
    }

    public void setAllPoint(int allPoint) {
        this.allPoint = allPoint;
    }

    public int getAllHyokaCnt() {
        return allHyokaCnt;
    }

    public void setAllHyokaCnt(int allHyokaCnt) {
        this.allHyokaCnt = allHyokaCnt;
    }

    public int getSasieCnt() {
        return sasieCnt;
    }

    public void setSasieCnt(int sasieCnt) {
        this.sasieCnt = sasieCnt;
    }

    public int getKaiwaritu() {
        return kaiwaritu;
    }

    public void setKaiwaritu(int kaiwaritu) {
        this.kaiwaritu = kaiwaritu;
    }

    public String getNovelUpdatedAt() {
        return novelUpdatedAt;
    }

    public void setNovelUpdatedAt(String novelUpdatedAt) {
        this.novelUpdatedAt = novelUpdatedAt;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
    }

    public int getRank() {
        return rank;
    }

    public void setRank(int rank) {
        this.rank = rank;
    }
}
