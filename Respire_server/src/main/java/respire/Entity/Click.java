package respire.Entity;

import javax.persistence.Entity;
import javax.persistence.Table;


public class Click {
	private String userid;
	private String itemid;
	private double lengrh;
	public String getUserid() {
		return userid;
	}
	public void setUserid(String userid) {
		this.userid = userid;
	}
	public String getItemid() {
		return itemid;
	}
	public void setItemid(String itemid) {
		this.itemid = itemid;
	}
	public double getLengrh() {
		return lengrh;
	}
	public void setLengrh(double lengrh) {
		this.lengrh = lengrh;
	}
	
  
}
