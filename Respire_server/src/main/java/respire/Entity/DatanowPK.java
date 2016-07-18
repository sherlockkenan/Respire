package respire.Entity;

import java.io.Serializable;
import java.util.Date;


public class DatanowPK implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String userid;
		
    private Date time;



	public String getUserid() {
		return userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}

	public Date getTime() {
		return time;
	}

	public void setTime(Date time) {
		this.time = time;
	}
     
     
		
}
