package com.lebartodev.artistslist.view;

import android.annotation.TargetApi;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ListView;
import android.widget.Toast;

import com.github.jorgecastilloprz.FABProgressCircle;
import com.lebartodev.artistslist.R;
import com.lebartodev.artistslist.controller.Controller;
import com.lebartodev.artistslist.model.ArtistsList;
import com.melnykov.fab.FloatingActionButton;
import com.nhaarman.listviewanimations.appearance.simple.AlphaInAnimationAdapter;

public class MainActivity extends AppCompatActivity {
    private ListView listArtists;
    private FABProgressCircle fabProgressCircle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);





        listArtists = (ListView) findViewById(R.id.listArtists);

        //Анимация загрузки
        fabProgressCircle = (FABProgressCircle) findViewById(R.id.fabProgressCircle);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        try{


        toolbar.setTitle("Исполнители");
        }
        catch (NullPointerException e){
            Toast.makeText(MainActivity.this, "Something is wrong", Toast.LENGTH_SHORT).show();
        }
        setSupportActionBar(toolbar);
        initFAB();
        //Если есть кэш - выводим его, иначе загружаем из интернета
        try{
            Controller controller = new Controller();
            initListArtist(controller.loadArtistsOffline());
        }
        catch (NullPointerException e){
            GetArtists ga = new GetArtists();
            ga.execute();
        }
    }
    //Инициализация кнопки обновления
    public void initFAB(){
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        //При прокрутке вниз listArtists кнопка будет автоматически убираться
            if (fab != null) {
                fab.attachToListView(listArtists);
                fab.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        fabProgressCircle.show();
                        GetArtists ga = new GetArtists();
                        ga.execute();
                    }
                });
            }


    }
    public void complete(){
        fabProgressCircle.hide();
    }
    public void initListArtist(ArtistsList artistsList){
        ArtistAdapter adapter = new ArtistAdapter(this, artistsList);



        AlphaInAnimationAdapter swingBottomInAnimationAdapter = new AlphaInAnimationAdapter(adapter);
        swingBottomInAnimationAdapter.setAbsListView(listArtists);

        assert swingBottomInAnimationAdapter.getViewAnimator() != null;
        swingBottomInAnimationAdapter.getViewAnimator().setInitialDelayMillis(0);
        swingBottomInAnimationAdapter.getViewAnimator().setAnimationDurationMillis(200);


        try {
            //Установка адаптера для списка исполнителей
            listArtists.setAdapter(swingBottomInAnimationAdapter);
        }
        catch (NullPointerException e){
            Toast.makeText(MainActivity.this, "Something is wrong", Toast.LENGTH_SHORT).show();
        }


    }
    //Класс получения исполнителей
    class GetArtists extends AsyncTask<Void, Void, ArtistsList> {



        @Override
        protected ArtistsList doInBackground(Void... params) {
            Controller controller = new Controller();
            //Загрузка исполнителей
            return controller.loadArtists();
        }

        @Override
        protected void onPostExecute(ArtistsList artistsList) {
            super.onPostExecute(artistsList);
            initListArtist(artistsList);

            complete();
        }
    }

}
