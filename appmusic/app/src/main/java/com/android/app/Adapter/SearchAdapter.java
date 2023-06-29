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
import com.android.app.Model.Song;
import com.android.app.R;
import com.android.app.ServiceAPI.APIService;
import com.android.app.ServiceAPI.DataService;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SearchAdapter extends RecyclerView.Adapter<SearchAdapter.ViewHolder>{
    Context context;
    ArrayList<Song> song_array;
    private int count = 0;

    public SearchAdapter(Context context, ArrayList<Song> song_array) {
        this.context = context;
        this.song_array = song_array;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.layout_search, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Song song = song_array.get(position);
        holder.tvTitle.setText(song.getTenBaiHat());
        holder.tvArtist.setText(song.getCasiBaiHat());
        Picasso.get().load(song.getHinhBaiHat()).into(holder.ivSong);
    }

    @Override
    public int getItemCount() {
        return song_array.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView tvTitle, tvArtist;
        ImageView ivSong, ivLove;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvTitle = itemView.findViewById(R.id.tvSearchTitle);
            tvArtist = itemView.findViewById(R.id.tvSearchArtist);
            ivLove = itemView.findViewById(R.id.ivSearchLove);
            ivSong = itemView.findViewById(R.id.ivSearch);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(context, PlayMusicActivity.class);
                    intent.putExtra("playmusic", song_array.get(getPosition()));
                    context.startActivity(intent);
                }
            });
            ivLove.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (count == 0){
                        ivLove.setImageResource(R.drawable.iconloved);
                        DataService dataService = APIService.getService();
                        Call<String> callback = dataService.UpdateLoved("1", song_array.get(getPosition()).getIdBaiHat());
                        callback.enqueue(new Callback<String>() {
                            @Override
                            public void onResponse(Call<String> call, Response<String> response) {
                                String result = response.body();
                                if (result.equals("Success")) {
                                    Toast.makeText(context, "Đã thích", Toast.LENGTH_SHORT).show();
                                } else {
                                    Toast.makeText(context, "Lỗi", Toast.LENGTH_SHORT).show();
                                }
                            }

                            @Override
                            public void onFailure(Call<String> call, Throwable t) {

                            }
                        });
                        count++;
                    } else {
                        ivLove.setImageResource(R.drawable.iconlove);
                        DataService dataService = APIService.getService();
                        Call<String> callback = dataService.DeleteLove("1", song_array.get(getPosition()).getIdBaiHat());
                        callback.enqueue(new Callback<String>() {
                            @Override
                            public void onResponse(Call<String> call, Response<String> response) {
                                String result = response.body();
                                if (result.equals("Success")) {
                                    Toast.makeText(context, "Đã bỏ thích", Toast.LENGTH_SHORT).show();
                                } else {
                                    Toast.makeText(context, "Lỗi", Toast.LENGTH_SHORT).show();
                                }
                            }

                            @Override
                            public void onFailure(Call<String> call, Throwable t) {

                            }
                        });
                        count--;
                    }
                    //ivLove.setEnabled(false);
                }
            });
        }
    }
}
