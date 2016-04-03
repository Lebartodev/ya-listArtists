package com.lebartodev.artistslist.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Alexandr on 3/25/2016.
 */
public class ArtistsList {
    private List<Artist> artists = new ArrayList<>();

    public List<Artist> getArtists() {
        return artists;
    }

    public void setArtists(List<Artist> artists) {
        this.artists = artists;
    }
    public void addArtist(Artist artist){
        artists.add(artist);

    }

}
