package respire.Entity;

import java.io.Serializable;
import java.util.Date;


public class DatanowPK implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private long userid;
		
    private Date time;

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
     
     
		
}
