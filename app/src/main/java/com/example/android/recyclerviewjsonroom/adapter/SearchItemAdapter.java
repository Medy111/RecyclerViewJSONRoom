package com.example.android.recyclerviewjsonroom.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.android.recyclerviewjsonroom.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by Medy on 13.03.2018.
 */

// First take care of the Boilerplate
// create Viewholder class   public class SearchItemViewHolder extends RecyclerView.ViewHolder {
// create constructor matching Super
// implement methods

// 1. intitilize your views
// 2 . find your views


// 3 . insert your logic into the implemented methods



public class SearchItemAdapter extends RecyclerView.Adapter<SearchItemAdapter.SearchItemViewHolder> {

    private Context context;
    private ArrayList<SearchItem> mSearchList;

    // ALT+INSERT Constructor
    public SearchItemAdapter(Context context, ArrayList <SearchItem> mSearchList) {
        this.context = context;
        this.mSearchList = mSearchList;
    }

    @NonNull
    @Override
    public SearchItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // create a new view
        // use R.layout   not R.id  ( R.id is for views not for whole layouts)
        View v = LayoutInflater.from(context).inflate(R.layout.search_item, parent, false);
        return new SearchItemViewHolder(v);

    }

    @Override
    public void onBindViewHolder(@NonNull SearchItemViewHolder holder, int position) {
        // - get element from your dataset / (Arraylist) at this position
        // - replace the contents of the view with that element

        SearchItem currentitem = mSearchList.get(position);


        String imageUrl = currentitem.getmImageUrl();
        String creator = currentitem.getmCreator();

        holder.mTextView.setText(creator);
        //  picasso  takes care of transfroming our url into image
        Picasso.with(context).load(imageUrl).fit().centerInside().into(holder.mImageView);
    }

    //  Return the size of your dataset (invoked by the layout manager)
    // Makes sure we have as many items in our adapter as are in our array list.
    @Override
    public int getItemCount() {
        return mSearchList.size();
    }




    public class SearchItemViewHolder extends RecyclerView.ViewHolder {
        // Initialize Your Search Item
        public ImageView mImageView;
        public TextView mTextView;


        public SearchItemViewHolder(View itemView) {
            super(itemView);

            // find your Views
            mImageView = itemView.findViewById(R.id.image_view);
            mTextView = itemView.findViewById(R.id.text_view_creator);
        }
    }
}
