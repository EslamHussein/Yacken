package com.yackeen.yackeen.view.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.yackeen.utils.PicassoHelper;
import com.yackeen.yackeen.R;
import com.yackeen.yackeen.model.dto.NewsItem;
import com.yackeen.yackeen.model.dto.WeatherItem;
import com.yackeen.yackeen.view.viewholder.RecyclerViewSimpleTextViewHolder;
import com.yackeen.yackeen.view.viewholder.ViewHolderTypeNews;
import com.yackeen.yackeen.view.viewholder.ViewHolderTypeWeatherList;

import java.util.List;


public class MainAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private final int NEWS_ITEM = 0, WEATHER_LIST = 1;
    private List<Object> items;
    private Context context;

    public MainAdapter(List<Object> items, Context context) {
        this.items = items;
        this.context = context;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        RecyclerView.ViewHolder viewHolder;
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        switch (viewType) {

            case NEWS_ITEM:
                View newsView = inflater.inflate(R.layout.news_item, parent, false);
                viewHolder = new ViewHolderTypeNews(newsView);

                break;
            case WEATHER_LIST:

                View weatherView = inflater.inflate(R.layout.weather_list_item, parent, false);
                viewHolder = new ViewHolderTypeWeatherList(weatherView);
                break;
            default:

                View simpleView = inflater.inflate(android.R.layout.simple_expandable_list_item_1, parent, false);
                viewHolder = new RecyclerViewSimpleTextViewHolder(simpleView);
                break;
        }


        return viewHolder;
    }


    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        switch (holder.getItemViewType()) {
            case NEWS_ITEM:
                ViewHolderTypeNews viewHolderTypeNews = (ViewHolderTypeNews) holder;
                configureViewHolderNews(viewHolderTypeNews, position);
                break;
            case WEATHER_LIST:
                ViewHolderTypeWeatherList viewHolderTypeWeather = (ViewHolderTypeWeatherList) holder;
                configureViewHolderWeather(viewHolderTypeWeather, position);
                break;
            default:
                RecyclerViewSimpleTextViewHolder vh = (RecyclerViewSimpleTextViewHolder) holder;
                configureDefaultViewHolder(vh, position);
                break;
        }
    }


    private void configureDefaultViewHolder(RecyclerViewSimpleTextViewHolder vh, int position) {
        vh.getLabel().setText((CharSequence) items.get(position));


    }

    private void configureViewHolderWeather(ViewHolderTypeWeatherList viewHolderTypeWeather, int position) {
        List<WeatherItem> weatherItems = (List<WeatherItem>) items.get(position);
        if (weatherItems != null) {
            viewHolderTypeWeather.getWeatherRecyclerView().setAdapter(new WeatherAdapter(weatherItems, context));
        }
    }

    private void configureViewHolderNews(ViewHolderTypeNews viewHolderTypeNews, int position) {
        NewsItem newsItem = (NewsItem) items.get(position);
        if (newsItem != null) {
            viewHolderTypeNews.getTitleTextView().setText(newsItem.getTitle());
            viewHolderTypeNews.getPublishedDateTextView().setText(newsItem.getPublishedDate());
            if (newsItem.getMultiMediaItem() != null && newsItem.getMultiMediaItem().getUrl() != null)
                PicassoHelper.loadImage(context, newsItem.getMultiMediaItem().getUrl(), viewHolderTypeNews.getNewsImageView());

        }
    }

    @Override
    public int getItemCount() {
        return this.items.size();

    }

    @Override
    public int getItemViewType(int position) {
        if (items.get(position) instanceof List) {
            return WEATHER_LIST;
        } else if (items.get(position) instanceof NewsItem) {
            return NEWS_ITEM;
        }
        return -1;
    }
}
