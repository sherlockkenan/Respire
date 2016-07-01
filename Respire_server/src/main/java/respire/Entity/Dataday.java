package respire.Entity;

import java.util.Date;

import javax.persistence.Entity;

import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;



@Entity 
@Table(name="dataday")
@IdClass(DatanowPK.class)
public class Dataday {
	
	@Id
	private long userid;
	
	@Id
	private Date time;
	
    private int pm25;
    private int so2;
    private int co2;
    
	public long getUserid() {
		return userid;
	}
	public void setUserid(long userid) {
		this.userid = userid;
	}
	public Date getTime() {
		return time;
	}
	public void setTime(Date time) {
		this.time = time;
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
}
