package respire.Dao;


import java.util.List;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import respire.Entity.Datanow;
import respire.Entity.DatanowPK;
import respire.Result.DataModel;
import respire.Result.RankModel;

@Repository 
public interface DatadayDao extends PagingAndSortingRepository<Datanow,DatanowPK> {
	
	@Query("select new respire.Result.DataModel(time,pm25,so2,co2,1) from Dataday where userid=?1 and date_format(now(), '%Y-%m-%d')>date_format(time, '%Y-%m-%d')  ORDER BY time desc")
	public List<DataModel> getbyweek1(long userid, Pageable pageable);
	default List<DataModel> getbyweek(long userid){
		return getbyweek1(userid, new PageRequest(0,7));
	}
	
	@Query("select new respire.Result.DataModel(time,pm25,so2,co2,2) from Dataday where userid=?1 and date_format(now(), '%Y-%m')=date_format(time, '%Y-%m') ORDER BY time desc")
	public List<DataModel> getbymonth(long userid);
	
	@Query("select new respire.Result.DataModel(time,Avg(pm25) as pm25, Avg(so2) as so2,Avg(co2) as co2,3) from Dataday d where date_format(now(),'%Y')=DATE_FORMAT(time,'%Y') and d.userid=?1 group by date_format(time, '%Y-%m') ORDER BY time desc") 
	public List<DataModel> getbyyear(long userid);
	
	@Query("select new respire.Result.RankModel(userid,pm25) from Dataday d where date_format(now(),'%Y-%m-%d')=DATE_FORMAT(time,'%Y-%m-%d') and d.userid in (select u.userid from User u where u.cityid = ?1)  ORDER BY pm25 asc") 
	public List<RankModel> getrank1(int cityid, Pageable pageable);
	default List<RankModel> getrank(int cityid){
		return getrank1(cityid, new PageRequest(0,10));
	}

}
