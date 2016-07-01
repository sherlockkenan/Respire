package respire.Server;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import respire.Dao.CityNodeDao;
import respire.Entity.CityNode;

@Service
public class CityNodeServer {
    //atuowired the cityNodeDao
	@Autowired
	private CityNodeDao cityNodeDao;
	
	public List<CityNode> getCityNodes(int fatherid){
		return cityNodeDao.findByFatherid(fatherid);
	}
}
