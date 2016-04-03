package com.lebartodev.artistslist.controller;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.lebartodev.artistslist.model.Artist;
import com.lebartodev.artistslist.model.ArtistsList;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;

/**
 * Created by Alexandr on 3/25/2016.
 */
public class Controller {
    private final String siteName = "http://download.cdn.yandex.net/mobilization-2016/artists.json";
    private final String fileName="artistList.txt";
//Загрузка списка исполнителей из интернета
    public ArtistsList loadArtists() {
        try {

            URL urlObj = new URL(siteName);
            //Для правильного отображение русских символов
            InputStreamReader isr = new InputStreamReader(urlObj.openStream(), "windows-1251");
            BufferedReader br = new BufferedReader(isr);
            StringBuilder a = new StringBuilder();
            int cp;
            //Читаем файл
            while ((cp = br.read()) != -1) {
                a.append((char) cp);
            }
            //Сохраняем на карту памяти
            MemoryWorker mw = new MemoryWorker();
            mw.writeFile(fileName,a);

            return generateArtistList(a);

        } catch (IOException e) {
            return null;
        }

    }
    //Загрузка исполнителей из памяти
    public ArtistsList loadArtistsOffline() {

            MemoryWorker mw = new MemoryWorker();
        //Получаем тот же StringBuilder что и при загрузке из интернета
            StringBuilder a = mw.readFileSD(fileName);


            return generateArtistList(a);


    }
    private ArtistsList generateArtistList(StringBuilder sb){
        //Новый билдер, чтобы преобразовать Json в объект
        Gson artistsGson = new GsonBuilder()
                .registerTypeAdapter(Artist.class, new ArtistDeserializer())
                .registerTypeAdapter(ArtistsList.class, new ArtistListDeserializer())

                .create();

        //Преобразование Json в объект
        ArtistsList res = artistsGson.fromJson(sb.toString(), ArtistsList.class);
        return res;

    }
}
