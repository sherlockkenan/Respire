package com.example.respireapp.Entity;

/**
 * Created by piglet on 2016/7/18.
 */
import com.example.respireapp.Entity.Scenery;
import java.io.Serializable;
import java.util.List;

public class Place{

    private String name;
    private String address;
    private double latitude;
    private double longitude;
    private double distance;
    private int pm25;
    private int so2;
    private int co2;
    private String turl;
    private int status;
    private List<Scenery> sceneries;
    private double general;
    public Place() {
        super();
    }
    public Place(String name, String address, String uid, double latitude, double longitude,double distance) {
        super();
        this.name = name;
        this.address = address;

        this.latitude = latitude;
        this.longitude = longitude;
        this.distance=distance;
    }
    public void setGeneral(double general){
        this.general=general;
    }
    public double getGeneral(){
        return general;
    }
    public void setStatus(int status){
        this.status=status;
    }
    public int getStatus(){
        return status;
    }
    public String getTurl(){
        return turl;
    }
    public void setTurl(String turl){
        this.turl=turl;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getAddress() {
        return address;
    }
    public void setAddress(String address) {
        this.address = address;
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
    public double getDistance() {
        return distance;
    }
    public void setDistance(double distance) {
        this.distance = distance;
    }
    public int getPm25() {
        return pm25;
    }
    public void setPm25(int pm25) {
        this.pm25 = pm25;
    }
    public int getSo2() {
        return so2;
    }
    public void setSo2(int so2) {
        this.so2 = so2;
    }
    public int getCo2() {
        return co2;
    }
    public void setCo2(int co2) {
        this.co2 = co2;
    }
    public List<Scenery> getSceneries() {
        return sceneries;
    }
    public void setSceneries(List<Scenery> sceneries) {
        this.sceneries = sceneries;
    }
}
