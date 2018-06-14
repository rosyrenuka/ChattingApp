package com.example.hp.chattingapp;

import android.app.Activity;
import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.app.Fragment;
import android.support.v4.util.Pair;
import android.support.v4.view.ViewCompat;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */

public class Consult_fragment extends Fragment {

   public Intent sharedIntent;
   Consult_fragment consult_fragment;


    public Consult_fragment() {
        // Required empty public constructor
    }

    ImageView im;
    RecyclerView contacts_recyclerView;
    ContactsListAdapter contactsListAdapter;
    ArrayList<String> contactNames= new ArrayList<>();
    ArrayList<Integer> contactImages=new ArrayList<>();
    ContactsListAdapter.OnClickContact clickContactListener;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
      final View view =  inflater.inflate(R.layout.consult_tab, container, false);
      contacts_recyclerView=view.findViewById(R.id.consult_recyclerview);

      functionToAddDummyImagesAndContacts();

      sharedIntent = new Intent(getContext(),Contact_Detail.class);
    //  im=userHolder.contactImage;


     contactsListAdapter=new ContactsListAdapter(getContext(), contactNames,contactImages, new ContactsListAdapter.OnClickContact() {
         @Override
         public void onContactClick(int position) {
             Toast.makeText(getContext(), "contact clicked", Toast.LENGTH_SHORT).show();


         }
     });

        contacts_recyclerView .setItemAnimator(new DefaultItemAnimator());
        contacts_recyclerView.setLayoutManager(new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL,false));
        contacts_recyclerView.addItemDecoration(new DividerItemDecoration(getContext() , DividerItemDecoration.VERTICAL));
        contacts_recyclerView.setAdapter(contactsListAdapter);

        contactsListAdapter.notifyDataSetChanged();
        return view;

    }

    private void functionToAddDummyImagesAndContacts() {

        // to add drawable files in arraylists of names and images
        contactImages.add(R.drawable.image1);
        contactImages.add(R.drawable.image2);
        contactImages.add(R.drawable.image3);
        contactImages.add(R.drawable.image4);
        contactImages.add(R.drawable.image5);
        contactImages.add(R.drawable.image6);
        contactImages.add(R.drawable.image7);
        contactImages.add(R.drawable.image8);
        contactImages.add(R.drawable.image9);
        contactImages.add(R.drawable.image10);

        contactNames.add("Albirtina Britain");
        contactNames.add("Well Dornado");
        contactNames.add("Thomas Gnufreek");
        contactNames.add("Messy Chawala");
        contactNames.add("Lindian Bold");
        contactNames.add("Konskiya Albert");
        contactNames.add("Albirtina Britain");
        contactNames.add("Thomas Gnufreek");
        contactNames.add("Messy Chawala");
        contactNames.add("Albirtina Britain");
    }
}
