package com.android.app.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import com.android.app.Activity.SongListActivity;
import com.android.app.Model.Playlist;
import com.android.app.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class PlaylistfortodayAdapter extends RecyclerView.Adapter<PlaylistfortodayAdapter.ViewHolder> {
    Context context;
    ArrayList<Playlist> playlist_array;

    public PlaylistfortodayAdapter(Context context, ArrayList<Playlist> playlist_array) {
        this.context = context;
        this.playlist_array = playlist_array;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.layout_playlistfortoday,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Playlist playlist = playlist_array.get(position);
        holder.txtTitle.setText(playlist.getTenPlaylist());
        Picasso.get().load(playlist.getHinhPlaylist()).into(holder.imgbBgPlaylist);
        Picasso.get().load(playlist.getIconPlaylist()).into(holder.imgPlaylist);
    }

    @Override
    public int getItemCount() {
        return playlist_array.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView txtTitle;
        ImageView imgbBgPlaylist, imgPlaylist;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imgbBgPlaylist = itemView.findViewById(R.id.imgbBgPlaylist);
            imgPlaylist = itemView.findViewById(R.id.imgIconPlaylist);
            txtTitle = itemView.findViewById(R.id.txtTitlePlis);
            imgbBgPlaylist.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(context, SongListActivity.class);
                    intent.putExtra("itemplaylist", playlist_array.get(getAdapterPosition()));
                    context.startActivity(intent);
                }
            });
        }
    }
}
