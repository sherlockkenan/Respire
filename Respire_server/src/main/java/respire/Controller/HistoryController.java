package respire.Controller;

import java.util.List;
import java.util.concurrent.locks.ReentrantReadWriteLock;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mysql.fabric.xmlrpc.base.Data;

import respire.Entity.User;
import respire.Result.ReturnValue;
import respire.Server.HistorySever;

@RestController
@RequestMapping("/history")
public class HistoryController {
	
	@Autowired
	HistorySever historyServer;
	
	
	@RequestMapping("/getday")
	public ReturnValue getday(HttpServletRequest request){
		ReturnValue result=new ReturnValue();
		//User user=(User) request.getSession().getAttribute("user");
		List<Object> data=historyServer.getday(2);
		
		result.setData(data);
		result.setReturn_type("success");
		return result;
	}
	
//	@RequestMapping("/getweek")
//	public ReturnValue getday(){
//		
//	}
//	
//	@RequestMapping("/getmonth")
//	public ReturnValue getday(){
//		
//	}
//	
//	@RequestMapping("/getyear")
//	public ReturnValue getday(){
//		
//	}
//	
	
	
}
