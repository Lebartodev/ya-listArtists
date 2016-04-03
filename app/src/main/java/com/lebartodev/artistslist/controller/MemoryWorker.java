package com.lebartodev.artistslist.controller;

import android.os.Environment;
import android.util.Log;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

/**
 * Created by Sasha on 03.04.2016.
 */
public class MemoryWorker {
    private final String DIR_SD = "ArtistList";
    public StringBuilder readFileSD(String fileName) {
        //Если память не доступна, кэширования не будет
        if (!Environment.getExternalStorageState().equals(
                Environment.MEDIA_MOUNTED)) {
            Log.d("Getter", "SD-карта не доступна: " + Environment.getExternalStorageState());


            return null;
        }

        File sdPath = Environment.getExternalStorageDirectory();

        sdPath = new File(sdPath.getAbsolutePath() + "/" + DIR_SD);
        //Получаем файл в папке /ArtistList/
        //Если файла не существует, возвращаем null.
        File sdFile = new File(sdPath, fileName);
        if(!sdFile.exists()){
            try {
                sdFile.createNewFile();
                return null;
            } catch (IOException e) {
                return null;
            }

        }
        try {

            BufferedReader br = new BufferedReader(new FileReader(sdFile));
            String str = "";
            StringBuilder a = new StringBuilder();

            while ((str = br.readLine()) != null) {
                a.append(str);

            }
            //Просто фозвращаем содержимое файла
            return a;
        } catch (FileNotFoundException e) {
            return null;
        } catch (IOException e) {
            return null;
        }

    }
    public void writeFile(String fileName, StringBuilder content) {
        //Если память не доступна, кэширования не будет
        if (!Environment.getExternalStorageState().equals(
                Environment.MEDIA_MOUNTED)) {
            Log.d("Getter", "SD-карта не доступна: " + Environment.getExternalStorageState());
            return;
        }

        File sdPath = Environment.getExternalStorageDirectory();

        sdPath = new File(sdPath.getAbsolutePath() + "/" + DIR_SD);

        if(!sdPath.exists())
            sdPath.mkdirs();

        File sdFile = new File(sdPath, fileName);

        if(!sdFile.exists()){
            try {
                sdFile.createNewFile();
            } catch (IOException e) {
                return;
            }

        }
        try {

            BufferedWriter bw = new BufferedWriter(new FileWriter(sdFile));
        //Записываем в файл content
            bw.write(content.toString());

            bw.close();
        } catch (IOException e) {
            return;
        }
    }
}
