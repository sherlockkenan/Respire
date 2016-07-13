package respire.Entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "click")
public class Click {
	
	@Id
	private String clickid;
	private String userid;
	private String itemid;
	private double lengrh;
	
	
	public String getClickid() {
		return clickid;
	}
	public void setClickid(String clickid) {
		this.clickid = clickid;
	}
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
