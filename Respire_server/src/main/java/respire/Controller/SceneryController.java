package respire.Controller;

import java.io.File;

import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import respire.Entity.Scenery;
import respire.Entity.User;
import respire.Result.ReturnValue;
import respire.Service.SceneryService;

@RestController
@RequestMapping("/scenery")
public class SceneryController {

	@Autowired
	SceneryService sceneryService;
	
	@RequestMapping("/uploadfile")
	 public ReturnValue uploadfile(HttpServletRequest request,@RequestParam MultipartFile upload,Scenery scenery) {
	      ReturnValue result=new ReturnValue();
	      try {
	    	   
                sceneryService.uploadfile(request, upload,scenery);			
				result.setReturn_type("success");
				result.setData("succeed to upload");
				return result;
				
			} catch (Exception e) {
				e.printStackTrace();
				result.setReturn_type("fail");
				result.setData(e.toString());
				return result;
			}     
	}
	
}
