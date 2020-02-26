package com.example.spark.ratingsapp;

import android.content.Context;
import android.database.Cursor;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.TextView;

import java.util.List;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.MyViewHolder>{

    Context mContext;
    List<RatingsModel> mData;

    public RecyclerViewAdapter(Context mContext, List<RatingsModel> mData) {
        this.mContext = mContext;
        this.mData = mData;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v;
        v = LayoutInflater.from(mContext).inflate(R.layout.item_rating,parent,false);
        MyViewHolder myViewHolder = new MyViewHolder(v);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {

        holder.tv_rating.setText(mData.get(position).getRating());
        holder.tv_movie.setText(mData.get(position).getMovie());
        holder.tv_review.setText(mData.get(position).getReview());

    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{
        private TextView tv_rating;
        private TextView tv_movie;
        private TextView tv_review;

        public MyViewHolder(View itemView) {
            super(itemView);

            tv_rating = itemView.findViewById(R.id.user_rating);
            tv_movie = itemView.findViewById(R.id.movie_name);
            tv_review = itemView.findViewById(R.id.user_review);
        }
    }
}
