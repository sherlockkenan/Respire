package respire.Controller;

import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import respire.Entity.Datanow;

import respire.Entity.User;
import respire.Entity.UserCity;
import respire.Result.ReturnValue;
import respire.Service.CityNodeService;
import respire.Service.UserService;

/**
 * A class to test interactions with the MySQL database using the UserDao class.
 *
 * @author respire
 */
@Controller
public class UserController {

	@RequestMapping("/register")
	@ResponseBody
	public ReturnValue register(@RequestBody User user) {
		ReturnValue result = new ReturnValue();
		try {

			User user1 = userServer.register(user);
			if (user1 == null) {
				// register fail
				result.setReturn_type("fail");
				result.setData("username have been used");
				return result;
			}
			result.setReturn_type("success");
			result.setData("success to register");
			return result;
		} catch (Exception ex) {

			result.setReturn_type("fail");
			result.setData(ex.toString());
			return result;
		}

	}

	@RequestMapping(value = "/login", method = RequestMethod.POST)
	@ResponseBody
	public ReturnValue login(@RequestBody User user, HttpServletRequest request) {
		ReturnValue result = new ReturnValue();

		try {
			User user1 = (User) request.getSession().getAttribute("user");
			System.out.println("username:" + user.getUsername() + "password:" + user.getPassword());
			User userfind = userServer.login(user.getUsername(), user.getPassword());
			String cookies = request.getHeader("Cookie");
			System.out.println("headercookie:" + cookies);
			String cookie = request.getRequestedSessionId();
			System.out.println("sessionid:" + cookie);
			if (user1 != null)
				System.out.println("sessionusername:" + user1.getUsername());

			if (userfind != null) {
				// 登录成
				System.out.println("success");
				request.getSession().setAttribute("user", userfind);

				request.getSession().getId();
				result.setReturn_type("success");
				HashMap<String, String> sessionid = new HashMap<>();
				User user2 = (User) request.getSession().getAttribute("user");
				sessionid.put("JSESSIONID", request.getSession().getId());
				System.out.println(request.getSession().getId());
				result.setData(request.getSession().getId());

				return result;
			} else {
				System.out.println("fail");
				System.out.println(request.getSession().getId());
				User user2 = (User) request.getSession().getAttribute("user");

				result.setReturn_type("fail");
				result.setData("fail to login");
				return result;
			}
		} catch (Exception ex) {

			result.setReturn_type("fail");
			result.setData(ex.toString());
			return result;
		}

	}

	@RequestMapping("/logout")
	@ResponseBody
	public ReturnValue logout(HttpServletRequest request) {
		ReturnValue result = new ReturnValue();
		try {
			request.getSession().removeAttribute("user");
			result.setReturn_type("success");
			result.setData("succeed to logout");
			return result;
		} catch (Exception ex) {
			result.setReturn_type("fail");
			result.setData(ex.toString());
			return result;
		}
	}

	@RequestMapping("/postdata")
	@ResponseBody
	public ReturnValue postdata(@RequestBody Datanow datanow, HttpServletRequest request) {
		ReturnValue result = new ReturnValue();

		User user = (User) request.getSession().getAttribute("user");
		if (user == null) {
			result.setReturn_type("fail");
			result.setData("user not register");
			return result;
		}
		try {
			datanow.setUserid(user.getUserid());
			datanow.setTime(new java.util.Date());
			userServer.postdata(datanow);
			result.setReturn_type("success");
			result.setData("succees to postdata");
			return result;
		} catch (Exception e) {
			result.setReturn_type("fail");
			result.setData(e);
			return result;
		}
	}

	@RequestMapping("/update")
	@ResponseBody
	public ReturnValue updateUser(@RequestBody User user, HttpServletRequest request) {
		ReturnValue result = new ReturnValue();
		User oldUser = (User) request.getSession().getAttribute("user");
		try {
			if (oldUser == null) {
				result.setReturn_type("fail");
				result.setData("Error updating the user: not login!");
				return result;
			}

//			if (oldUser.getUsername() != user.getUsername()) {
//				System.out.println(oldUser.getUsername());
//				System.out.println(user.getUsername());
//				result.setReturn_type("fail");
//				result.setData("Error updating the user: Userid or username is not correct.");
//				return result;
//			}

			oldUser.setEmail(user.getEmail());
			oldUser.setRole(user.getRole());
			oldUser.setCityid(user.getCityid());
			oldUser.setPassword(user.getPassword());
			oldUser.setPhone(user.getPhone());
			oldUser.setSex(user.getSex());
			userServer.update(oldUser);
			result.setReturn_type("success");
			result.setData("User succesfully updated.");
			return result;
		} catch (Exception ex) {
			result.setReturn_type("fail");
			result.setData("Error updating the user: " + ex.toString());
			return result;
		}
	}

	@RequestMapping("/getprofile")
	@ResponseBody
	public ReturnValue getproFile(HttpServletRequest request) {
		ReturnValue result = new ReturnValue();

		User user = (User) request.getSession().getAttribute("user");
		try {
			if (user == null) {
				result.setReturn_type("fail");
				result.setData("user not register");
				return result;
			}
			else {
				result.setReturn_type("success");
				
			    JSONObject jsonObject=JSONObject.fromObject(user);
			    UserCity userCity=cityNodeServer.getusercity(user.getCityid());
			    
			    jsonObject.put("city4", userCity.getCity4());
			    jsonObject.put("city3", userCity.getCity3());
			    jsonObject.put("city2", userCity.getCity2());
			    jsonObject.put("city1", userCity.getCity1());
			    result.setData(jsonObject);
				
				return result;
			}
		} catch (

		Exception ex) {
			result.setReturn_type("fail");
			result.setData("Error updating the user: " + ex.toString());
			return result;
		}
	}

	// ------------------------
	// PRIVATE FIELDS
	// ------------------------

	// @Autowired
	// private UserDao userDao;

	@Autowired
	private UserService userServer;
	
	@Autowired
	private CityNodeService cityNodeServer;

} // class UserController
