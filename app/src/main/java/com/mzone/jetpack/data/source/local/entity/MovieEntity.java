package com.mzone.jetpack.data.source.local.entity;

public class MovieEntity {

    private String idMovie;
    private String titleMovie;
    private String originalTitleMovie;
    private String releaseDateMovie;
    private String overviewMovie;
    private String posterPathMovie;

    public MovieEntity(String idMovie, String titleMovie, String originalTitleMovie, String releaseDateMovie, String overviewMovie, String posterPathMovie) {
        this.idMovie = idMovie;
        this.titleMovie = titleMovie;
        this.originalTitleMovie = originalTitleMovie;
        this.releaseDateMovie = releaseDateMovie;
        this.overviewMovie = overviewMovie;
        this.posterPathMovie = posterPathMovie;
    }

    public String getIdMovie() {
        return idMovie;
    }

    public String getTitleMovie() {
        return titleMovie;
    }

    public String getOriginalTitleMovie() {
        return originalTitleMovie;
    }

    public String getReleaseDateMovie() {
        return releaseDateMovie;
    }

    public String getOverviewMovie() {
        return overviewMovie;
    }

    public String getPosterPathMovie() {
        return posterPathMovie;
    }

}
