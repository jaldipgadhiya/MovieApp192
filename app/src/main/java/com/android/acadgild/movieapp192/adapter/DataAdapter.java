package com.android.acadgild.movieapp192.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;


import com.android.acadgild.movieapp192.R;
import com.android.acadgild.movieapp192.models.DataHandler;

import java.util.List;

/**
 * Created by Jal on 22-10-2017.
 * Data Adapter class to assign adapter to RecyclerView.
 */
public class DataAdapter extends RecyclerView.Adapter<DataAdapter.ViewHolder>{

    Context context;
    List<DataHandler> data;
    ClickListener clickListener;

    public DataAdapter(Context context, List<DataHandler> data){
        this.context=context;
        this.data=data;
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.row, null);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;

    }

    //onBindViewHolder method to bind data to recycler view.
    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        holder.name.setText(data.get(position).getName());
        holder.vote.setText(data.get(position).getVote()+"");
        holder.mid.setText(data.get(position).getMid());
        holder.parent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(clickListener!=null){
                    clickListener.ItemClicked(v,position);
                }
            }
        });
    }

    //getItemCount method to get count of list size.
    @Override
    public int getItemCount() {
        return data.size();
    }

    //ViewHolder class to define UI components need to be bound
    public class ViewHolder extends RecyclerView.ViewHolder{

        TextView name, vote, mid;
        RelativeLayout parent;

        public ViewHolder(View itemView) {
            super(itemView);
            name= (TextView)itemView.findViewById(R.id.name);
            vote= (TextView)itemView.findViewById(R.id.vote);
            mid= (TextView)itemView.findViewById(R.id.mid);
            parent= (RelativeLayout)itemView.findViewById(R.id.parent);
        }
    }

    //ClickListener Interface
    public interface ClickListener{
        void ItemClicked(View v, int position);
    }
    //setClickListener method
    public void setClickListener(ClickListener clickListener){
        this.clickListener=clickListener;
    }
}
