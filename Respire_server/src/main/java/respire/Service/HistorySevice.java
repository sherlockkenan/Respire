package respire.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import respire.Dao.DatanowDao;
import respire.Result.DataModle;
import respire.Dao.DatadayDao;

@Service
public class HistorySevice {
	
	@Autowired
	DatanowDao datanowDao;
	@Autowired
	DatadayDao datadayDao;
	
	public List<DataModle> getday(long userid){
		List<DataModle> list= datanowDao.getbyday(userid);
	    //int temp=list.get(0).getPm25();
	    return list;
	}
	
	public List<DataModle> getweek(long userid){
		return datadayDao.getbyweek(userid);
	}
	
	public List<DataModle> getmonth(long userid){
		return datadayDao.getbymonth(userid);
	}
	
	public List<DataModle> getyear(long userid){
		return datadayDao.getbyyear(userid);
	}
}
