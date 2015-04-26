package com.example.steven.app1;


import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


/**
 * A simple {@link Fragment} subclass.
 */
public class Myfragment4 extends Fragment {
    TextView textdetail;


    public Myfragment4() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_myfragment4, container, false);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        this.textdetail=(TextView)getActivity().findViewById(R.id.textdetail);
    }

    public void datachange(int i)
    {
        String[] temp=getResources().getStringArray(R.array.description);
        this.textdetail.setText(temp[i]);
    }
}
