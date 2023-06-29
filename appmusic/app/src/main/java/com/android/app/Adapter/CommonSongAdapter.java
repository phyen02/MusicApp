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
import com.android.app.Model.Common;
import com.android.app.Model.Song;
import com.android.app.R;
import com.android.app.ServiceAPI.APIService;
import com.android.app.ServiceAPI.DataService;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CommonSongAdapter extends RecyclerView.Adapter<CommonSongAdapter.ViewHolder>{
    Context context;
    ArrayList<Song> commonSong_array;
    private int count = 0;

    public CommonSongAdapter(Context context, ArrayList<Song> commonSong_array) {
        this.context = context;
        this.commonSong_array = commonSong_array;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.layout_common_song,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Song common = commonSong_array.get(position);
        holder.txtTitle.setText(common.getTenBaiHat());
        holder.txtArtist.setText(common.getCasiBaiHat());
        Picasso.get().load(common.getHinhBaiHat()).into(holder.imgThumb);
    }

    @Override
    public int getItemCount() {
        return commonSong_array.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView txtTitle, txtArtist;
        ImageView imgThumb, imgLove;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txtTitle = itemView.findViewById(R.id.txtTitleCommonSong);
            txtArtist = itemView.findViewById(R.id.txtArtistCommonSong);
            imgThumb = itemView.findViewById(R.id.imgCommonSong);
            imgLove = itemView.findViewById(R.id.imgFavoriteCommonSong);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(context, PlayMusicActivity.class);
                    intent.putExtra("playmusic", commonSong_array.get(getAdapterPosition()));
                    context.startActivity(intent);
                }
            });
            imgLove.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (count == 0){
                        imgLove.setImageResource(R.drawable.iconloved);
                        DataService dataService = APIService.getService();
                        Call<String> callback = dataService.UpdateLoved("1", commonSong_array.get(getPosition()).getIdBaiHat());
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
                        imgLove.setImageResource(R.drawable.iconlove);
                        DataService dataService = APIService.getService();
                        Call<String> callback = dataService.DeleteLove("1", commonSong_array.get(getPosition()).getIdBaiHat());
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
                }
            });

        }
    }
}
