package respire.Controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import net.sf.json.JSONArray;
import respire.Entity.User;
import respire.Result.DataModel;
import respire.Result.MapDataModel;
import respire.Result.ReturnValue;
import respire.Service.DataService;

@RestController
@RequestMapping("/map")
public class MapController {
	
	@Autowired
	DataService dataService;
 
	@RequestMapping("/getalldata")
	public ReturnValue getalldata(HttpServletRequest request){
		ReturnValue result=new ReturnValue();
		try{
		    List<MapDataModel> data=dataService.get_today_data();
		    result.setReturn_type("success");
		    result.setData(JSONArray.fromObject(data));
		    return result;
		}
		catch(Exception e){
			result.setReturn_type("success");
			result.setData(e.toString());
			return result;
		}
	}
	
	@RequestMapping("/getdatabyuser")
	public ReturnValue getdatabuuser(HttpServletRequest request){
		ReturnValue result=new ReturnValue();
		User user=(User) request.getSession().getAttribute("user");
		//List<MapDataModel> data=dataService.get_user_data(user.getUserid());
		List<MapDataModel> data=dataService.get_user_data(1);
		result.setReturn_type("success");
		result.setData(JSONArray.fromObject(data));
		return result;
	}
}
