package com.yackeen.yackeen.view.viewholder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.yackeen.yackeen.R;

/**
 * Created by nprx8037 on 12/25/2017.
 */

public class ViewHolderTypeNews extends RecyclerView.ViewHolder {


    private TextView titleTextView, publishedDateTextView;
    private ImageView newsImageView;


    public ViewHolderTypeNews(View v) {
        super(v);
        titleTextView = (TextView) v.findViewById(R.id.text_view_news_title);
        publishedDateTextView = v.findViewById(R.id.text_view_published_date);
        newsImageView = v.findViewById(R.id.image_view_news_image);
    }

    public TextView getTitleTextView() {
        return titleTextView;
    }

    public void setTitleTextView(TextView titleTextView) {
        this.titleTextView = titleTextView;
    }

    public TextView getPublishedDateTextView() {
        return publishedDateTextView;
    }

    public void setPublishedDateTextView(TextView publishedDateTextView) {
        this.publishedDateTextView = publishedDateTextView;
    }

    public ImageView getNewsImageView() {
        return newsImageView;
    }

    public void setNewsImageView(ImageView newsImageView) {
        this.newsImageView = newsImageView;
    }
}
