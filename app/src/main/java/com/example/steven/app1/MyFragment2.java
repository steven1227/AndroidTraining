package com.example.steven.app1;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by steven on 23-4-15.
 */
public class MyFragment2 extends Fragment {

    public MyFragment2() {
        Log.v(getClass().getSimpleName(),"MyFragment2 Constrcut");
        // Required empty public constructor
    }
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Log.v(getClass().getSimpleName(), "On CreateView2");
        return inflater.inflate(R.layout.my_fragment2,container,false);
    }
}
