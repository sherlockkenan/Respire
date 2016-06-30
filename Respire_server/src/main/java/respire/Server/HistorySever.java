package respire.Server;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import respire.Dao.DatanowDao;

@Service
public class HistorySever {
	
	@Autowired
	DatanowDao datanowDao;
	
	public List<Object> getday(long userid,Date date){
		return datanowDao.getbyday(userid);
	}
}
