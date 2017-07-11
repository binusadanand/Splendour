package com.costa.bs.splendours.Models;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.costa.bs.splendours.R;

/**
 * Created by binusadanand on 11/07/2017.
 */

public class SearchResultViewHolder extends RecyclerView.ViewHolder {

    public ImageView mImageIv;
    public TextView mTitleTv;
    public TextView mAddressOneTv;
    public TextView mAddressTwoTv;
    public TextView mAddressThreeTv;
    public FrameLayout mLinkFl;

    private View mParent;

    public SearchResultViewHolder(View itemView) {
        super(itemView);

        mParent = itemView;
        mImageIv = (ImageView) itemView.findViewById(R.id.view_item_main_image_iv);
        mTitleTv = (TextView) itemView.findViewById(R.id.view_item_main_text_tv);
        mAddressOneTv = (TextView) itemView.findViewById(R.id.venue_item_address_line1);
        mAddressTwoTv =  (TextView) itemView.findViewById(R.id.venue_item_address_line2);
        mAddressThreeTv = (TextView) itemView.findViewById(R.id.venue_item_address_line3);
        mLinkFl = (FrameLayout) itemView.findViewById(R.id.view_item_link_fl);

    }


}
