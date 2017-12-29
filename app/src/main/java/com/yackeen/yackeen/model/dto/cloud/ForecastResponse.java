package com.yackeen.yackeen.model.dto.cloud;


import com.yackeen.yackeen.model.dto.WeatherItem;

import java.util.List;


/**
 * Created by EslamHussein on 12/28/17.
 */

public class ForecastResponse {

    private String cod;
    private int cnt;
    private List<WeatherItem> list;

    public String getCod() {
        return cod;
    }

    public void setCod(String cod) {
        this.cod = cod;
    }

    public int getCnt() {
        return cnt;
    }

    public void setCnt(int cnt) {
        this.cnt = cnt;
    }

    public List<WeatherItem> getList() {
        return list;
    }

    public void setList(List<WeatherItem> list) {
        this.list = list;
    }


}
