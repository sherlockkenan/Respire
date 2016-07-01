package respire.Entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "citynodes")
public class CityNode {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int cityid;
	
	@NotNull
	private String name;
	
	@NotNull
	private int fatherid;
	
	@NotNull
	private int level;
	
	public int getCityid(){
		return cityid;
	}
	
	public void setCityid(int cityid){
		this.cityid = cityid;
	}
	
	public String getName(){
		return name;
	}
	
	public void setName(String name){
		this.name = name;
	}
	
	public int getFatherid(){
		return fatherid;
	}
	
	public void setFatherid(int fatherid){
		this.fatherid = fatherid;
	}
	
	public int getLevel(){
		return fatherid;
	}
	
	public void setLevel(int level){
		this.level = level;
	}
}
