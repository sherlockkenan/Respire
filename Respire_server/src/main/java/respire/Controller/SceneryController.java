package respire.Controller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServlet;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.hibernate.loader.custom.Return;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import net.sf.json.JSONArray;
import sun.misc.*;

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
	public ReturnValue uploadfile(HttpServletRequest request, @RequestBody Scenery scenery) {

	
		ReturnValue result = new ReturnValue();
		
		
		try {
			  sceneryService.uploadfile(request, scenery);
			  
                 System.out.println("success");
				//System.out.println("Key = " + entry.getKey() + ", Value = " + entry.getValue());
				String Path= request.getSession().getServletContext().getRealPath("/")+"3.jpg";
				
				 
			
			  result.setReturn_type("success");
		      result.setData("success to upload");
			  return result;
		} catch (Exception e) {
			e.printStackTrace();
			 result.setReturn_type("fail");
			 result.setData(e.toString());
			 return result;
		}

	}

	
	
	@RequestMapping("/getimage")
	public ReturnValue getimage(HttpServletRequest request){
		ReturnValue result = new ReturnValue();
		try{
			
			List<Scenery> scenery=sceneryService.getimage();
			 result.setReturn_type("success");
		      result.setData(JSONArray.fromObject(scenery));
			
			return result;
		}
		catch(Exception e){
			
		}
		return result;
	}

}
