package com.example.cm_hw2;

import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.cm_hw2.datamodel.City;
import com.example.cm_hw2.datamodel.WeatherType;
import com.example.cm_hw2.network.CityResultsObserver;
import com.example.cm_hw2.network.IpmaWeatherClient;
import com.example.cm_hw2.network.WeatherTypesResultsObserver;

import java.util.HashMap;

public class fragment_a extends Fragment {
    private RecyclerView mRecyclerView;
    private CityListAdapter mAdapter;
    View view;

    IpmaWeatherClient client = new IpmaWeatherClient();
    HashMap<String, City> cities;
    private String out;
    private HashMap<Integer, WeatherType> weatherDescriptions;

    public fragment_a() {
        // Required empty public constructor
    }



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        callWeatherForecastForACityStep1();

    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        mRecyclerView  = getView().findViewById(R.id.recyclerview);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fragment_a, container, false);
    }

    private void callWeatherForecastForACityStep1() {


        // call the remote api, passing an (anonymous) listener to get back the results
        client.retrieveWeatherConditionsDescriptions(new WeatherTypesResultsObserver() {
            @Override
            public void receiveWeatherTypesList(HashMap<Integer, WeatherType> descriptorsCollection) {
                fragment_a.this.weatherDescriptions = descriptorsCollection;
                callWeatherForecastForACityStep2();
            }
            @Override
            public void onFailure(Throwable cause) {

            }
        });

    }

    private void callWeatherForecastForACityStep2() {
        client.retrieveCitiesList(new CityResultsObserver() {

            @Override
            public void receiveCitiesList(HashMap<String, City> citiesCollection) {
                fragment_a.this.cities = citiesCollection;
                // Create an adapter and supply the data to be displayed.
                //System.out.println(cities.keySet());
                mAdapter = new CityListAdapter(getContext(), cities);

                // Connect the adapter with the RecyclerView.
                mRecyclerView.setAdapter(mAdapter);
                // Give the RecyclerView a default layout manager.
                mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

            }

            @Override
            public void onFailure(Throwable cause) {

            }
        });
    }

}