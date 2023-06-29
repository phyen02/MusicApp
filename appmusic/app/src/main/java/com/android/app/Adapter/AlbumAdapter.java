package com.android.app.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.android.app.Activity.SongListActivity;
import com.android.app.Model.Album;
import com.android.app.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class AlbumAdapter extends RecyclerView.Adapter<AlbumAdapter.ViewHolder> {
    Context context;
    ArrayList<Album> album_array;
    View view;

    public AlbumAdapter(Context context, ArrayList<Album> album_array) {
        this.context = context;
        this.album_array = album_array;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        view = layoutInflater.inflate(R.layout.layout_album,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Album album = album_array.get(position);
        holder.txtArtist.setText(album.getCasiAlbum());
        holder.txtAlbum.setText(album.getTenAlbum());
        Picasso.get().load(album.getHinhAlbum()).into(holder.imgAlbum);
    }

    @Override
    public int getItemCount() {
        return album_array.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imgAlbum;
        TextView txtAlbum, txtArtist;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imgAlbum = itemView.findViewById(R.id.imgBAlbum);
            txtAlbum = itemView.findViewById(R.id.txtTitleAlbum);
            txtArtist = itemView.findViewById(R.id.txtArtistAlbum);
            imgAlbum.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(context, SongListActivity.class);
                    intent.putExtra("itemalbum", album_array.get(getPosition()));
                    context.startActivity(intent);
                }
            });
        }
    }
}
