package respire.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import respire.Dao.DatanowDao;
import respire.Result.DataModel;
import respire.Dao.DatadayDao;

@Service
public class HistorySevice {
	
	@Autowired
	DatanowDao datanowDao;
	@Autowired
	DatadayDao datadayDao;
	
	public List<DataModel> getday(String userid){
		List<DataModel> list= datanowDao.getbyday(userid);
	    //int temp=list.get(0).getPm25();
	    return list;
	}
	
	public List<DataModel> getweek(String userid){
		return datadayDao.getbyweek(userid);
	}
	
	public List<DataModel> getmonth(String userid){
		return datadayDao.getbymonth(userid);
	}
	
	public List<DataModel> getyear(String userid){
		return datadayDao.getbyyear(userid);
	}
}
