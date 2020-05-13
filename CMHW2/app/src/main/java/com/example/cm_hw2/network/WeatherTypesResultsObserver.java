package com.example.cm_hw2.network;

import com.example.cm_hw2.datamodel.WeatherType;

import java.util.HashMap;



public interface WeatherTypesResultsObserver {
    public void receiveWeatherTypesList(HashMap<Integer, WeatherType> descriptorsCollection);
    public void onFailure(Throwable cause);
}
