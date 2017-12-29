package com.yackeen.yackeen.view.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.yackeen.utils.TextUtils;
import com.yackeen.yackeen.R;
import com.yackeen.yackeen.model.dto.WeatherItem;
import com.yackeen.yackeen.view.viewholder.ViewHolderTypeWeather;

import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Locale;

public class WeatherAdapter extends RecyclerView.Adapter<ViewHolderTypeWeather> {

    private List<WeatherItem> mData;
    private Context context;


    public WeatherAdapter(List<WeatherItem> mData, Context context) {
        this.mData = mData;
        this.context = context;
    }

    @Override
    public ViewHolderTypeWeather onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.weather_item, parent, false);
        return new ViewHolderTypeWeather(view);
    }

    @Override
    public void onBindViewHolder(ViewHolderTypeWeather holder, int position) {

        SimpleDateFormat dateFormatter = new SimpleDateFormat("EEEE, MMMM d", Locale.US);
        WeatherItem weatherItem = mData.get(position);
        String maxString = String.format("%s %s\n%s", String.valueOf(weatherItem.getMain().getTempMax()),
                TextUtils.getString(R.string.f), TextUtils.getString(R.string.max));
        String minString = String.format("%s %s\n%s", String.valueOf(weatherItem.getMain().getTempMin()),
                TextUtils.getString(R.string.f), TextUtils.getString(R.string.min));


        holder.getMaxTextView().setText(maxString);
        holder.getDateTextView().setText(dateFormatter.format(mData.get(position).getDt()));
        holder.getMinTextView().setText(minString);

    }

    @Override
    public int getItemCount() {
        return mData.size();
    }
}
