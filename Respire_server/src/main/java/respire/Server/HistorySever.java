package respire.Server;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import respire.Dao.DatanowDao;
import respire.Dao.DatadayDao;

@Service
public class HistorySever {
	
	@Autowired
	DatanowDao datanowDao;
	@Autowired
	DatadayDao datadayDao;
	
	public List<Object> getday(long userid){
		return datanowDao.getbyday(userid);
	}
	
	public List<Object> getweek(long userid){
		return datadayDao.getbyweek(userid);
	}
	
	public List<Object> getmonth(long userid){
		return datadayDao.getbymonth(userid);
	}
	
	public List<Object> getyear(long userid){
		return datadayDao.getbyyear(userid);
	}
}
