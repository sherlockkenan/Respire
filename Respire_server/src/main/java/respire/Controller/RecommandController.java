package respire.Controller;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.hibernate.loader.custom.Return;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;
import respire.Dao.RecommandDao;
import respire.Entity.Click;
import respire.Entity.Item;
import respire.Entity.Place;
import respire.Entity.Search;
import respire.Entity.User;
import respire.Result.ReturnValue;
import respire.Result.SceneryDataModel;
import respire.Service.RecommandService;
import respire.Utils.JsonDateValueProcessor;

@RestController
@RequestMapping("/recommand")
public class RecommandController {
	@Autowired
	private RecommandService recommandService;
	
	@RequestMapping("/getclickitem")
	public ReturnValue getclickitem(HttpServletRequest request,@RequestBody Map<String, Object>map){
		
		ReturnValue result=new ReturnValue();
		recommandService.saveclickitem(map,request);
		
		return result;
	}
	
	@RequestMapping("search")
	public ReturnValue search(HttpServletRequest request,@RequestBody Search search){
		
		ReturnValue result=new ReturnValue();
		List<Place>places=recommandService.search(search);
		result.setReturn_type("success");
		JsonConfig jsonConfig = new JsonConfig();  
		  jsonConfig.registerJsonValueProcessor(Date.class, new JsonDateValueProcessor());
		result.setData(JSONArray.fromObject(places,jsonConfig));	
		return result;	
	}
	
	@RequestMapping("getrecommand")
	public ReturnValue getrecommand(HttpServletRequest request,@RequestBody Search search){
		ReturnValue result=new ReturnValue();
		//User user=(User) request.getSession().getAttribute("user");
		User user=new User();
		user.setUserid("1");
		if(user!=null){
	       List<SceneryDataModel> recommand=recommandService.getrecommand(user.getUserid(),search.getLatitude(),search.getLongitude());
	       result.setReturn_type("success");
	       JsonConfig jsonConfig = new JsonConfig();  
		  jsonConfig.registerJsonValueProcessor(Date.class, new JsonDateValueProcessor());
		   result.setData(JSONArray.fromObject(recommand,jsonConfig));
		   return result;
		}
				
		
		return result;	
		
	}
	

}
