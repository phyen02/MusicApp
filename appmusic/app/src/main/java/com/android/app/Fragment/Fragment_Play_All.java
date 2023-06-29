package com.android.app.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.app.Activity.PlayMusicActivity;
import com.android.app.Adapter.PlayMusicAdapter;
import com.android.app.Model.Song;
import com.android.app.R;

import java.util.ArrayList;

public class Fragment_Play_All extends Fragment {
    View view;
    RecyclerView recyclerViewPlayAll;
    PlayMusicAdapter adapter;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_play_all, container, false);
        recyclerViewPlayAll = view.findViewById(R.id.recycerViewPlayAll);
        if (PlayMusicActivity.song_array.size() > 0){
            adapter = new PlayMusicAdapter(getActivity(), PlayMusicActivity.song_array);
            recyclerViewPlayAll.setLayoutManager(new LinearLayoutManager(getActivity()));
            recyclerViewPlayAll.setAdapter(adapter);
        }
        return view;
    }
}
