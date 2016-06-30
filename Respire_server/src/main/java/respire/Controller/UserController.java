package respire.Controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.aspectj.weaver.ast.Test;
import org.hibernate.loader.custom.Return;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.metadata.PostgresCallMetaDataProvider;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import respire.Server.UserServer;
import respire.Entity.Datanow;
import respire.Entity.User;
import respire.Result.ReturnValue;


/**
 * A class to test interactions with the MySQL database using the UserDao class.
 *
 * @author respire
 */
@Controller
public class UserController {

  @RequestMapping("/register")
  @ResponseBody
  public ReturnValue register(@ModelAttribute User user) {
      ReturnValue result=new ReturnValue();
    try {
         userServer.register(user);
  
         result.setReturn_type("success");
         result.setData("success to register");
         return result;
    }
    catch (Exception ex) {

         result.setReturn_type("fail");
         result.setData(ex.toString());
         return result;
    }

  }
  
  
  @RequestMapping(value="/login",method=RequestMethod.GET)
  @ResponseBody
  public ReturnValue login(String username,String password,HttpServletRequest request) {
	    ReturnValue result=new ReturnValue();
	    
    try {
      User userfind = userServer.login(username, password);
      if(userfind!=null){
    	  //登录成功
    	  request.getSession().setAttribute("user", userfind);
    	  request.getSession().getId();
          result.setReturn_type("success");
          result.setData("success to login");
          return result;
      }else{
          result.setReturn_type("fail");
          result.setData("fail to login");
          return result;
      }
    }catch (Exception ex) {
  
        result.setReturn_type("fail");
        result.setData(ex.toString());
        return result;
      }
   
  }
  
 
  
  @RequestMapping("/logout")
  @ResponseBody
  public ReturnValue logout(HttpServletRequest request) {
	  ReturnValue result=new ReturnValue();
    try {
      request.getSession().removeAttribute("user");
      result.setReturn_type("success");
      result.setData("succeed to login");
      return result;
    }
    catch (Exception ex) {
    	result.setReturn_type("fail");
        result.setData(ex.toString());
        return result;
    }
  }
  
  
  @RequestMapping("/postdata")
  @ResponseBody
  public ReturnValue postdata(@ModelAttribute Datanow datanow,HttpServletRequest request){
	 ReturnValue result=new ReturnValue();
	 
	 User user=(User) request.getSession().getAttribute("user");
	 if(user==null) {
		 result.setReturn_type("fail");
         result.setData("user not register"); 
         return result;
	}
	 try{
	    datanow.setUserid(user.getUserid());
	    datanow.setTime(new java.util.Date());
	    userServer.postdata(datanow);
	    result.setReturn_type("success");
        result.setData("succees to postdata");
        return result;
     }catch (Exception e){
    	 result.setReturn_type("fail");
         result.setData(e); 
         return result;
     }
  }
  
  @Autowired
  private UserServer userServer;
  
} // class UserController
