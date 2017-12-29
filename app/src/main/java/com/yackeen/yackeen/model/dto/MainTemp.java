package com.yackeen.yackeen.model.dto;

import com.google.gson.annotations.SerializedName;

/**
 * Created by EslamHussein on 12/28/17.
 */

public class MainTemp {

    @SerializedName("temp_min")
    private double tempMin;
    @SerializedName("temp_max")
    private double tempMax;

    public MainTemp() {
    }

    public double getTempMin() {
        return tempMin;
    }

    public void setTempMin(double tempMin) {
        this.tempMin = tempMin;
    }

    public double getTempMax() {
        return tempMax;
    }

    public void setTempMax(double tempMax) {
        this.tempMax = tempMax;
    }
}