package com.android.app.Adapter;

import android.content.Context;
import android.content.Intent;
import android.media.Image;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.android.app.Activity.SongListActivity;
import com.android.app.Model.Genre;
import com.android.app.R;
import com.squareup.picasso.Picasso;

import java.io.Serializable;
import java.util.ArrayList;

public class GenreAdapter extends RecyclerView.Adapter<GenreAdapter.ViewHolder> {
    Context context;
    ArrayList<Genre> genre_array;
    View view;

    public GenreAdapter(Context context, ArrayList<Genre> genre_array) {
        this.context = context;
        this.genre_array = genre_array;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        view = inflater.inflate(R.layout.layout_genre,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Genre genre = genre_array.get(position);
        holder.txtGenre.setText(genre.getTenTheLoai());
        Picasso.get().load(genre.getHinhTheLoai()).into(holder.imgGenre);
    }

    @Override
    public int getItemCount() {
        return genre_array.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        ImageView imgGenre;
        TextView txtGenre;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imgGenre = itemView.findViewById(R.id.imgBGenre);
            txtGenre = itemView.findViewById(R.id.txtGenre);
            imgGenre.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(context, SongListActivity.class);
                    intent.putExtra("itemgenre", genre_array.get(getPosition()));
                    context.startActivity(intent);
                }
            });
        }
    }
}
