package com.example.newclass;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        List<String> mList = new ArrayList<>();
        for (int i = 0;i<100;i++)
        {
            String s = String.valueOf(i);
            mList.add(s);
        }
        List<Integer> mHeight = new ArrayList<Integer>();
        for (int i = 0;i<100;i++)
        {
            mHeight.add((int)(100+Math.random()*300));
        }

        RecyclerView mRecycleView = this.findViewById(R.id.recycle_View);

        mRecycleView.setLayoutManager(new LinearLayoutManager(this));

        mRecycleView.setItemAnimator(new DefaultItemAnimator());

        HomeAdapter mHomeAdapter = new HomeAdapter(this,mList,mHeight);

        mHomeAdapter.setOnItemClickListener(new HomeAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                Toast.makeText(MainActivity.this, "点击第"+(position+1)+"条"
                        , Toast.LENGTH_SHORT).show();
            }

            @Override
            public void OnItemLongClick(View view, int position) {

                new AlertDialog.Builder(MainActivity.this)
                        .setTitle("确认删除吗？")
                        .setNegativeButton("取消",null)
                        .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                mHomeAdapter.removeData(position);
                            }
                        }).show();
            }
        });

        //mRecycleView.addItemDecoration(new DividerItemDecoration(MainActivity.this,DividerItemDecoration.VERTICAL_LIST));

        mRecycleView.setLayoutManager(new StaggeredGridLayoutManager(4,
                StaggeredGridLayoutManager.VERTICAL));
       // mRecycleView.addItemDecoration((new DividerItemDecoration(MainActivity.this, DividerItemDecoration.HORIZONTAL_LIST)));
        mRecycleView.setAdapter(mHomeAdapter);
    }

    static class HomeAdapter extends RecyclerView.Adapter<HomeAdapter.MyViewHolder>{



        public interface OnItemClickListener
        {
            void onItemClick(View view,int position);
            void OnItemLongClick(View view,int position);
        }
        public void setOnItemClickListener(OnItemClickListener mOnItemClickListener)
        {
            this.mOnItemClickListener = mOnItemClickListener;
        }
        private OnItemClickListener mOnItemClickListener;
        private List<String> mList;
        private List<Integer> mHeights;
        private Context mContext;




        public HomeAdapter(Context context,List<String> list,List<Integer> integerList){
            mHeights = integerList;
            mContext = context;
            mList = list;
        }

        public void removeData(int position){
            mList.remove(position);
            notifyItemChanged(position);
        }


        @NonNull
        @Override
        public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            MyViewHolder holder = new MyViewHolder(LayoutInflater.from(mContext)
                    .inflate(R.layout.lise_recyleview,parent,false));

            return holder;
        }

        @Override
        public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

            holder.tv.setText(mList.get(position));

            ViewGroup.LayoutParams lp = holder.tv.getLayoutParams();
            lp.height = mHeights.get(position);
            holder.tv.setLayoutParams(lp);



            if (mOnItemClickListener != null)
            {

                holder.tv.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        int pos =holder.getLayoutPosition();
                        mOnItemClickListener.onItemClick(holder.tv,pos);
                    }
                });
            }
            holder.tv.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    int pos = holder.getLayoutPosition();
                    mOnItemClickListener.OnItemLongClick(holder.tv,pos);
                    return false;
                }
            });

        }

        @Override
        public int getItemCount() {
            return mList.size();
        }

        public class MyViewHolder extends RecyclerView.ViewHolder {
            TextView tv;

            public MyViewHolder(@NonNull View itemView) {
                super(itemView);
                tv = itemView.findViewById(R.id.tv_list);
            }
        }
    }

}