package respire.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import respire.Dao.ClickDao;
import respire.Dao.ItemDao;
import respire.Dao.RecommandDao;
import respire.Dao.SceneryDao;
import respire.Entity.Click;
import respire.Entity.Item;
import respire.Entity.Place;
import respire.Entity.Scenery;
import respire.Entity.Search;
import respire.Entity.User;
import respire.Result.SceneryDataModel;
import respire.Utils.BMapPlace;

@Service
public class RecommandService {
      @Autowired
      ClickDao clickDao;
      @Autowired
      ItemDao itemDao;
      @Autowired
      SceneryDao sceneryDao;
      @Autowired
      RecommandDao recommandDao;
      @Autowired
      UpdateAirService updateAirService;
      
      public void saveclickitem(Map<String, Object>map,HttpServletRequest request){
    	  Item item=new Item();
    	  Click click=new Click();
    	  String itemid=UUID.randomUUID().toString();
    	  item.setItemid(itemid);
    	  item.setTime((int) map.get("time"));
    	  item.setRecomid((String) map.get("recomid"));
    	  click.setItemid(itemid);
    	  click.setLength((double) map.get("length"));
    	  
    	  User user=(User) request.getSession().getAttribute("user");
    	  if(user!=null){
    	      click.setUserid(user.getUserid());
    	  }
    	  itemDao.save(item);
    	  clickDao.save(click);
    	  
      }
      
      public List<Place>search(Search search){
    	List<Place>places=BMapPlace.getplacebyloc(search.getLatitude(), search.getLongitude(), search.getSearch());	
    
    	  for(int i=0;i<places.size();i++){
    		  List<Scenery>sceneries=sceneryDao.findByUid(places.get(i).getUid());
    		  places.get(i).setSceneries(sceneries);
    		  updateAirService.search_for_update(places.get(i));
    	  }
    	  
    	  return places;
    	      
      }
      
      public List<SceneryDataModel> getrecommand(String userid){
    	  
      }
}
