package com.android.app.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.app.Activity.PlaylistListActivity;
import com.android.app.Activity.SongListActivity;
import com.android.app.Adapter.PlaylistfortodayAdapter;
import com.android.app.Model.Playlist;
import com.android.app.R;
import com.android.app.ServiceAPI.DataService;
import com.android.app.ServiceAPI.APIService;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Fragment_PlaylistForToday extends Fragment{
    View view;
    PlaylistfortodayAdapter adapter;
    RecyclerView recyclerView;
    TextView txtTitle, txtViewmore;
    ArrayList<Playlist> plist_array;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_playlistfortoday,container,false);
        recyclerView = view.findViewById(R.id.recyclerviewplaylist);
        txtTitle = view.findViewById(R.id.txtTitlePlistfortoday);
        txtViewmore = view.findViewById(R.id.txtViewmorePlaylist);
        getData();
        txtViewmore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), PlaylistListActivity.class);
                startActivity(intent);
            }
        });
        return view;
    }

    private void getData() {
        DataService dataService = APIService.getService();
        Call<List<Playlist>> callback = dataService.GetPlaylistCurrentDay();
        callback.enqueue(new Callback<List<Playlist>>() {
            @Override
            public void onResponse(Call<List<Playlist>> call, Response<List<Playlist>> response) {
                plist_array = (ArrayList<Playlist>) response.body();
                adapter = new PlaylistfortodayAdapter(getActivity(),plist_array);
                LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
                linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
                recyclerView.setLayoutManager(linearLayoutManager);
                recyclerView.setAdapter(adapter);
            }

            @Override
            public void onFailure(Call<List<Playlist>> call, Throwable t) {

            }
        });
    }
}
