package com.example.steven.app1;


import android.os.Bundle;
import android.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;


/**
 * A simple {@link Fragment} subclass.
 */
public class MyFragment extends Fragment implements View.OnClickListener{

    private Button clickme;
    private int counter;
    private  communicator comm;

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


    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        if(savedInstanceState==null)
        {
            counter=0;
        }
        else
        {
            counter=savedInstanceState.getInt("counter");
        }

        comm=(communicator)getActivity();
        this.clickme=(Button)getActivity().findViewById(R.id.buttonf);
        clickme.setOnClickListener(this);
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt("counter",counter);

    }

    @Override
    public void onClick(View v) {

        counter++;
        comm.respond("The button clicked:"+counter);

    }


}
