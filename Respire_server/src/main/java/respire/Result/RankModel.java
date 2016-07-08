package respire.Result;

public class RankModel {

	
	private double pm25;
	private long userid;
	
	public RankModel(long userid,int pm25) {
		super();
		this.userid = userid;
		this.pm25 = pm25;
	}

	public RankModel() {
		super();
	}
	
	public long getUserid(){
		return userid;
	}
	
	public void setUserid(long userid){
		this.userid = userid;
	}
	
	public double getPm25(){
		return pm25;
	}
	
	public void setPm25(double pm25){
		this.pm25 = pm25;
	}

}
