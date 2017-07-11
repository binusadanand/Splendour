package com.costa.bs.splendours.Ui;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.costa.bs.splendours.Models.SearchResultViewHolder;
import com.costa.bs.splendours.Models.Venue;
import com.costa.bs.splendours.R;

import java.util.ArrayList;

/**
 * Created by binusadanand on 10/07/2017.
 */

public class HomeScreenAdapter extends RecyclerView.Adapter<SearchResultViewHolder>{

    private ArrayList<Venue> mData;

    HomeScreenAdapter()  {
        mData =  new ArrayList<>();
    }

    public void upDate(ArrayList<Venue> aList) {
        mData.clear();
        mData.addAll(aList);
        notifyDataSetChanged();
    }

    @Override
    public SearchResultViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.venue_item_layout, parent, false);
        return new SearchResultViewHolder(v);
    }

    @Override
    public void onBindViewHolder(SearchResultViewHolder holder, int position) {
        Venue aItem = mData.get(position);

        if (aItem != null) {

            holder.mTitleTv.setText(aItem.name);
            if ((aItem.location != null)
                    && (aItem.location.formattedAddress != null)) {
                holder.mAddressOneTv.setText(aItem.location.formattedAddress.get(0));
                holder.mAddressTwoTv.setText(aItem.location.formattedAddress.get(1));
                holder.mAddressThreeTv.setText(aItem.location.formattedAddress.get(2));
                holder.mAddressOneTv.setVisibility(View.VISIBLE);
                holder.mAddressTwoTv.setVisibility(View.VISIBLE);
                holder.mAddressThreeTv.setVisibility(View.VISIBLE);
            }

        }
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

}
