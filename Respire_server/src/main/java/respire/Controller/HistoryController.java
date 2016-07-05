package respire.Controller;

import java.util.List;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import net.sf.json.JSONArray;
import respire.Result.DataModle;

import respire.Result.ReturnValue;
import respire.Service.HistorySevice;
import respire.Entity.User;


@RestController
@RequestMapping("/history")
public class HistoryController {
	
	@Autowired
	HistorySevice historyServer;
	
	
	@RequestMapping("/getday")
	public ReturnValue getday(HttpServletRequest request){
		ReturnValue result=new ReturnValue();
		User user=(User) request.getSession().getAttribute("user");
		List<DataModle> data=historyServer.getday(user.getUserid());
		//System.out.println(data.get(1).getCo2());
       
		result.setData(JSONArray.fromObject(data));
		result.setReturn_type("success");
		return result;
	}
	
	@RequestMapping("/getweek")
	public ReturnValue getweek(HttpServletRequest request){
		ReturnValue result=new ReturnValue();
		User user=(User) request.getSession().getAttribute("user");
		List<DataModle> data=historyServer.getweek(user.getUserid());
		
		result.setData(JSONArray.fromObject(data));
		result.setReturn_type("success");
		return result;
	}
	
	@RequestMapping("/getmonth")
	public ReturnValue getmonth(HttpServletRequest request){
		ReturnValue result=new ReturnValue();
		User user=(User) request.getSession().getAttribute("user");
		List<DataModle> data=historyServer.getmonth(user.getUserid());
		
		result.setData(JSONArray.fromObject(data));
		result.setReturn_type("success");
		return result;
	}
	
	@RequestMapping("/getyear")
	public ReturnValue getyear(HttpServletRequest request){
		ReturnValue result=new ReturnValue();
		User user=(User) request.getSession().getAttribute("user");
		List<DataModle> data=historyServer.getyear(user.getUserid());
		
		result.setData(JSONArray.fromObject(data));
		result.setReturn_type("success");
		return result;
	}
	
	
	
}
