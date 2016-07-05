package respire.Server;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import respire.Result.RankModel;
import respire.Entity.User;
import respire.Dao.DatadayDao;
import respire.Dao.DatanowDao;

@Service
public class PoolServer {
	@Autowired
	DatanowDao datanowDao;
	@Autowired
	DatadayDao datadayDao;
	
	public List<RankModel> getrank(User user){
		int cityid = user.getCityid();
		return datadayDao.getrank(cityid);
	}
	
	public double getnow(User user){
		int cityid = user.getCityid();
		return datanowDao.getnow(cityid);
	}
}
