package com.yackeen.yackeen.model.dto.cloud;

import com.google.gson.annotations.SerializedName;
import com.yackeen.yackeen.model.dto.NewsItem;

import java.util.List;

/**
 * Created by EslamHussein on 12/28/17.
 */

public class NYTimesTopStoriesResponse {

    private String status;
    @SerializedName("num_results")
    private int numResults;
    private List<NewsItem> results;


    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getNumResults() {
        return numResults;
    }

    public void setNumResults(int numResults) {
        this.numResults = numResults;
    }

    public List<NewsItem> getResults() {
        return results;
    }

    public void setResults(List<NewsItem> results) {
        this.results = results;
    }


}
