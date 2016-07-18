package respire.Entity;

import java.util.List;

public class Place {
     private String name;
     private String address;
     private String uid;
     private double latitude;
     private double longitude;
     
     private double distance;
     
     private int pm25;
     private int so2;
     private int co2;
     private List<Scenery> sceneries;
	public Place() {
		super();
	}
	public Place(String name, String address, String uid, double latitude, double longitude,double distance) {
		super();
		this.name = name;
		this.address = address;
		this.uid = uid;
		this.latitude = latitude;
		this.longitude = longitude;
		this.distance=distance;
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
	public String getUid() {
		return uid;
	}
	public void setUid(String uid) {
		this.uid = uid;
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
