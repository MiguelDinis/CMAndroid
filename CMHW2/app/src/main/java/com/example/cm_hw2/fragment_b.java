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
import android.widget.TextView;

import com.example.cm_hw2.datamodel.City;
import com.example.cm_hw2.datamodel.Weather;
import com.example.cm_hw2.datamodel.WeatherType;
import com.example.cm_hw2.network.CityResultsObserver;
import com.example.cm_hw2.network.ForecastForACityResultsObserver;
import com.example.cm_hw2.network.IpmaWeatherClient;
import com.example.cm_hw2.network.WeatherTypesResultsObserver;

import java.util.HashMap;
import java.util.List;

public class fragment_b extends Fragment {
    private TextView feedback;
    private RecyclerView mRecyclerView;
    private CityListAdapter mAdapter;
    View view;
    String DEFAULT = "N/A";
    IpmaWeatherClient client = new IpmaWeatherClient();
    HashMap<String, City> cities;
    private String out;
    private HashMap<Integer, WeatherType> weatherDescriptions;
    String city;

    public fragment_b() {
        // Required empty public constructor
    }



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //String city  =  sharedPref.getString("city",DEFAULT);
        if(getActivity().getIntent().hasExtra("city")){
            city = getActivity().getIntent().getStringExtra("city");
        }else{
            city = "Aveiro";
        }

        callWeatherForecastForACityStep1(city);

    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        feedback = getView().findViewById(R.id.tvFeedback);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_b, container, false);
        feedback = (TextView) view.findViewById(R.id.tvFeedback);
        return view;
    }

    private void callWeatherForecastForACityStep1(String city) {


        // call the remote api, passing an (anonymous) listener to get back the results
        client.retrieveWeatherConditionsDescriptions(new WeatherTypesResultsObserver() {
            @Override
            public void receiveWeatherTypesList(HashMap<Integer, WeatherType> descriptorsCollection) {
                fragment_b.this.weatherDescriptions = descriptorsCollection;
                callWeatherForecastForACityStep2(city);
            }
            @Override
            public void onFailure(Throwable cause) {

            }
        });

    }

    private void callWeatherForecastForACityStep2(String city) {
        client.retrieveCitiesList(new CityResultsObserver() {

            @Override
            public void receiveCitiesList(HashMap<String, City> citiesCollection) {
                // Create an adapter and supply the data to be displayed.
                //System.out.println(cities.keySet());
                fragment_b.this.cities = citiesCollection;
                City cityFound = cities.get(city);
                int locationId = cityFound.getGlobalIdLocal();
                feedback.append("\nGetting forecast for: " + city); feedback.append("\n \n");
                callWeatherForecastForACityStep3(locationId);
            }

            @Override
            public void onFailure(Throwable cause) {

            }
        });
    }

    private void callWeatherForecastForACityStep3(int localId) {
        client.retrieveForecastForCity(localId, new ForecastForACityResultsObserver() {
            @Override
            public void receiveForecastList(List<Weather> forecast) {

                for (Weather day : forecast) {
                    feedback.append(day.toString());
                    feedback.append("\t");
                }
            }
            @Override
            public void onFailure(Throwable cause) {
                feedback.append( "Failed to get forecast for 5 days");
            }
        });

    }

}