package respire.Entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

@Entity 
@Table(name="recommand")
public class Recommand {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	
	private String userid;
	private String itemid1;
	private String itemid2;
	private String itemid3;
	private String itemid4;
	private String itemid5;
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getUserid() {
		return userid;
	}
	public void setUserid(String userid) {
		this.userid = userid;
	}
	public String getItemid1() {
		return itemid1;
	}
	public void setItemid1(String itemid1) {
		this.itemid1 = itemid1;
	}
	public String getItemid2() {
		return itemid2;
	}
	public void setItemid2(String itemid2) {
		this.itemid2 = itemid2;
	}
	public String getItemid3() {
		return itemid3;
	}
	public void setItemid3(String itemid3) {
		this.itemid3 = itemid3;
	}
	public String getItemid4() {
		return itemid4;
	}
	public void setItemid4(String itemid4) {
		this.itemid4 = itemid4;
	}
	public String getItemid5() {
		return itemid5;
	}
	public void setItemid5(String itemid5) {
		this.itemid5 = itemid5;
	}
	

}
