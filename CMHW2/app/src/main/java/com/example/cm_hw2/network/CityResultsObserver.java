package com.example.cm_hw2.network;

import com.example.cm_hw2.datamodel.City;

import java.util.HashMap;


public interface  CityResultsObserver {
    public void receiveCitiesList(HashMap<String, City> citiesCollection);
    public void onFailure(Throwable cause);
}
