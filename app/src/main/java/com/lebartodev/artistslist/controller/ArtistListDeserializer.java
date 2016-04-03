package com.lebartodev.artistslist.controller;

import com.google.gson.JsonArray;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.lebartodev.artistslist.model.Artist;
import com.lebartodev.artistslist.model.ArtistsList;

import java.lang.reflect.Type;

/**
 * Created by Alexandr on 3/25/2016.
 */
public class ArtistListDeserializer implements JsonDeserializer<ArtistsList> {


    @Override
    public ArtistsList deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {

        ArtistsList artistlist = new ArtistsList();
        JsonArray artists = json.getAsJsonArray();
        //Заполнение списка исполнителей
        //Десереализуем каждого исполнителя в отдельном десериалайзере и добавляем в список
        for(JsonElement art:artists) {
            Artist artist = (Artist) context.deserialize(art, Artist.class);
            artistlist.addArtist(artist);
        }
        return artistlist;

    }
}
