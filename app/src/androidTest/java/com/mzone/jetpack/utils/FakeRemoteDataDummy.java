package com.mzone.jetpack.utils;

import com.mzone.jetpack.data.source.local.entity.MovieEntity;
import com.mzone.jetpack.data.source.local.entity.TvEntity;

import java.util.ArrayList;

public class FakeRemoteDataDummy {

    public static ArrayList<MovieEntity> generateRemoteDummyMovies() {

        ArrayList<MovieEntity> movies = new ArrayList<>();

        movies.add(new MovieEntity("475557",
                "Joker",
                "Joker",
                "2019-10-04",
                "During the 1980s, a failed stand-up comedian is driven insane and turns to a life of crime and chaos in Gotham City while becoming an infamous psychopathic crime figure.",
                "/udDclJoHjfjb8Ekgsd4FDteOkCU.jpg"));
        movies.add(new MovieEntity("420809",
                "Maleficent: Mistress of Evil",
                "Maleficent: Mistress of Evil",
                "2019-10-18",
                "Maleficent and her goddaughter Aurora begin to question the complex family ties that bind them as they are pulled in different directions by impending nuptials, unexpected allies, and dark new forces at play.",
                "/tBuabjEqxzoUBHfbyNbd8ulgy5j.jpg"));
        return movies;
    }

    public static ArrayList<TvEntity> generateRemoteDummyTv() {

        ArrayList<TvEntity> tv = new ArrayList<>();

        tv.add(new TvEntity("62286",
                "Fear the Walking Dead",
                "Fear the Walking Dead",
                "2015-08-23",
                "What did the world look like as it was transforming into the horrifying apocalypse depicted in \"The Walking Dead\"? This spin-off set in Los Angeles, following new characters as they face the beginning of the end of the world, will answer that question.",
                "/lZMb3R3e5vqukPbeDMeyYGf2ZNG.jpg"));
        tv.add(new TvEntity("1412",
                "Arrow",
                "Arrow",
                "2012-10-10",
                "Spoiled billionaire playboy Oliver Queen is missing and presumed dead when his yacht is lost at sea. He returns five years later a changed man, determined to clean up the city as a hooded vigilante armed with a bow.",
                "/mo0FP1GxOFZT4UDde7RFDz5APXF.jpg"));
        return tv;
    }
}
