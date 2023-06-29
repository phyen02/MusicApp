package com.android.app.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.app.Adapter.SongListAdapter;
import com.android.app.Model.Album;
import com.android.app.Model.Genre;
import com.android.app.Model.Playlist;
import com.android.app.Model.Song;
import com.android.app.R;
import com.android.app.ServiceAPI.DataService;
import com.android.app.ServiceAPI.APIService;
import com.google.android.material.appbar.CollapsingToolbarLayout;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.squareup.picasso.Picasso;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SongListActivity extends AppCompatActivity {
    CoordinatorLayout coordinatorLayout;
    CollapsingToolbarLayout collapsingToolbarLayout;
    Toolbar toolbar;
    TextView tvCollapsing;
    RecyclerView recyclerViewSongList;
    FloatingActionButton floatingActionButton;
    ImageView ivSongList;
    ArrayList<Song> song_array;
    SongListAdapter SongListAdapter;
    Playlist playlist;
    Genre genre;
    Album album;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_song_list);
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        DataIntent();
        anhxa();
        init();

        if(playlist != null && !playlist.getTenPlaylist().equals("")){
            setValueInView(playlist.getTenPlaylist(), playlist.getIconPlaylist());
            GetDataPlaylist(playlist.getIdPlaylist());
        }

        if(genre != null && !genre.getTenTheLoai().equals("")){
            setValueInView(genre.getTenTheLoai(), genre.getHinhTheLoai());
            GetDataGenre(genre.getIdTheLoai());
        }

        if(album != null && !album.getTenAlbum().equals("")){
            setValueInView(album.getTenAlbum(), album.getHinhAlbum());
            GetDataAlbum(album.getIdAlbum());
        }
    }

    private void setValueInView(String ten, String hinhPlaylist) {
        Picasso.get().load(hinhPlaylist).into(ivSongList);
        tvCollapsing.setText(ten);
        getSupportActionBar().setTitle(ten);
    }

    private void GetDataPlaylist(String idPlaylist) {
        DataService dataService = APIService.getService();
        Call<List<Song>> callback = dataService.GetSongPlaylistList(idPlaylist);
        callback.enqueue(new Callback<List<Song>>() {
            @Override
            public void onResponse(Call<List<Song>> call, Response<List<Song>> response) {
                song_array = (ArrayList<Song>) response.body();
                SongListAdapter = new SongListAdapter(SongListActivity.this, song_array);
                recyclerViewSongList.setLayoutManager(new LinearLayoutManager(SongListActivity.this));
                recyclerViewSongList.setAdapter(SongListAdapter);
                eventClick();
            }

            @Override
            public void onFailure(Call<List<Song>> call, Throwable t) {

            }
        });
    }

    private void GetDataGenre(String idGenre) {
        DataService dataService = APIService.getService();
        Call<List<Song>> callback = dataService.GetSongGenreList(idGenre);
        callback.enqueue(new Callback<List<Song>>() {
            @Override
            public void onResponse(Call<List<Song>> call, Response<List<Song>> response) {
                song_array = (ArrayList<Song>) response.body();
                SongListAdapter = new SongListAdapter(SongListActivity.this, song_array);
                recyclerViewSongList.setLayoutManager(new LinearLayoutManager(SongListActivity.this));
                recyclerViewSongList.setAdapter(SongListAdapter);
                eventClick();
            }

            @Override
            public void onFailure(Call<List<Song>> call, Throwable t) {

            }
        });
    }

    private void GetDataAlbum(String idAlbum) {
        DataService dataService = APIService.getService();
        Call<List<Song>> callback = dataService.GetSongAlbumList(idAlbum);
        callback.enqueue(new Callback<List<Song>>() {
            @Override
            public void onResponse(Call<List<Song>> call, Response<List<Song>> response) {
                song_array = (ArrayList<Song>) response.body();
                SongListAdapter = new SongListAdapter(SongListActivity.this, song_array);
                recyclerViewSongList.setLayoutManager(new LinearLayoutManager(SongListActivity.this));
                recyclerViewSongList.setAdapter(SongListAdapter);
                eventClick();
            }

            @Override
            public void onFailure(Call<List<Song>> call, Throwable t) {

            }
        });
    }

    private void DataIntent() {
        Intent intent = getIntent();
        if (intent != null){
            if(intent.hasExtra("itemplaylist")){
                playlist = (Playlist) intent.getSerializableExtra("itemplaylist");
            }
            if(intent.hasExtra("itemgenre")){
                genre = (Genre) intent.getSerializableExtra("itemgenre");
            }
            if(intent.hasExtra("itemalbum")){
                album = (Album) intent.getSerializableExtra("itemalbum");
            }
        }
    }
    private void eventClick(){
        floatingActionButton.setEnabled(true);
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (song_array != null){
                    if (song_array.size() > 0){
                        Intent intent = new Intent(SongListActivity.this, PlayMusicActivity.class);
                        intent.putExtra("songlist",song_array);
                        startActivity(intent);
                    }else {
                        Toast.makeText(SongListActivity.this, "Danh sách không có bài hát nào", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }

    public void init(){
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                finish();
            }
        });
        collapsingToolbarLayout.setExpandedTitleColor(Color.WHITE);
        collapsingToolbarLayout.setCollapsedTitleTextColor(Color.WHITE);
        floatingActionButton.setEnabled(true);
    }

    private void anhxa() {
        coordinatorLayout = findViewById(R.id.coordSongList);
        collapsingToolbarLayout = findViewById(R.id.collapsingToolBar);
        toolbar = findViewById(R.id.toolbarSongList);
        recyclerViewSongList = findViewById(R.id.recyclerviewSongList);
        floatingActionButton = findViewById(R.id.floatingAction);
        ivSongList = findViewById(R.id.ivSongList);
        tvCollapsing = findViewById(R.id.textViewcollapsing);
    }
}