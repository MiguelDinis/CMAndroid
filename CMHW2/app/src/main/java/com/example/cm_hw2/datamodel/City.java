package com.example.cm_hw2.datamodel;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.Date;
import java.util.List;


public class City {

    @Expose
    @SerializedName("local")
    private String local;

    @Expose
    @SerializedName( "globalIdLocal")
    private int globalIdLocal;

    @Expose
    @SerializedName("latitude")
    private double latitude;

    @Expose
    @SerializedName("longitude")
    private double longitude;

    private Date lastRefresh;

    private List<Weather> forecasts;


    public City(String local, int globalIdLocal, double latitude, double longitude, Date lastRefresh)
    {
        this.local = local;
        this.globalIdLocal = globalIdLocal;
        this.latitude = latitude;
        this.longitude = longitude;
        this.lastRefresh = lastRefresh;
    }



    /* ---- Getters & Setters---- */

    public List<Weather> getForecasts() {
        return forecasts;
    }

    public void setForecasts(List<Weather> forecasts) {
        this.forecasts = forecasts;
    }

    public String getLocal() {
        return local;
    }

    public void setLocal(String local) {
        this.local = local;
    }

    public int getGlobalIdLocal() {return globalIdLocal;}

    public void setGlobalIdLocal(int globalIdLocal) {
        this.globalIdLocal = globalIdLocal;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public void setLastRefresh(Date lastRefresh) { this.lastRefresh = lastRefresh; }

    public Date getLastRefresh() { return lastRefresh; }


    @Override
    public String toString() {
        return "City{" +
                "local='" + local + '\'' +
                ", globalIdLocal=" + globalIdLocal +
                ", forecasts=" + forecasts +
                ", latitude=" + latitude +
                ", longitude=" + longitude +
                '}';
    }
}