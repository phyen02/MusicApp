package com.android.app.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.app.Activity.GenreListActivity;
import com.android.app.Activity.SongListActivity;
import com.android.app.Adapter.GenreAdapter;
import com.android.app.Model.Genre;
import com.android.app.R;
import com.android.app.ServiceAPI.DataService;
import com.android.app.ServiceAPI.APIService;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Fragment_Genre extends Fragment {
    View view;
    RecyclerView recyclerViewGenre;
    TextView txtTitleGenre, txtViewMoreGenre;
    GenreAdapter genreAdapter;
    ArrayList<Genre> genre_array;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_genre,container,false);
        recyclerViewGenre = view.findViewById(R.id.recycleView_Genre);
        txtTitleGenre = view.findViewById(R.id.txtTitleGenre);
        txtViewMoreGenre = view.findViewById(R.id.txtViewMoreGenre);
        getData();
        txtViewMoreGenre.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), GenreListActivity.class);
                startActivity(intent);
            }
        });
        return view;
    }

    private void getData() {
        DataService dataService = APIService.getService();
        Call<List<Genre>> callback = dataService.GetGenreCurrentDay();
        callback.enqueue(new Callback<List<Genre>>() {
            @Override
            public void onResponse(Call<List<Genre>> call, Response<List<Genre>> response) {
                genre_array = (ArrayList<Genre>) response.body();
                genreAdapter = new GenreAdapter(getActivity(),genre_array);
                LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
                linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
                recyclerViewGenre.setLayoutManager(linearLayoutManager);
                recyclerViewGenre.setAdapter(genreAdapter);
            }

            @Override
            public void onFailure(Call<List<Genre>> call, Throwable t) {

            }
        });
    }
}
