package respire.Dao;


import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import respire.Entity.Datanow;
import respire.Entity.DatanowPK;

@Repository 
public interface DatanowDao extends CrudRepository<Datanow, DatanowPK> {
		
	@Query(value="select date_format(time, '%Y-%m-%d-%H')as time,Avg(pm25) as pm25, Avg(so2) as so2,Avg(co2) as co2 from datanow where date_format(now(),'%Y-%m-%d')=DATE_FORMAT(time,'%Y-%m-%d') and userid=:userid group by date_format(time, '%Y-%m-%d-%H') ORDER BY time desc")
	 public List<Object> getbyday(@Param("userid") long userid);
	
	@Query(value="select date_format(time, '%Y-%m-%d')as time,Avg(pm25) as pm25, Avg(so2) as so2,Avg(co2) as co2 from datanow where date_sub(curdate(), INTERVAL 7 DAY) <= date(time)and userid=:userid group by date_format(time, '%Y-%m-%d') ORDER BY time desc")
	 public List<Object> getbyweek(@Param("userid") long userid);
}
