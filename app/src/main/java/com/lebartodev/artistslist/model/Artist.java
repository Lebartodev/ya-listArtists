package com.lebartodev.artistslist.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.text.Normalizer;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Alexandr on 3/25/2016.
 */

public class Artist implements Parcelable {
    private int id = 0, tracks = 0, albums = 0;
    private String name, description;
    private List<String> genres = new ArrayList<>();
    private String link;
    private String imageBig, imageSmall;

    protected Artist(Parcel in) {
        id = in.readInt();
        tracks = in.readInt();
        albums = in.readInt();
        name = in.readString();
        description = in.readString();
        genres = in.createStringArrayList();
        link = in.readString();
        imageBig = in.readString();
        imageSmall = in.readString();
    }

    public Artist() {

    }

    public static final Creator<Artist> CREATOR = new Creator<Artist>() {
        @Override
        public Artist createFromParcel(Parcel in) {
            return new Artist(in);
        }

        @Override
        public Artist[] newArray(int size) {
            return new Artist[size];
        }
    };

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getTracks() {
        return tracks;
    }

    public void setTracks(int tracks) {
        this.tracks = tracks;
    }

    public int getAlbums() {
        return albums;
    }

    public void setAlbums(int albums) {
        this.albums = albums;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        //Кодировка русских символов
        try {
            this.name = new String(name.getBytes("windows-1251"), "UTF-8");
        } catch (UnsupportedEncodingException e) {
            this.name = name;
        }
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        //Кодировка русских символов
        try {
            this.description = new String(description.getBytes("windows-1251"), "UTF-8");
        } catch (UnsupportedEncodingException e) {
            this.description = description;
        }
    }

    public List<String> getGenres() {
        return genres;
    }

    public void setGenres(List<String> genres) {
        this.genres = genres;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getImageBig() {
        return imageBig;
    }

    public void setImageBig(String imageBig) {
        this.imageBig = imageBig;
    }

    public String getImageSmall() {
        return imageSmall;
    }

    public void setImageSmall(String imageSmall) {
        this.imageSmall = imageSmall;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeInt(tracks);
        dest.writeInt(albums);
        dest.writeString(name);
        dest.writeString(description);
        dest.writeStringList(genres);
        dest.writeString(link);
        dest.writeString(imageBig);
        dest.writeString(imageSmall);
    }
}
