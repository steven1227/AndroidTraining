package com.example.steven.app1;

import android.app.Activity;
import android.app.Dialog;
import android.app.DialogFragment;
import android.os.Bundle;
import android.app.ListFragment;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;




public class MyListfragment extends DialogFragment implements View.OnClickListener {

    Button yes,no;
    TextView texttry;
    comm com1;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }


    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        com1=(comm)activity;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        texttry=(TextView)getActivity().findViewById(R.id.texttry);
//        com1=(comm)getActivity();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        getDialog().setTitle("My Dialog Title");
        View view=inflater.inflate(R.layout.my_dialog, null);
        no=(Button)view.findViewById(R.id.button3);
        yes=(Button)view.findViewById(R.id.button4);
        no.setOnClickListener(this);
        yes.setOnClickListener(this);
        setCancelable(false);
        return view;
    }

    @Override
    public void onClick(View v) {
        if(v.getId()==R.id.button3){
            texttry.setText("hello 仁栋");
            dismiss();

        }
        else if(v.getId()==R.id.button4){
            texttry.setText("hello steven");
            dismiss();
            com1.Onshow(String.valueOf(v.getId()));

        }

    }

    public interface comm{
        public void Onshow(String messege);
    }
}
