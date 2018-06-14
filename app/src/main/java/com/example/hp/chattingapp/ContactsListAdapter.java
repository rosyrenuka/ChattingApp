package com.example.hp.chattingapp;

import android.app.ActivityOptions;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import static java.security.AccessController.getContext;

/**
 * Created by HP on 06-06-2018.
 */

public class ContactsListAdapter extends RecyclerView.Adapter<ContactsListAdapter.UserHolder> {



    public interface OnClickContact{
        void onContactClick(int position);
    }

    OnClickContact clickContactListener;

    Context context;
    ArrayList<String> contactNames;
    ArrayList<Integer> contactImages;



    public ContactsListAdapter(Context context, ArrayList<String> contactNames, ArrayList<Integer> contactImages, OnClickContact clickContactListener) {

        this.context=context;
        this.contactNames=contactNames;
        this.contactImages=contactImages;
        this.clickContactListener=clickContactListener;

    }


    @Override
    public UserHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.consult_recycler_single,null);

        UserHolder userHolder=new UserHolder(view);
        return userHolder;

    }

    @Override
    public void onBindViewHolder(final UserHolder holder, int position) {

        if(holder!=null)
        {
            holder.contactImage.setImageResource(contactImages.get(position));
            holder.contactName.setText(contactNames.get(position));
        }

        holder.view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                int position = holder.getAdapterPosition();

                Intent intent = new Intent(context,Contact_Detail.class);

                View sharedView = holder.contactImage;

                //int imageid=holder.getItemViewType(contactImages);


               String ans= String.valueOf(holder.contactImage.getDrawable());

                String transitionName = "imageTransition";

                int id=holder.contactImage.getId();

                intent.putExtra("im",id);


                ActivityOptions transitionActivityOptions = null;
                if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP) {
                    transitionActivityOptions = ActivityOptions.makeSceneTransitionAnimation((MainActivity)context, sharedView, transitionName);

                }

                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                    context.startActivity(intent, transitionActivityOptions.toBundle());
                }

               // clickContactListener.onContactClick(holder.getAdapterPosition());

            }
        });
    }

    @Override
    public int getItemCount() {
        return contactNames.size();
    }

    public class UserHolder extends RecyclerView.ViewHolder {

        ImageView contactImage;
        TextView contactName;
        View view;

        public UserHolder(View itemView) {
            super(itemView);

            this.view=itemView;
            this.contactImage=itemView.findViewById(R.id.contact_image);
            this.contactName=itemView.findViewById(R.id.contact_name);
        }
    }
}
