package com.costa.bs.splendours.Ui;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
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
    private Context mContext;

    HomeScreenAdapter(Context aContext)  {
        mData =  new ArrayList<>();
        mContext = aContext;
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
        final Venue aItem = mData.get(position);

        if (aItem != null) {

            holder.mTitleTv.setText(aItem.name);
            if ((aItem.location != null)
                    && (aItem.location.formattedAddress != null)) {

                if (aItem.location.formattedAddress.size() >= 1) {
                    holder.mAddressOneTv.setText(aItem.location.formattedAddress.get(0));
                    holder.mAddressOneTv.setVisibility(View.VISIBLE);
                } else {
                    holder.mAddressOneTv.setVisibility(View.GONE);
                }

                if (aItem.location.formattedAddress.size() >= 2) {
                    holder.mAddressTwoTv.setText(aItem.location.formattedAddress.get(1));
                    holder.mAddressTwoTv.setVisibility(View.VISIBLE);
                } else {
                    holder.mAddressTwoTv.setVisibility(View.GONE);
                }

                if (aItem.location.formattedAddress.size() >= 3) {
                    holder.mAddressThreeTv.setText(aItem.location.formattedAddress.get(2));
                    holder.mAddressThreeTv.setVisibility(View.VISIBLE);
                } else {
                    holder.mAddressThreeTv.setVisibility(View.GONE);
                }

            }

            if (!TextUtils.isEmpty(aItem.url)) {

                holder.mLinkFl.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String aUrl = aItem.url;
                        if (!aUrl.startsWith("http://") && !aUrl.startsWith("https://")) {
                            aUrl = "http://" + aUrl;
                        }
                        Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(aUrl));
                        mContext.startActivity(browserIntent);
                    }
                });
                holder.mLinkFl.setVisibility(View.VISIBLE);
            } else {
                holder.mLinkFl.setVisibility(View.GONE);
            }
        }
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

}
