package respire.Controller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServlet;


import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

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
	 public ReturnValue uploadfile(HttpServletRequest request,HttpServletResponse response)  {
		
//		 MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;   
//	        // 获得文件：   
//	      MultipartFile upload= multipartRequest.getFile("upload");
//          Iterator<String> name=multipartRequest.getFileNames();
//		  Scenery scenery=null;
	      ReturnValue result=new ReturnValue();
//	      try {
//	    	   
//                sceneryService.uploadfile(request, upload,scenery);			
//				result.setReturn_type("success");
//				result.setData("succeed to upload");
//				return result;
//				
//			} catch (Exception e) {
//				e.printStackTrace();
//				result.setReturn_type("fail");
//				result.setData(e.toString());
//				return result;
//			}     
//	}
		String fileName = null;
		File  file = null;
		// Create a factory for disk-based file items
		DiskFileItemFactory factory = new DiskFileItemFactory();

		// FileCleaningTracker fileCleaningTracker =
		// FileCleanerCleanup.getFileCleaningTracker(getServletContext());
		// factory.setFileCleaningTracker(fileCleaningTracker);

		// Set factory constraints
		//´óÓÚ500KÏÈ´æ·ÅÔÚÁÙÊ±ÎÄ¼þ¼ÐÏÂ
		factory.setSizeThreshold(1024 * 500);
		File tempDirectory = new File("f:\\files");
		factory.setRepository(tempDirectory);

		// Create a new file upload handler
		ServletFileUpload upload = new ServletFileUpload(factory);

		// Set overall request size constraint
		//×î´óÒ»´ÎÉÏ´«20M
		upload.setSizeMax(1024 * 1024 * 20);

		// Parse the request
		try {
			List<FileItem> items = upload.parseRequest(request);
			int i=0;
			// 2. ±éÀú items:
			for (FileItem item : items) {
				// ÈôÊÇÒ»¸öÒ»°ãµÄ±íµ¥Óò, ´òÓ¡ÐÅÏ¢
				i++;
				if (item.isFormField()) {
					String name = item.getFieldName();
					String value = item.getString();
					System.out.println(name + ": " + value);
				}
				else {
					String fieldName = item.getFieldName();
					fileName = item.getName();
					String contentType = item.getContentType();
					long sizeInBytes = item.getSize();

					System.out.println("fieldName:" + fieldName);
					System.out.println("fileName:" + fileName);
					System.out.println("contentType:" + contentType);
					System.out.println("sizeInBytes:" + sizeInBytes);

					//InputStream in = item.getInputStream();
					byte[] buffer = new byte[1024];
					int len = 0;
//					String path = getServletContext().getRealPath(
//							"/WEB-INF/image");
					String path = "f:\\files\\";
					file = new File(path, fileName);
					System.out.println("file:"+file.getName());
					//OutputStream out = new FileOutputStream(file);

//					while ((len = in.read(buffer)) != -1) {
//						out.write(buffer, 0, len);
//					}
					//out.close();
					//in.close();
					response.setContentType("text/html;charset=	UTF-8");
					//ServletOutputStream os = response.getOutputStream();
					//String result = ""+i+"success"; 
					
					//os.write(result.getBytes());
				}
			}

		} catch (FileUploadException e) {
			e.printStackTrace();
		}
		return result;
	}
	
}
