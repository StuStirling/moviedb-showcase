package com.stustirling.moviedbshowcase;

/**
 * Created by Stu Stirling on 15/06/16.
 */
public class TVShowGenre {

    public static String getName(int id) {
        switch (id) {
            case 10759:
                return "Action & Adventure";
            case 16:
                return "Animation";
            case 35:
                return "Comedy";
            case 99:
                return "Documentary";
            case 18:
                return "Drama";
            case 10761:
                return "Education";
            case 10751:
                return "Family";
            case 10762:
                return "Kids";
            case 9648:
                return "Mystery";
            case 10763:
                return "News";
            case 10764:
                return "Reality";
            case 10765:
                return "Sci-Fi & Fantasy";
            case 10766:
                return "Soap";
            case 10767:
                return "Talk";
            case 10768:
                return "Way & Politics";
            case 37:
                return "Western";
            default:
                return null;
        }
    }

}
