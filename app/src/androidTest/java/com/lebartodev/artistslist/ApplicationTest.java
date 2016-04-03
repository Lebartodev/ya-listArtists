package com.lebartodev.artistslist;

import android.app.Application;
import android.test.ApplicationTestCase;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.lebartodev.artistslist.controller.ArtistDeserializer;
import com.lebartodev.artistslist.controller.ArtistListDeserializer;
import com.lebartodev.artistslist.controller.Controller;
import com.lebartodev.artistslist.controller.MemoryWorker;
import com.lebartodev.artistslist.model.Artist;
import com.lebartodev.artistslist.model.ArtistsList;

import org.junit.Test;

/**
 * <a href="http://d.android.com/tools/testing/testing_android.html">Testing Fundamentals</a>
 */
public class ApplicationTest extends ApplicationTestCase<Application> {
    public ApplicationTest() {
        super(Application.class);
    }
    @Test
    public void controllerConnectionTest(){

        Controller controller = new Controller();
    }
    @Test
    public void artistDeserializerTest(){
        String text = "{\"id\":1080505," +
                "\"name\":\"Tove Lo\",\"genres\":[\"pop\",\"dance\",\"electronics\"]," +
                "\"tracks\":81,\"albums\":22,\"link\":\"http://www.tove-lo.com/\"," +
                "\"description\":\"С€РІРµРґСЃРєР°СЏ РїРµРІРёС†Р° Рё Р°РІС‚РѕСЂ РїРµСЃРµРЅ. РћРЅР°" +
                " РїСЂРёРІР»РµРєР»Р° Рє СЃРµР±Рµ РІРЅРёРјР°РЅРёРµ РІ 2013 РіРѕРґСѓ СЃ РІС‹РїСѓСЃРєРѕРј " +
                "СЃРёРЅРіР»Р° В«HabitsВ», РЅРѕ РЅР°СЃС‚РѕСЏС‰РµРіРѕ СѓСЃРїРµС…Р° РґРѕР±РёР»Р°СЃСЊ СЃ " +
                "СЂРµРјРёРєСЃРѕРј С…РёРї-С…РѕРї РїСЂРѕРґСЋСЃРµСЂР° Hippie Sabotage РЅР° СЌС‚Сѓ РїРµСЃРЅСЋ, " +
                "РєРѕС‚РѕСЂС‹Р№ РїРѕР»СѓС‡РёР» РЅР°Р·РІР°РЅРёРµ В«Stay HighВ». 4 РјР°СЂС‚Р° 2014 РіРѕРґР° " +
                "РІС‹С€РµР» РµС‘ РґРµР±СЋС‚РЅС‹Р№ РјРёРЅРё-Р°Р»СЊР±РѕРј Truth Serum, Р° 24 СЃРµРЅС‚СЏР±СЂСЏ " +
                "СЌС‚РѕРіРѕ Р¶Рµ РіРѕРґР° РґРµР±СЋС‚РЅС‹Р№ СЃС‚СѓРґРёР№РЅС‹Р№ Р°Р»СЊР±РѕРј Queen of the Clouds. " +
                "РўСѓРІРµ Р›Сѓ СЏРІР»СЏРµС‚СЃСЏ Р°РІС‚РѕСЂРѕРј РїРµСЃРµРЅ С‚Р°РєРёС… Р°СЂС‚РёСЃС‚РѕРІ, РєР°Рє " +
                "Icona Pop, Girls Aloud Рё РЁРµСЂ Р›Р»РѕР№Рґ.\"," +
                "\"cover\":{\"small\":\"http://avatars.yandex.net/get-music-content/dfc531f5.p.1080505/300x300\"," +
                "\"big\":\"http://avatars.yandex.net/get-music-content/dfc531f5.p.1080505/1000x1000\"}}";
        Gson artistGson = new GsonBuilder()
                .registerTypeAdapter(Artist.class, new ArtistDeserializer())
                .create();
        Artist artist = artistGson.fromJson(text, Artist.class);
        assertNotNull(artist);


    }
    @Test
    public void testLoadArtists(){
        Controller controller = new Controller();
        assertNotNull(controller.loadArtists());
    }
    @Test
    public void testLoadArtistsOffline(){
        Controller controller = new Controller();
        assertNotNull(controller.loadArtists());
    }
    @Test
    public void testWriteReadFile(){
        MemoryWorker mw = new MemoryWorker();
        StringBuilder sb = new StringBuilder("test");
        mw.writeFile("test.txt",sb);

        assertNotNull(mw.readFileSD("test.txt"));
    }


}