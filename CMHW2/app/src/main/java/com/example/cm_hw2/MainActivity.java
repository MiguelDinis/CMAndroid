package com.example.cm_hw2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.cm_hw2.datamodel.City;
import com.example.cm_hw2.datamodel.Weather;
import com.example.cm_hw2.datamodel.WeatherType;
import com.example.cm_hw2.network.CityResultsObserver;
import com.example.cm_hw2.network.ForecastForACityResultsObserver;
import com.example.cm_hw2.network.IpmaWeatherClient;
import com.example.cm_hw2.network.WeatherTypesResultsObserver;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private RecyclerView mRecyclerView;
    private CityListAdapter mAdapter;

    IpmaWeatherClient client = new IpmaWeatherClient();
    HashMap<String, City> cities;
    private String out;
    private HashMap<Integer, WeatherType> weatherDescriptions;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



    }







}
