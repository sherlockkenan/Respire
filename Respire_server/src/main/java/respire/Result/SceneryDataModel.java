package respire.Result;

import java.util.Date;

import respire.Entity.Scenery;

public class SceneryDataModel {
     private String sceneryid;
	
	 private String photo;
	
	 private double latitude;
	 
	 private double longitude;
	 
	 private String username;
	 
	 private int pm25;
	 private int co2;
	 private int so2;
	 
	 private String location;
	 private String description;
	 private Date time;
	 private double distance;
	 
	 
	public SceneryDataModel() {
		super();
	}
	
	public SceneryDataModel(Scenery scenery,double distance) {
		super();
		this.co2=scenery.getCo2();
		this.description=scenery.getDescription();
		this.latitude=scenery.getLatitude();
		this.location=scenery.getLocation();
		this.longitude=scenery.getLongitude();
		this.photo=scenery.getPhoto();
		this.pm25=scenery.getPm25();
		this.sceneryid=scenery.getId();
		this.so2=scenery.getSo2();
		this.time=scenery.getTime();
		this.username=scenery.getUsername();
		this.distance=distance;
	}
	
	public String getSceneryid() {
		return sceneryid;
	}
	public void setSceneryid(String sceneryid) {
		this.sceneryid = sceneryid;
	}
	public String getPhoto() {
		return photo;
	}
	public void setPhoto(String photo) {
		this.photo = photo;
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
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public int getPm25() {
		return pm25;
	}
	public void setPm25(int pm25) {
		this.pm25 = pm25;
	}
	public int getCo2() {
		return co2;
	}
	public void setCo2(int co2) {
		this.co2 = co2;
	}
	public int getSo2() {
		return so2;
	}
	public void setSo2(int so2) {
		this.so2 = so2;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Date getTime() {
		return time;
	}
	public void setTime(Date time) {
		this.time = time;
	}
	public double getDistance() {
		return distance;
	}
	public void setDistance(double distance) {
		this.distance = distance;
	}
	 
	 
}
