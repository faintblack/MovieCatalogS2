package com.system.perfect.moviecatalogs2.allmovies;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.system.perfect.moviecatalogs2.R;

public class AllMoviesFragment extends Fragment {
    private final String TAG = "AllMoviesFragment";

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment3, container, false);
        return v;
    }
}
