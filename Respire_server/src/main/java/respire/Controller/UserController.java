package respire.Controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.aspectj.weaver.ast.Test;
import org.hibernate.loader.custom.Return;
import org.springframework.beans.factory.annotation.Autowired;
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
  
  
  @RequestMapping(value="/login",method=RequestMethod.POST)
  @ResponseBody
  public ReturnValue login(String username,String password,HttpServletRequest request,@CookieValue("JSESSIONID") String cookie) {
	    ReturnValue result=new ReturnValue();
	    
    try {
      User userfind = userServer.login(username, password);
      if(userfind!=null){
    	  //登录成功
    	  request.getSession().setAttribute("user", userfind);
          result.setReturn_type("success");
          result.setData(cookie);
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
  
  /**
   * /update  --> Update the email and the name for the user in the database 
   * having the passed id.
   * 
   * @param id The id for the user to update.
   * @param email The new email.
   * @param name The new name.
   * @return A string describing if the user is succesfully updated or not.
   */
//  @RequestMapping("/update")
//  @ResponseBody
//  public String updateUser(long id, String email, String name) {
//    try {
//      User user = userDao.findOne(id);
//      user.setEmail(email);
//      user.setName(name);
//      userDao.save(user);
//    }
//    catch (Exception ex) {
//      return "Error updating the user: " + ex.toString();
//    }
//    return "User succesfully updated!";
//  }

  // ------------------------
  // PRIVATE FIELDS
  // ------------------------

//  @Autowired
//  private UserDao userDao;
  @Autowired
  private UserServer userServer;
  
} // class UserController
