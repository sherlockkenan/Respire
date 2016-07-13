package respire.Entity;



import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "scenery")
public class Scenery {
	@Id
	private String id;
	
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

	

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
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

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
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

	public String getDescribtion() {
		return description;
	}

	public void setDescribtion(String describtion) {
		this.description = describtion;
	}

	public Date getTime() {
		return time;
	}

	public void setTime(Date time) {
		this.time = time;
	}

}
