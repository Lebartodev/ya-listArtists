package com.lebartodev.artistslist.view;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.lebartodev.artistslist.R;
import com.lebartodev.artistslist.model.Artist;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

public class ArtistActivity extends AppCompatActivity {
    private Artist artist;
    private ImageView artist_image;
    private TextView artist_genres,artist_info,artist_bio;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_artist);
        //Сериализуемый объект исполнителя
        artist = (Artist) getIntent().getExtras().get("artist");
        artist_image = (ImageView)findViewById(R.id.artistimage);




        drawImage();
        initTextViews();
        initActionBar();




    }
    private void startAnim(){
        //Анимация загрузки
        try {
            findViewById(R.id.avloadingIndicatorView).setVisibility(View.VISIBLE);
        }
        catch (NullPointerException e){
            Toast.makeText(ArtistActivity.this, "Something is wrong", Toast.LENGTH_SHORT).show();
        }
    }

    private void stopAnim(){
        try {
            //Анимация загрузки
            findViewById(R.id.avloadingIndicatorView).setVisibility(View.GONE);
        }
        catch (NullPointerException e){
            Toast.makeText(ArtistActivity.this, "Something is wrong", Toast.LENGTH_SHORT).show();
        }
    }
    private void initActionBar(){
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_artist);


        setSupportActionBar(toolbar);
        try {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setTitle(artist.getName());
            toolbar.setNavigationOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    ArtistActivity.this.finish();
                    //Анимация перехода в другую Activity
                    overridePendingTransition(R.anim.slide_from_bottom, R.anim.slide_to_bottom);
                }
            });
        }
        catch (NullPointerException e){
            Toast.makeText(ArtistActivity.this, "Something is wrong", Toast.LENGTH_SHORT).show();
        }
    }
    private void initTextViews(){
        //Информация, биография, жанры
        artist_info=(TextView)findViewById(R.id.artist_info);
        artist_genres=(TextView)findViewById(R.id.artist_genres);
        artist_bio=(TextView)findViewById(R.id.artist_bio);
        artist_info.setText(artist.getTracks()+" Tracks, "+artist.getAlbums()+" Albums");
        artist_bio.setText(artist.getDescription());
        String prefix="";
        StringBuilder genresStr = new StringBuilder();

        for(String genreStr:artist.getGenres()){
            genresStr.append(prefix);

            prefix=", ";
            genresStr.append(genreStr);

        }
        artist_genres.setText(genresStr.toString());

    }
    private void drawImage(){
        //Изображение исполнителя
        startAnim();
        Callback callBack = new Callback(){
            @Override
            public void onSuccess(){
                stopAnim();
            }
            @Override
            public void onError(){
                stopAnim();
            }
        };
        Picasso.with(this)
                .load(artist.getImageBig())
                .placeholder(R.drawable.white)
                .fit() //
                .centerInside()
                .into(artist_image,callBack);



    }



}
