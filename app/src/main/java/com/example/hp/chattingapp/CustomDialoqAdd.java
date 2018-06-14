package com.example.hp.chattingapp;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatDialogFragment;
import android.view.LayoutInflater;
import android.view.View;

import java.util.ArrayList;

/**
 * Created by HP on 25-05-2018.
 */

@SuppressLint("ValidFragment")
public class CustomDialoqAdd extends AppCompatDialogFragment {

    ArrayList<String> list;


    public CustomDialoqAdd(ArrayList<String> list) {
        this.list = list;

    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {


        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.dialog_custom_add,null);
        builder.setView(view).setTitle("Add to list").setNegativeButton("Cancel",null).setPositiveButton("Add", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

                // add to arraylist , save into shared preference + adapter notify change
            }
        });




        return super.onCreateDialog(savedInstanceState);

    }
}
