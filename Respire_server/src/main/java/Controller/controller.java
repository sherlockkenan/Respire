package Controller;


import org.springframework.stereotype.Controller;
  
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

  
/** 
 * <p>User: Zhang Kaitao 
 * <p>Date: 13-12-22 
 * <p>Version: 1.0 
 */  
//@EnableAutoConfiguration  
@Controller  
@RequestMapping("/")  
public class controller {  
  
    @ResponseBody
    public String view() {  
    	return "hello world";
    }  
  
    //public static void main(String[] args) {  
    //    SpringApplication.run(UserController.class);  
    //}  
  
}  
