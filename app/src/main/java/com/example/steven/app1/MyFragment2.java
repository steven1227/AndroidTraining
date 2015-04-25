package com.example.steven.app1;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

/**
 * Created by steven on 23-4-15.
 */
public class MyFragment2 extends Fragment {

    private TextView clickme;


    public MyFragment2() {
        Log.v(getClass().getSimpleName(),"MyFragment2 Constrcut");
        // Required empty public constructor
    }
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Log.v(getClass().getSimpleName(), "On CreateView2");
        View view=inflater.inflate(R.layout.my_fragment2,container,false);
//        this.clickme=(TextView)view.findViewById(R.id.hello2);
        if(savedInstanceState==null)
        {

        }else
        {

            ((TextView)view.findViewById(R.id.hello2)).setText(savedInstanceState.getString("show"));
        }
        return view;
    }


    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        this.clickme=(TextView)getActivity().findViewById(R.id.hello2);
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString("show", String.valueOf(clickme.getText()));
    }

    public void changedData (String data){

        clickme.setText(data);

    }
}
