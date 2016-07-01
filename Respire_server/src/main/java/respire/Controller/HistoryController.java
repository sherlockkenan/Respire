package respire.Controller;

import java.util.List;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import respire.Entity.User;
import respire.Result.ReturnValue;
import respire.Server.HistorySever;

@Controller
@RequestMapping("/history")
public class HistoryController {
	
	@Autowired
	HistorySever historyServer;
	
	
	@RequestMapping("/getday")
	public ReturnValue getday(HttpServletRequest request){
		ReturnValue result=new ReturnValue();
		User user=(User) request.getSession().getAttribute("user");
		List<Object> data=historyServer.getday(user.getUserid());
		
		result.setData(data);
		result.setReturn_type("success");
		return result;
	}
	
	@RequestMapping("/getweek")
	public ReturnValue getweek(HttpServletRequest request){
		ReturnValue result=new ReturnValue();
		User user=(User) request.getSession().getAttribute("user");
		List<Object> data=historyServer.getday(user.getUserid());
		
		result.setData(data);
		result.setReturn_type("success");
		return result;
	}
	
	@RequestMapping("/getmonth")
	public ReturnValue getmonth(HttpServletRequest request){
		ReturnValue result=new ReturnValue();
		User user=(User) request.getSession().getAttribute("user");
		List<Object> data=historyServer.getday(user.getUserid());
		
		result.setData(data);
		result.setReturn_type("success");
		return result;
	}
	
	@RequestMapping("/getyear")
	public ReturnValue getyear(HttpServletRequest request){
		ReturnValue result=new ReturnValue();
		User user=(User) request.getSession().getAttribute("user");
		List<Object> data=historyServer.getday(user.getUserid());
		
		result.setData(data);
		result.setReturn_type("success");
		return result;
	}
	
	
	
}
