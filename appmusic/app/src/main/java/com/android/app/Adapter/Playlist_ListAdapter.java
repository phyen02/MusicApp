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
import com.android.app.Model.Playlist;
import com.android.app.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class Playlist_ListAdapter extends RecyclerView.Adapter<Playlist_ListAdapter.ViewHolder>{
    Context context;
    ArrayList<Playlist> playlist_array;

    public Playlist_ListAdapter(Context context, ArrayList<Playlist> playlist_array) {
        this.context = context;
        this.playlist_array = playlist_array;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.layout_playlist_list,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Playlist playlist = playlist_array.get(position);
        Picasso.get().load(playlist.getIconPlaylist()).into(holder.ivImage);
        holder.tvTitle.setText(playlist.getTenPlaylist());
    }

    @Override
    public int getItemCount() {
        return playlist_array.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView ivImage;
        TextView tvTitle;
        public ViewHolder(View itemView){
            super(itemView);
            ivImage = itemView.findViewById(R.id.ivPlaylist_List);
            tvTitle = itemView.findViewById(R.id.tvPlaylist_ListTitle);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(context, SongListActivity.class);
                    intent.putExtra("itemplaylist", playlist_array.get(getPosition()));
                    context.startActivity(intent);
                }
            });
        }
    }
}
