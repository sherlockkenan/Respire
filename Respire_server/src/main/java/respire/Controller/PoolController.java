package respire.Controller;

import java.util.List;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import net.sf.json.JSONArray;
import respire.Result.ReturnValue;
import respire.Server.PoolServer;
import respire.Entity.User;
import respire.Result.RankModel;

@RestController
@RequestMapping("/pool")
public class PoolController {
	
	@Autowired
	PoolServer poolServer;
	
	
	@RequestMapping("/getrank")
	public ReturnValue getrank(HttpServletRequest request){
		ReturnValue result=new ReturnValue();
		User user=(User) request.getSession().getAttribute("user");
		List<RankModel> data=poolServer.getrank(user);
       
		result.setData(JSONArray.fromObject(data));
		result.setReturn_type("success");
		return result;
	}
	
	@RequestMapping("/getnow")
	public ReturnValue getnow(HttpServletRequest request){
		ReturnValue result=new ReturnValue();
		User user=(User) request.getSession().getAttribute("user");
		double data=poolServer.getnow(user);
		
		result.setData(JSONArray.fromObject(data));
		result.setReturn_type("success");
		return result;
	}
	
	
	
}
