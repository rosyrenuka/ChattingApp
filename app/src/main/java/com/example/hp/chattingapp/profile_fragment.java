package com.example.hp.chattingapp;


import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import static android.content.Context.MODE_PRIVATE;


/**
 * A simple {@link Fragment} subclass.
 */
public class profile_fragment extends Fragment  {

    ImageView profile;
   ImageView add;
   TextView profile_name;
   TextView profile_age;
   TextView profile_gender;
   RecyclerView recyclerView;
   InterestAdapter interestAdapter;
    ArrayList<String> interestArrayList=new ArrayList<>();
    EditText addInterest;

    InterestAdapter.OnItemDeleteListener deleteListener;

    public profile_fragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment


        View row = inflater.inflate(R.layout.profile_tab, container, false);
        profile=row.findViewById(R.id.profile_image);
       profile_age=row.findViewById(R.id.age);
        profile_name=row.findViewById(R.id.name);
        profile_gender=row.findViewById(R.id.gender);
        recyclerView=row.findViewById(R.id.recyclerview);
        add=row.findViewById(R.id.add);
//        interestArrayList.add("renuka");
//        interestArrayList.add("raman");



        SharedPreferences prefs = getContext().getSharedPreferences("data", MODE_PRIVATE);
        String restoredText = prefs.getString("text", null);
        if (restoredText != null) {
            String name = prefs.getString("name", "No name defined");//"No name defined" is the default value.
            int idName = prefs.getInt("idName", 0); //0 is the default value.

            String username=prefs.getString("username","");
            String gender=prefs.getString("gender","");
            int age=prefs.getInt("age",0);
            String contact=prefs.getString("contact","");

            profile_name.setText(username);
            profile_age.setText(age);
            profile_gender.setText(gender);

        }



        interestAdapter= new InterestAdapter(getContext(), interestArrayList, new InterestAdapter.OnItemDeleteListener() {
            @Override
            public void OnItemDelete(final int position) {
                Toast.makeText(getContext(), "need to delete item", Toast.LENGTH_LONG).show();

                AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                builder.setMessage("Are you sure to delete ->"+ interestArrayList.get(position)+" from your interest List ?").setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                        interestArrayList.remove(position);
                        interestAdapter.notifyDataSetChanged();
                    }
                }).setNegativeButton("Cancel", null);

                AlertDialog alert = builder.create();
                alert.show();

            }
            });
        recyclerView .setItemAnimator(new DefaultItemAnimator());
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL,false));
        recyclerView.addItemDecoration(new DividerItemDecoration(getContext() , DividerItemDecoration.VERTICAL));
        recyclerView.setAdapter(interestAdapter);
//        interestArrayList.add("renuka");
        interestAdapter.notifyDataSetChanged();




        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Toast.makeText(getContext(),"NEED TO ADD ITEM",Toast.LENGTH_LONG).show();

                AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                LayoutInflater inflater = getActivity().getLayoutInflater();
                View dialogView = inflater.inflate(R.layout.dialog_custom_add,null);
                addInterest = dialogView.findViewById(R.id.edittext);

                builder.setView(dialogView).setTitle("Add to List").setNegativeButton("Cancel",null)
                        .setPositiveButton("ADD", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {

                                if (interestArrayList.size() < 7) {
                                    String text = addInterest.getText().toString();
                                    interestArrayList.add(text);
                                    interestAdapter.notifyDataSetChanged();
                                    // add to arrayList + add to shared preferences + adapter notify
                                } else {
                                    Toast.makeText(getContext(), "Can't add more than 6 interest", Toast.LENGTH_LONG).show();
                                }
                            }
                        });

                AlertDialog alert=builder.create();
                alert.show();

            }
        });

        return row;
    }

}
