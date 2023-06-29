package com.android.app.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.graphics.Color;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.StrictMode;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import com.android.app.Adapter.ViewPagerPlaylistAdapter;
import com.android.app.Fragment.Fragment_Dia_Nhac;
import com.android.app.Fragment.Fragment_Play_All;
import com.android.app.Model.Song;
import com.android.app.R;
import com.android.app.ServiceAPI.APIService;
import com.android.app.ServiceAPI.DataService;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Objects;
import java.util.Random;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PlayMusicActivity extends AppCompatActivity {
    private Toolbar toolbarPlay;
    private TextView tvTime, tvTimeTotal;
    private SeekBar seekBarTime;
    private ImageButton ibShuffle, ibPlay, ibPrevious, ibNext, ibRepeat;
    ViewPager viewPager;
    public static ArrayList<Song> song_array = new ArrayList<>();
    public static ViewPagerPlaylistAdapter adapterViewPager;
    Fragment_Dia_Nhac fragment_dia_nhac;
    Fragment_Play_All fragment_play_all;
    MediaPlayer mediaPlayer;
    private int position = 0, count = 0;
    boolean repeat = false, shuffle = false, next = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play_music);
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        getDataIntent();
        init();
        eventClick();
    }

    private void eventClick() {
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                if (adapterViewPager.getItem(1) != null){
                    if (song_array.size() > 0){
                        fragment_dia_nhac.PlayMusic(song_array.get(0).getHinhBaiHat());
                        handler.removeCallbacks(this);
                    } else {
                        handler.postDelayed(this, 300);
                    }
                }
            }
        }, 500);

        // Play button
        ibPlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mediaPlayer.isPlaying()){
                    mediaPlayer.pause();
                    ibPlay.setImageResource(R.drawable.iconplay);
                } else {
                    mediaPlayer.start();
                    ibPlay.setImageResource(R.drawable.iconpause);
                }
            }
        });

        // Repeat button
        ibRepeat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (repeat == false){
                    if (shuffle == true){
                        shuffle = false;
                        ibRepeat.setImageResource(R.drawable.iconsyned);
                        ibShuffle.setImageResource(R.drawable.iconshuffle);
                    }
                    ibRepeat.setImageResource(R.drawable.iconsyned);
                    repeat = true;
                } else {
                    ibRepeat.setImageResource(R.drawable.iconrepeat);
                    repeat = false;
                }
            }
        });

        // Shuffle button
        ibShuffle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (shuffle == false){
                    if (repeat == true){
                        repeat = false;
                        ibRepeat.setImageResource(R.drawable.iconrepeat);
                        ibShuffle.setImageResource(R.drawable.iconshuffled);
                    }
                    ibShuffle.setImageResource(R.drawable.iconshuffled);
                    shuffle = true;
                } else {
                    ibShuffle.setImageResource(R.drawable.iconshuffle);
                    shuffle = false;
                }
            }
        });

        // Customize seekbar as a duration of song
        seekBarTime.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                mediaPlayer.seekTo(seekBar.getProgress());
            }
        });

        // Button next song
        ibNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (song_array.size() > 0){
                    if (mediaPlayer.isPlaying() || mediaPlayer != null){
                        mediaPlayer.stop();
                        mediaPlayer.release();
                        mediaPlayer = null;
                    }
                    if (position < (song_array.size())){
                        ibPlay.setImageResource(R.drawable.iconpause);
                        position++;
                        if (repeat == true){
                            if (position == 0){
                                position = song_array.size();
                            }
                            position -= 1;
                        }
                        if (shuffle == true){
                            Random random = new Random();
                            int index = random.nextInt(song_array.size());
                            if (index == position){
                                position = index - 1;
                            }
                            position = index;
                        }
                        if (position > (song_array.size() - 1)){
                            position = 0;
                        }
                        new PlayMusic().execute(song_array.get(position).getLinkBaiHat());
                        fragment_dia_nhac.PlayMusic(song_array.get(position).getHinhBaiHat());
                        getSupportActionBar().setTitle(song_array.get(position).getTenBaiHat());
                        UpdateTime();
                    }
                }
                ibPrevious.setClickable(false);
                ibNext.setClickable(false);
                Handler handler1 = new Handler();
                handler1.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        ibPrevious.setClickable(true);
                        ibNext.setClickable(true);
                    }
                }, 5000);
            }
        });

        // Button previous song
        ibPrevious.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (song_array.size() > 0){
                    if (mediaPlayer.isPlaying() || mediaPlayer != null){
                        mediaPlayer.stop();
                        mediaPlayer.release();
                        mediaPlayer = null;
                    }
                    if (position < (song_array.size())){
                        ibPlay.setImageResource(R.drawable.iconpause);
                        position--;
                        if (position < 0){
                            position = song_array.size() - 1;
                        }
                        if (repeat == true){
                            position += 1;
                        }
                        if (shuffle == true){
                            Random random = new Random();
                            int index = random.nextInt(song_array.size());
                            if (index == position){
                                position = index - 1;
                            }
                            position = index;
                        }
                        new PlayMusic().execute(song_array.get(position).getLinkBaiHat());
                        fragment_dia_nhac.PlayMusic(song_array.get(position).getHinhBaiHat());
                        getSupportActionBar().setTitle(song_array.get(position).getTenBaiHat());
                        UpdateTime();
                    }
                }
                ibPrevious.setClickable(false);
                ibNext.setClickable(false);
                Handler handler1 = new Handler();
                handler1.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        ibPrevious.setClickable(true);
                        ibNext.setClickable(true);
                    }
                }, 5000);
            }
        });
    }

    private void getDataIntent() {
        Intent intent = getIntent();
        song_array.clear();
        if(intent != null){
            if (intent.hasExtra("playmusic")){
                Song song = intent.getParcelableExtra("playmusic");
                song_array.add(song);
            }
            if (intent.hasExtra("songlist")){
                ArrayList<Song> songlist_array = intent.getParcelableArrayListExtra("songlist");
                song_array = songlist_array;
            }
        }
    }

    private void init(){
        toolbarPlay = findViewById(R.id.toolBarPlayMusic);
        tvTime = findViewById(R.id.tvSongTime);
        tvTimeTotal = findViewById(R.id.tvSongTotalTime);
        seekBarTime = findViewById(R.id.seekBarSong);
        ibNext = findViewById(R.id.ibNextSong);
        ibPlay = findViewById(R.id.ibPlaySong);
        ibPrevious = findViewById(R.id.ibPrevious);
        ibRepeat = findViewById(R.id.ibRepeatSong);
        ibShuffle = findViewById(R.id.ibShuffle);
        viewPager = findViewById(R.id.viewPagerPlayMusic);
        setSupportActionBar(toolbarPlay);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbarPlay.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                mediaPlayer.stop();
                song_array.clear();
            }
        });
        toolbarPlay.setTitleTextColor(Color.BLACK);

        adapterViewPager = new ViewPagerPlaylistAdapter(getSupportFragmentManager());
        fragment_dia_nhac = new Fragment_Dia_Nhac();
        fragment_play_all = new Fragment_Play_All();
        adapterViewPager.addFragment(fragment_dia_nhac);
        adapterViewPager.addFragment(fragment_play_all);
        viewPager.setAdapter(adapterViewPager);

        fragment_dia_nhac = (Fragment_Dia_Nhac) adapterViewPager.getItem(0);

        if (song_array.size() > 0){
            getSupportActionBar().setTitle(song_array.get(0).getTenBaiHat() + " - " + song_array.get(0).getCasiBaiHat());
            new PlayMusic().execute(song_array.get(0).getLinkBaiHat());
            ibPlay.setImageResource(R.drawable.iconpause);
        }
    }

    class PlayMusic extends AsyncTask<String, Void, String>{

        @Override
        protected String doInBackground(String... strings) {
            return strings[0];
        }

        @Override
        protected void onPostExecute(String song) {
            super.onPostExecute(song);
            try {
                mediaPlayer = new MediaPlayer();
                mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
                mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                    @Override
                    public void onCompletion(MediaPlayer mp) {
                        mediaPlayer.stop();
                        mediaPlayer.reset();
                    }
                });
                mediaPlayer.setDataSource(song);
                mediaPlayer.prepare();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            mediaPlayer.start();
            TimeSong();
            UpdateTime();
        }
    }

    private void TimeSong() {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("mm:ss");
        tvTimeTotal.setText(simpleDateFormat.format(mediaPlayer.getDuration()));
        seekBarTime.setMax(mediaPlayer.getDuration());
    }

    private void UpdateTime(){
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                if (mediaPlayer != null){
                    seekBarTime.setProgress(mediaPlayer.getCurrentPosition());
                    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("mm:ss");
                    tvTime.setText(simpleDateFormat.format(mediaPlayer.getCurrentPosition()));
                    handler.postDelayed(this, 300);
                    mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                        @Override
                        public void onCompletion(MediaPlayer mp) {
                            next = true;
                            try {
                                Thread.sleep(1000);
                            } catch (InterruptedException e) {
                                throw new RuntimeException(e);
                            }
                        }
                    });
                }
            }
        },300);
        Handler handler1 = new Handler();
        handler1.postDelayed(new Runnable() {
            @Override
            public void run() {
                if (next == true){
                    if (position < (song_array.size())){
                        ibPlay.setImageResource(R.drawable.iconpause);
                        position++;
                        if (repeat == true){
                            if (position == 0){
                                position = song_array.size();
                            }
                            position -= 1;
                        }
                        if (shuffle == true){
                            Random random = new Random();
                            int index = random.nextInt(song_array.size());
                            if (index == position){
                                position = index - 1;
                            }
                            position = index;
                        }
                        if (position > (song_array.size() - 1)){
                            position = 0;
                        }
                        new PlayMusic().execute(song_array.get(position).getLinkBaiHat());
                        fragment_dia_nhac.PlayMusic(song_array.get(position).getHinhBaiHat());
                        getSupportActionBar().setTitle(song_array.get(position).getTenBaiHat());
                    }
                    ibPrevious.setClickable(false);
                    ibNext.setClickable(false);
                    Handler handler1 = new Handler();
                    handler1.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            ibPrevious.setClickable(true);
                            ibNext.setClickable(true);
                        }
                    }, 5000);
                    next = false;
                    handler1.removeCallbacks(this);
                } else {
                    handler1.postDelayed(this,1000);
                }
            }
        },1000);
    }
}