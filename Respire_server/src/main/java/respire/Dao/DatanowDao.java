package respire.Dao;


import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import respire.Entity.Datanow;
import respire.Entity.DatanowPK;
import respire.Result.DataModle;

@Repository 
public interface DatanowDao extends CrudRepository<Datanow, DatanowPK> {
		
	
	@Query("select new respire.Result.DataModle( time,Avg(d.pm25), Avg(d.so2),Avg(d.co2),4) from Datanow d where date_format(now(),'%Y-%m-%d')=DATE_FORMAT(time,'%Y-%m-%d') and d.userid=?1 group by date_format(time, '%Y-%m-%d-%H') ORDER BY time desc")
	public List<DataModle> getbyday( long userid);
	
	@Query("select Avg(d.pm25) as pm25 from Datanow d where date_format(now(),'%Y-%m-%d-%H')=DATE_FORMAT(time,'%Y-%m-%d-%H') and d.userid in (select userid from User where cityid=?1)")
	public double getnow(int cityid);
}
