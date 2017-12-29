package com.yackeen.yackeen.view.viewholder;

import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.yackeen.yackeen.R;


public class ViewHolderTypeWeatherList extends RecyclerView.ViewHolder {

    private RecyclerView weatherRecyclerView;

    public ViewHolderTypeWeatherList(View v) {
        super(v);
        weatherRecyclerView = (RecyclerView) v.findViewById(R.id.recycler_view_weather);
    }

    public RecyclerView getWeatherRecyclerView() {
        return weatherRecyclerView;
    }

    public void setWeatherRecyclerView(RecyclerView weatherRecyclerView) {
        this.weatherRecyclerView = weatherRecyclerView;
    }
}
