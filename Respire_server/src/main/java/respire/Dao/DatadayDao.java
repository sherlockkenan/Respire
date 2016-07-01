package respire.Dao;


import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import respire.Entity.Datanow;
import respire.Entity.DatanowPK;

@Repository 
public interface DatadayDao extends CrudRepository<Datanow, DatanowPK> {
	//@Query("select d from dataday d where d.userid=?1") 
	@Query("select date_format(time, '%Y-%m-%d-%H')as time,Avg(pm25) as pm25, Avg(so2) as so2,Avg(co2) as co2 from Datanow d where date_format(now(),'%Y-%m-%d')-DATE_FORMAT(time,'%Y-%m-%d')<=7 and d.userid=?1 group by date_format(time, '%Y-%m-%d-%H') ORDER BY time desc")
	public List<Object> getbyweek(long userid);
	
	//@Query("select d from dataday d where d.userid=?1") 
	@Query("select date_format(time, '%Y-%m-%d-%H')as time,Avg(pm25) as pm25, Avg(so2) as so2,Avg(co2) as co2 from Datanow d where date_format(now(),'%Y-%m-%d')-DATE_FORMAT(time,'%Y-%m-%d')<=7 and d.userid=?1 group by date_format(time, '%Y-%m-%d-%H') ORDER BY time desc")
	public List<Object> getbymonth(long userid);
	
	//@Query("select d from dataday d where d.userid=?1") 
	@Query("select date_format(time, '%Y-%m-%d-%H')as time,Avg(pm25) as pm25, Avg(so2) as so2,Avg(co2) as co2 from Datanow d where date_format(now(),'%Y-%m-%d')-DATE_FORMAT(time,'%Y-%m-%d')<=7 and d.userid=?1 group by date_format(time, '%Y-%m-%d-%H') ORDER BY time desc")
	public List<Object> getbyyear(long userid);
}
