package com.lebartodev.artistslist.view;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.andexert.library.RippleView;
import com.lebartodev.artistslist.R;
import com.lebartodev.artistslist.model.Artist;
import com.lebartodev.artistslist.model.ArtistsList;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

/**
 * Created by Sasha on 01.04.2016.
 */
public class ArtistAdapter extends BaseAdapter {
    private Context context;
    private ArtistsList artistsList;

    public ArtistAdapter(Context context,ArtistsList artistsList) {
        this.artistsList = artistsList;
        this.context = context;
    }

    @Override
    public int getCount() {
        try {
            return artistsList.getArtists().size();
        }
        catch (NullPointerException e){
            return 0;
        }
    }

    @Override
    public Object getItem(int position) {
        return artistsList.getArtists().get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }
    @Override
    public View getView(final int position, final View convertView, ViewGroup parent) {

        LayoutInflater inflater = LayoutInflater.from(context);
        final View rowView = inflater.inflate(R.layout.item_artist, null, true);
        TextView name = (TextView) rowView.findViewById(R.id.artist_name);
        TextView genres = (TextView) rowView.findViewById(R.id.artist_genres);
        TextView info = (TextView) rowView.findViewById(R.id.artist_info);
        ImageView artistImage = (ImageView)rowView.findViewById(R.id.imageView);
        //Имя исполнителя
        name.setText(artistsList.getArtists().get(position).getName());
        try{
            Callback callBack = new Callback(){
                @Override
                public void onSuccess(){
                    //Отключение анимации загрузки
                    rowView.findViewById(R.id.avloadingIndicatorView).setVisibility(View.GONE);
                }
                @Override
                public void onError(){
                    rowView.findViewById(R.id.avloadingIndicatorView).setVisibility(View.GONE);
                }
            };
            //Анимация загрузки
            rowView.findViewById(R.id.avloadingIndicatorView).setVisibility(View.VISIBLE);
            //Изибражение исполнителя
            Picasso.with(context)
                    .load(artistsList.getArtists().get(position).getImageSmall())
                    .placeholder(R.drawable.white)
                    .fit() //
                    .centerInside()
                    .into(artistImage,callBack);

        }
        catch (IllegalArgumentException e){

        }
        //Жанры исполнителя
        String prefix="";
        StringBuilder genresStr = new StringBuilder();

        for(String genreStr:artistsList.getArtists().get(position).getGenres()){
            genresStr.append(prefix);

            prefix=", ";
            genresStr.append(genreStr);

        }
        genres.setText(genresStr.toString());
        //Информация об исполнителе
        info.setText(artistsList.getArtists().get(position).getAlbums() + " Albums, " +artistsList.getArtists().get(position).getTracks() + " Tracks");
        //Анимация при нажатии

        final RippleView rippleView = (RippleView) rowView.findViewById(R.id.more);
        //Действие при завершении анимации
        rippleView.setOnRippleCompleteListener(new RippleView.OnRippleCompleteListener() {

            @Override
            public void onComplete(RippleView rippleView) {
                Intent intent = new Intent(context, ArtistActivity.class);
                intent.putExtra("artist", (Artist) getItem(position));
                ((Activity) context).startActivity(intent);
                //Анимация вызова нового Activity
                ((Activity) context).overridePendingTransition(R.anim.slide_from_bottom,R.anim.slide_to_bottom);


            }

        });
        return rowView;

    }
}
