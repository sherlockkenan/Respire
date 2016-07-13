package respire.Result;

public class RankModel {

	
	private double pm25;
	private String userid;
	
	public RankModel(String userid,int pm25) {
		super();
		this.userid = userid;
		this.pm25 = pm25;
	}

	public RankModel() {
		super();
	}
	
	public String getUserid(){
		return userid;
	}
	
	public void setUserid(String userid){
		this.userid = userid;
	}
	
	public double getPm25(){
		return pm25;
	}
	
	public void setPm25(double pm25){
		this.pm25 = pm25;
	}

}
