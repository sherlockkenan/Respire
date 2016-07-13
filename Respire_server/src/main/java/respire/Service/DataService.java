package respire.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import respire.Dao.DatanowDao;
import respire.Entity.Datanow;
import respire.Result.MapDataModel;

@Service
public class DataService {
	
       @Autowired
       DatanowDao datanowDao;
       
       public List<MapDataModel> get_today_data(){
    	   return datanowDao.getall();
    	   
       }
       
       public List<MapDataModel> get_user_data(String userid){
    	   return datanowDao.getbyuser(userid);
    	   
       }
}
