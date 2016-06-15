package com.stustirling.moviedbshowcase.data.entity.person;

import java.util.List;

/**
 * Created by Stu Stirling on 15/06/16.
 */
public class PopularPeopleResponse {

    List<PersonEntity> results;

    public List<PersonEntity> getPeople() {
        return results;
    }
}
