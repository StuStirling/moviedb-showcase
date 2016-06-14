package com.stustirling.moviedbshowcase.data.entity.tvshows;

import java.util.List;

/**
 * Created by Stu Stirling on 14/06/16.
 */
public class PopularTVShowsResponse {

    public List<TVShowEntity> results;

    public List<TVShowEntity> getTVShows() {
        return results;
    }
}
