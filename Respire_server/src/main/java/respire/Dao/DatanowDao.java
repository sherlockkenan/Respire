package respire.Dao;


import java.util.List;

import org.aspectj.weaver.ast.And;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import respire.Entity.Datanow;
import respire.Entity.DatanowPK;
import respire.Result.DataModel;
import respire.Result.MapDataModel;

@Repository 
public interface DatanowDao extends CrudRepository<Datanow, DatanowPK> {
		
	
	@Query("select new respire.Result.DataModel( time,Avg(d.pm25), Avg(d.so2),Avg(d.co2),4) from Datanow d where date_format(now(),'%Y-%m-%d')=DATE_FORMAT(time,'%Y-%m-%d') and d.userid=?1 group by date_format(time, '%Y-%m-%d-%H') ORDER BY time desc")
	public List<DataModel> getbyday( String userid);
	
	@Query("select Avg(d.pm25) as pm25 from Datanow d where date_format(now(),'%Y-%m-%d-%H')=DATE_FORMAT(time,'%Y-%m-%d-%H') and d.userid in (select userid from User where cityid=?1)")
	public double getnow(int cityid);
	
	@Query("select new respire.Result.MapDataModel(d.pm25, d.so2,d.co2,d.latitude,d.longitude) from Datanow d")
	public List<MapDataModel> getall();
	
	@Query("select new respire.Result.MapDataModel(d.pm25, d.so2,d.co2,d.latitude,d.longitude) from Datanow d where userid=?1 ORDER BY time desc")
	public List<MapDataModel> getbyuser(String userid);
	
	@Query("select d from Datanow d where abs(latitude-?1)<0.1 and abs(longitude-?2)<0.1) and date_format(time, '%Y-%m-%d %H')=date_format(now(),'%Y-%m-%d %H')")
	public List<Datanow> getair(double latitude,double longitude);
}
