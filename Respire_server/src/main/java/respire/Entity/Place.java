package respire.Entity;

public class Place {
     private String name;
     private String address;
     private String uid;
     private double latitude;
     private double longitude;
     
     private double distance;
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
     
}
