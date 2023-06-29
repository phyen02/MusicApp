package com.android.app.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.android.app.Activity.PlayMusicActivity;
import com.android.app.Model.Artist;
import com.android.app.Model.Song;
import com.android.app.R;
import com.android.app.ServiceAPI.APIService;
import com.android.app.ServiceAPI.DataService;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ArtistAdapter extends RecyclerView.Adapter<ArtistAdapter.ViewHolder>{
    Context context;
    ArrayList<Artist> artist_array;
    View view;

    public ArtistAdapter(Context context, ArrayList<Artist> artist_array) {
        this.context = context;
        this.artist_array = artist_array;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        view = layoutInflater.inflate(R.layout.layout_artist,parent,false);
        return new ArtistAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Artist artist = artist_array.get(position);
        holder.txtArtist.setText(artist.getTenCaSi());
        Picasso.get().load(artist.getHinhCaSi()).into(holder.imgThumb);
    }

    @Override
    public int getItemCount() {
        return artist_array.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView txtArtist;
        ImageView imgThumb;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txtArtist = itemView.findViewById(R.id.tvArtist);
            imgThumb = itemView.findViewById(R.id.imageviewnghesi);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(context, PlayMusicActivity.class);
                    intent.putExtra("songlist", artist_array.get(getAdapterPosition()));
                    context.startActivity(intent);
                }
            });
        }
    }
}