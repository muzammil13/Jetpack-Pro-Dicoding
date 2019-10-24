package com.mzone.jetpack.data.source.local.entity;

public class TvEntity {

    private String idTv;
    private String titleTv;
    private String originalTitleTv;
    private String firstAirDateTv;
    private String overviewTv;
    private String posterPathTv;

    public TvEntity(String idTv, String titleTv, String originalTitleTv, String firstAirDateTv, String overviewTv, String posterPathTv) {
        this.idTv = idTv;
        this.titleTv = titleTv;
        this.originalTitleTv = originalTitleTv;
        this.firstAirDateTv = firstAirDateTv;
        this.overviewTv = overviewTv;
        this.posterPathTv = posterPathTv;
    }

    public String getIdTv() {
        return idTv;
    }

    public String getTitleTv() {
        return titleTv;
    }

    public String getOriginalTitleTv() {
        return originalTitleTv;
    }

    public String getFirstAirDateTv() {
        return firstAirDateTv;
    }

    public String getOverviewTv() {
        return overviewTv;
    }

    public String getPosterPathTv() {
        return posterPathTv;
    }

}
