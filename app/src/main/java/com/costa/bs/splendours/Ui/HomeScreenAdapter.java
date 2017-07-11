package com.costa.bs.splendours.Ui;

import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.costa.bs.splendours.Models.SearchResult;

import java.util.ArrayList;
import java.util.Locale;

/**
 * Created by binusadanand on 10/07/2017.
 */

public class HomeScreenAdapter extends RecyclerView.Adapter<WeatherViewHolder>{
    private ArrayList<SearchResult> mData;
    private Context mContext;
    WeatherAdapter(Context aContext)  {
        mData =  new ArrayList<>();
        mContext = aContext;
    }

    public void upDate(ArrayList<WeatherItem> aList) {
        mData.clear();
        mData.addAll(aList);
        notifyDataSetChanged();
    }

    @Override
    public WeatherViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.weather_card_layout, parent, false);
        return new WeatherViewHolder(v);
    }

    @Override
    public void onBindViewHolder(WeatherViewHolder holder, int position) {
        WeatherItem aItem = mData.get(position);

        if (aItem != null) {

            holder.mDataTv.setText(DateConverter.FriendlyFromEpoc(aItem.dt));
            holder.mDescriptionTv.setText(aItem.weather.get(0).description);
            holder.mTempTv.setText(String.format(Locale.ENGLISH, "%.1f \u00B0C", UnitConverter.toCelsiusFromK(aItem.main.temp)));

            holder.mItemKey1.setText(R.string.humidity);
            holder.mItemKey2.setText(R.string.pressure);
            holder.mItemKey3.setText(R.string.temp_max);
            holder.mItemKey4.setText(R.string.temp_min);

            holder.mItemVal1.setText(String.format(Locale.ENGLISH, "%.1f %%", aItem.main.humidity));
            holder.mItemVal2.setText(String.format(Locale.ENGLISH, "%.1f hPa", aItem.main.pressure));
            holder.mItemVal3.setText(String.format(Locale.ENGLISH, "%.2f \u00B0C", UnitConverter.toCelsiusFromK(aItem.main.temp_max)));
            holder.mItemVal4.setText(String.format(Locale.ENGLISH, "%.2f \u00B0C", UnitConverter.toCelsiusFromK(aItem.main.temp_min)));


            int aResID = WeatherImageDecoder.getResID(aItem.weather.get(0).icon);
            if (aResID != 0) {
                holder.mIconIv.setImageDrawable(ContextCompat.getDrawable(mContext, aResID));
            }
        }
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }}

}
