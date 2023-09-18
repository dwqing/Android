package com.example.newclass;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class RecyclerViewAdapter extends RecyclerView.Adapter <RecyclerViewAdapter.ViewHolder>{
    private Context mContext;

    public RecyclerViewAdapter(Context context) {
    mContext = context;
    }

    @Override
    public RecyclerViewAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view =
                LayoutInflater.from(parent.getContext()).inflate
                        (R.layout.list_item_card,parent,false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final RecyclerViewAdapter.ViewHolder holder, int position) {

        final View view = holder.mView;
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(mContext, "奔跑在孤傲的路上", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return 10;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{

        public final  View mView;
        public ViewHolder(@NonNull View itemView) {

            super(itemView);
            mView  = itemView;
        }
    }
}
