package com.yackeen.yackeen.view.viewholder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.yackeen.yackeen.R;


public class ViewHolderTypeWeather extends RecyclerView.ViewHolder {

    private TextView minTextView, maxTextView, dateTextView;

    public ViewHolderTypeWeather(View v) {
        super(v);
        minTextView = v.findViewById(R.id.text_view_weather_min);
        maxTextView = v.findViewById(R.id.text_view_weather_max);
        dateTextView = v.findViewById(R.id.text_view_weather_date);
    }

    public TextView getMinTextView() {
        return minTextView;
    }

    public void setMinTextView(TextView minTextView) {
        this.minTextView = minTextView;
    }

    public TextView getMaxTextView() {
        return maxTextView;
    }

    public void setMaxTextView(TextView maxTextView) {
        this.maxTextView = maxTextView;
    }

    public TextView getDateTextView() {
        return dateTextView;
    }

    public void setDateTextView(TextView dateTextView) {
        this.dateTextView = dateTextView;
    }
}
