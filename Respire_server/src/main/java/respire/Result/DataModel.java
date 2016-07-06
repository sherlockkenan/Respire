package respire.Result;

import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.Date;



public class DataModel {

	private String time;
	
    private int pm25;
    private int so2;
    private int co2;
	
	public DataModel(Date time, int pm25, int so2, int co2, int type) {
		super();
		Format formatter = null;
		if(type == 1) formatter = new SimpleDateFormat("MM-dd");
		if(type == 2) formatter = new SimpleDateFormat("dd");
		if(type == 3) formatter = new SimpleDateFormat("MM");
		this.time = formatter.format(time);
		this.pm25 = pm25;
		this.so2 = so2;
		this.co2 = co2;
	}
	public DataModel(Date time, double pm25, double so2, double co2, int type) {
		super();
		Format formatter = null;
		if(type == 1) formatter = new SimpleDateFormat("MM-dd");
		if(type == 2) formatter = new SimpleDateFormat("dd");
		if(type == 3) formatter = new SimpleDateFormat("MM");
		if(type == 4) formatter = new SimpleDateFormat("hh:mm:ss");
		this.time = formatter.format(time);
		this.pm25 = (int) pm25;
		this.so2 = (int) so2;
		this.co2 = (int) co2;
	}

	public DataModel() {
		super();
	}

	public String getTime() {
		return time;
	}
	public void setTime(String time) {
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
