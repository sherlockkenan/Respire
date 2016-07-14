package respire.Controller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Date;
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
import net.sf.json.JsonConfig;
import sun.misc.*;

import respire.Entity.Scenery;
import respire.Entity.Uploadfile;
import respire.Entity.User;
import respire.Result.ReturnValue;
import respire.Result.SceneryDataModel;
import respire.Service.SceneryService;
import respire.Utils.JsonDateValueProcessor;

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
	public ReturnValue getimage(HttpServletRequest request,@RequestBody Scenery scenery){
		ReturnValue result = new ReturnValue();
		try{
			
			 List<SceneryDataModel> sceneries=sceneryService.getimage(scenery.getLatitude(),scenery.getLongitude());
			 result.setReturn_type("success");
			 JsonConfig jsonConfig = new JsonConfig();  
			 jsonConfig.registerJsonValueProcessor(Date.class, new JsonDateValueProcessor());  
		     result.setData(JSONArray.fromObject(sceneries,jsonConfig));
			
			return result;
		}
		catch(Exception e){
			
		}
		return result;
	}

}
