package respire.Controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import respire.Entity.User;
import respire.Result.ReturnValue;
import respire.Service.MessageService;
import respire.Entity.ChatRoom;

@RestController
public class MessageController {
	@Autowired
	private MessageService messageService;
	
	@RequestMapping("/getchatroom")
	public ReturnValue getrank(HttpServletRequest request){
		ReturnValue result=new ReturnValue();
		User user=(User) request.getSession().getAttribute("user");
		ChatRoom room=messageService.findByCityid(user.getCityid());
		if(room == null){
			result.setData(room);
			result.setReturn_type("Error");
			return result;
		}
		result.setData(room);
		result.setReturn_type("success");
		return result;
	}
}
