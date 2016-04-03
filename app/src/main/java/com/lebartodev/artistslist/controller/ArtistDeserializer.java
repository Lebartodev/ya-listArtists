package com.lebartodev.artistslist.controller;

import com.google.gson.JsonArray;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.lebartodev.artistslist.model.Artist;

import java.lang.reflect.Type;

/**
 * Created by Alexandr on 3/25/2016.
 */
public class ArtistDeserializer implements JsonDeserializer<Artist> {

//Десериализуем исполнителя
    @Override
    public Artist deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        Artist artist = new Artist();
        JsonObject data = json.getAsJsonObject();
        //Проверка на наличие поля id и присвоение его объекту artist
        // Аналогично для остальных полей
        if (data.has("id") && !data.get("id").isJsonNull())
            artist.setId(data.get("id").getAsInt());
        if (data.has("albums") && !data.get("albums").isJsonNull())
            artist.setAlbums(data.get("albums").getAsInt());
        if (data.has("tracks") && !data.get("tracks").isJsonNull())
            artist.setTracks(data.get("tracks").getAsInt());
        if (data.has("name") && !data.get("name").isJsonNull())
            artist.setName(data.get("name").getAsString());
        if (data.has("description") && !data.get("description").isJsonNull())
            artist.setDescription(data.get("description").getAsString());
        if (data.has("link") && !data.get("link").isJsonNull())
            artist.setLink(data.get("link").getAsString());
        if (data.has("genres") && !data.get("genres").isJsonNull()) {
            JsonArray genres = data.getAsJsonArray("genres");
            for (JsonElement genre : genres) {
                artist.getGenres().add((String) genre.getAsString());
            }
        }
        if (data.has("cover") && !data.get("cover").isJsonNull()) {
            JsonObject cover = data.getAsJsonObject("cover");

            if (cover.has("small") && !cover.get("small").isJsonNull())
                artist.setImageSmall(cover.get("small").getAsString());

            if (cover.has("big") && !cover.get("big").isJsonNull())
                artist.setImageBig(cover.get("big").getAsString());
        }
        System.out.println(artist.getName());
        return artist;


    }
}
