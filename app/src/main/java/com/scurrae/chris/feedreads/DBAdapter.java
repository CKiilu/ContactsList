package com.scurrae.chris.feedreads;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by chris on 3/17/16.
 */
public class DBAdapter extends RecyclerView.Adapter<DBAdapter.MyViewHolder> {
    private LayoutInflater inflater;
    List<Contact> data = new ArrayList<>();

    public DBAdapter(Context context, List<Contact> data){
        inflater = LayoutInflater.from(context);
        this.data = data;
    }
    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view  = inflater.inflate(R.layout.views, parent, false);
        MyViewHolder holder = new MyViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        Contact current = data.get(position);
        holder.textView.setText(current.get_name());
        holder.imageView.setText(current.get_phone_number());


    }

    @Override
    public int getItemCount() {
        return data.size();
    }
    class MyViewHolder extends RecyclerView.ViewHolder{
        TextView textView;
        TextView imageView;

        public MyViewHolder(View itemView) {
            super(itemView);
            textView = (TextView)itemView.findViewById(R.id.contactname);
            imageView = (TextView)itemView.findViewById(R.id.contactnum);


        }
    }
}
