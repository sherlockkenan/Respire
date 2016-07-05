package respire.Controller;

import java.util.List;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import net.sf.json.JSONArray;
import respire.Entity.Datanow;
import respire.Entity.User;
import respire.Result.DataModle;

import respire.Result.ReturnValue;
import respire.Service.HistorySever;

@RestController
@RequestMapping("/history")
public class HistoryController {
	
	@Autowired
	HistorySever historyServer;
	
	
	@RequestMapping("/getday")
	public ReturnValue getday(HttpServletRequest request){
		ReturnValue result=new ReturnValue();
		//User user=(User) request.getSession().getAttribute("user");
		List<DataModle> data=historyServer.getday(2);
		//System.out.println(data.get(1).getCo2());
       
		result.setData(JSONArray.fromObject(data));
		result.setReturn_type("success");
		return result;
	}
	
	@RequestMapping("/getweek")
	public ReturnValue getweek(HttpServletRequest request){
		ReturnValue result=new ReturnValue();
		//User user=(User) request.getSession().getAttribute("user");
		List<DataModle> data=historyServer.getweek(2);
		
		result.setData(JSONArray.fromObject(data));
		result.setReturn_type("success");
		return result;
	}
	
	@RequestMapping("/getmonth")
	public ReturnValue getmonth(HttpServletRequest request){
		ReturnValue result=new ReturnValue();
		//User user=(User) request.getSession().getAttribute("user");
		List<DataModle> data=historyServer.getmonth(2);
		
		result.setData(JSONArray.fromObject(data));
		result.setReturn_type("success");
		return result;
	}
	
	@RequestMapping("/getyear")
	public ReturnValue getyear(HttpServletRequest request){
		ReturnValue result=new ReturnValue();
		//User user=(User) request.getSession().getAttribute("user");
		List<DataModle> data=historyServer.getyear(2);
		
		result.setData(JSONArray.fromObject(data));
		result.setReturn_type("success");
		return result;
	}
	
	
	
}
