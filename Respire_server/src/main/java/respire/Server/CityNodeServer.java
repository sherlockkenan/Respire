package respire.Server;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import respire.Dao.CityNodeDao;
import respire.Entity.CityNode;
import respire.Entity.UserCity;

@Service
public class CityNodeServer {
    //atuowired the cityNodeDao
	@Autowired
	private CityNodeDao cityNodeDao;
	
	public List<CityNode> getCityNodes(int fatherid){
		return cityNodeDao.findByFatherid(fatherid);
	}
	
	public UserCity getusercity(int city4){
		UserCity userCity=new UserCity();
		CityNode citynode4=cityNodeDao.findByCityid(city4);
		userCity.setCity4(citynode4.getName());
		CityNode citynode3=cityNodeDao.findByCityid(citynode4.getFatherid());
		userCity.setCity3(citynode3.getName());
		CityNode citynode2=cityNodeDao.findByCityid(citynode3.getFatherid());
		userCity.setCity2(citynode2.getName());
		CityNode citynode1=cityNodeDao.findByCityid(citynode2.getFatherid());
		userCity.setCity1(citynode1.getName());
		return userCity;

	}
}