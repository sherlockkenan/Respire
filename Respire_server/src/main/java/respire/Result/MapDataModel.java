package respire.Result;

public class MapDataModel {
	 private double pm25;
	 private double so2;
	 private double co2;
	 
	 private double latitude;
	 private double longitude;
	 
	 
	 
	public MapDataModel(double pm25, double so2, double co2, double latitude, double longitude) {
		super();
		this.pm25 = pm25;
		this.so2 = so2;
		this.co2 = co2;
		this.latitude = latitude;
		this.longitude = longitude;
	}
	
	public MapDataModel(int pm25, int so2, int co2, double latitude, double longitude) {
		super();
		this.pm25 = pm25;
		this.so2 = so2;
		this.co2 = co2;
		this.latitude = latitude;
		this.longitude = longitude;
	}
	public double getPm25() {
		return pm25;
	}
	public void setPm25(double pm25) {
		this.pm25 = pm25;
	}
	public double getSo2() {
		return so2;
	}
	public void setSo2(double so2) {
		this.so2 = so2;
	}
	public double getCo2() {
		return co2;
	}
	public void setCo2(double co2) {
		this.co2 = co2;
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
	 
	 
	 
}
