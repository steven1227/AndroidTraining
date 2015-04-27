package com.example.steven.app1;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * Created by steven on 27-4-15.
 */
public class Myalert extends DialogFragment {

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder bb=new AlertDialog.Builder(getActivity());
         bb.setTitle("hello world");
//        bb.setMessage("I am a test");
        bb.setIcon(R.drawable.pencil_icon);
//        bb.setItems(new String[]{"Netherland","Delft","TU"},new DialogInterface.OnClickListener() {
//            @Override
//            public void onClick(DialogInterface dialog, int which) {
//                Toast.makeText(getActivity(), ":" + which, Toast.LENGTH_SHORT).show();
//            }
//        });

        bb.setMultiChoiceItems(new String[]{"Netherland", "Delft", "TU"}, new boolean[]{true,false,true}, new DialogInterface.OnMultiChoiceClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which, boolean isChecked) {
                Toast.makeText(getActivity(), ":" + which+isChecked, Toast.LENGTH_SHORT).show();
            }
        });

        bb.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(getActivity(), ":" + which, Toast.LENGTH_SHORT).show();
            }
        });
        bb.setPositiveButton("yes",new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(getActivity(), ":" + which, Toast.LENGTH_SHORT).show();
            }
        });
        Dialog dialog=bb.create();
        return dialog;
    }
}
