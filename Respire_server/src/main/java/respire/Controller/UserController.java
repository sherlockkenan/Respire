package respire.Controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
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
      result.setData("succeed to logout");
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
  

  @RequestMapping("/update")
  @ResponseBody
  public ReturnValue updateUser(@ModelAttribute User user) {
	  ReturnValue result=new ReturnValue();
	  try {
		  long id = user.getUserid();
		  User oldUser = userServer.find(id);
		  
		  if(oldUser == null){
			  result.setReturn_type("fail");
		      result.setData("Error updating the user: User not found!");
		      return result;
		  }
		  
		  if(oldUser.getUsername() != user.getUsername()){
			  result.setReturn_type("fail");
		      result.setData("Error updating the user: Userid or username is not correct.");
		      return result;
		  }
		  
		  userServer.update(user);
		  result.setReturn_type("success");
		  result.setData("User succesfully updated.");
		  return result;
	  }
	  catch (Exception ex) {
         result.setReturn_type("fail");
         result.setData("Error updating the user: " +ex.toString());
         return result;
	  }
  }

  // ------------------------
  // PRIVATE FIELDS
  // ------------------------

//  @Autowired
//  private UserDao userDao;

  @Autowired
  private UserServer userServer;
  
} // class UserController
