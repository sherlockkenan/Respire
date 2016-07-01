package respire.Server;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import respire.Dao.DatanowDao;
import respire.Result.DataModle;
import respire.Dao.DatadayDao;

@Service
public class HistorySever {
	
	@Autowired
	DatanowDao datanowDao;
	@Autowired
	DatadayDao datadayDao;
	
	public List<DataModle> getday(long userid){
		return datanowDao.getbyday(userid);
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
