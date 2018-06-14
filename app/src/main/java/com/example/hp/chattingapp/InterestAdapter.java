package com.example.hp.chattingapp;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import static android.widget.Toast.LENGTH_SHORT;

/**
 * Created by HP on 25-05-2018.
 */

public class InterestAdapter  extends RecyclerView.Adapter<InterestAdapter.ViewHolder>{

    Context context;
    ArrayList<String> interestList;

    public interface OnItemDeleteListener
    {
        void OnItemDelete(int position);
    }

    OnItemDeleteListener listener;


    public InterestAdapter(Context context, ArrayList<String> interestList,OnItemDeleteListener listener) {
        this.context = context;
        this.interestList = interestList;
        this.listener=listener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        LayoutInflater inflater=(LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view=inflater.inflate(R.layout.interest_single_layout,null);

        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;

    }

    @Override
    public void onBindViewHolder(final InterestAdapter.ViewHolder holder, int position) {

        if(holder!=null)
        {
            Toast.makeText(context, "inside on bind", Toast.LENGTH_SHORT).show();
            holder.interest.setText(interestList.get(position));

            holder.delete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    listener.OnItemDelete(holder.getAdapterPosition());
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return interestList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        View view;
        ImageView delete;
        TextView interest;

        public ViewHolder(View itemView) {

            super(itemView);
            this.view=itemView;
            delete=view.findViewById(R.id.delete);
            interest=view.findViewById(R.id.interest);
        }
    }
}
