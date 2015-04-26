package com.example.steven.app1;


import android.os.Bundle;
import android.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;


/**
 * A simple {@link Fragment} subclass.
 */
public class Myfragment3 extends Fragment implements  AdapterView.OnItemClickListener {


    ListView list;
    communicator comm;
    public Myfragment3() {
        // Required empty public constructor
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        list=(ListView)getActivity().findViewById(R.id.listView2);
        ArrayAdapter adapter=ArrayAdapter.createFromResource(getActivity(), R.array.title, android.R.layout.simple_list_item_1);
        list.setAdapter(adapter);
        list.setOnItemClickListener(this);
        comm=(communicator)getActivity();

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        return inflater.inflate(R.layout.fragment_myfragment3, container, false);
    }


    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        comm.respond(String.valueOf(position));

    }


}
