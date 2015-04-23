package com.example.steven.app1;


import android.os.Bundle;
import android.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


/**
 * A simple {@link Fragment} subclass.
 */
public class MyFragment extends Fragment {


    public MyFragment() {
        Log.v(getClass().getSimpleName(),"MyFragment Constrcut");
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        Log.v(getClass().getSimpleName(),"On CreateView");
        return inflater.inflate(R.layout.my_fragment,container,false);

    }


}
