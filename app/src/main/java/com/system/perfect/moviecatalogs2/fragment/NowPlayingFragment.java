package com.system.perfect.moviecatalogs2.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;

import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.system.perfect.moviecatalogs2.R;
import com.system.perfect.moviecatalogs2.adapter.NowPlayingAdapter;

public class NowPlayingFragment extends Fragment {
    private final String TAG = NowPlayingFragment.class.getName();
    public NowPlayingFragment(){}

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment1, container, false);

        RecyclerView recyclerView = v.findViewById(R.id.rv_now_playing);
        recyclerView.setLayoutManager(new GridLayoutManager(getActivity(),2));

        NowPlayingAdapter adapter = new NowPlayingAdapter(getActivity());
        recyclerView.setAdapter(adapter);
        return v;
    }
}
