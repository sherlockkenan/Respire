package respire.Service;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import org.springframework.web.multipart.MultipartFile;

import net.sf.json.JSONArray;
import respire.Dao.SceneryDao;
import respire.Entity.Scenery;
import respire.Entity.User;
import sun.misc.BASE64Decoder;

@Service
public class SceneryService {
	
	@Autowired
	SceneryDao sceneryDao;
	


	public void uploadfile(HttpServletRequest request,Scenery scenery) {
		String savepath = "f://files/image/";
		//

		// create file
		String filepath =null;
		String photo ="";


		JSONArray jsonArray=JSONArray.fromObject(scenery.getPhoto());
		
		for(int i=0;i<jsonArray.size();i++){
			String savefilename = makeFileName();// 得到保存在硬盘的文件名
			filepath=savepath+savefilename;
		    GenerateImage(jsonArray.getString(i),filepath);
		    photo=photo+"/image/"+savefilename+";";
			
		  
		}
		  scenery.setId(UUID.randomUUID().toString());
		  scenery.setTime(new Date());
		  scenery.setPhoto(photo);
		  
		  User user=(User) request.getSession().getAttribute("user");
		  if(user!=null){
			  scenery.setUserid(user.getUserid());
		  }
		  sceneryDao.save(scenery);

	}
	
	
	public static boolean GenerateImage(String imgStr, String imgFilePath) {// 对字节数组字符串进行Base64解码并生成图片
		if (imgStr == null) // 图像数据为空
			return false;
		BASE64Decoder decoder = new BASE64Decoder();
		try {
			// Base64解码
			byte[] bytes = decoder.decodeBuffer(imgStr);
			for (int i = 0; i < bytes.length; ++i) {
				if (bytes[i] < 0) {// 调整异常数据
					bytes[i] += 256;
				}
			}
			// 生成jpeg图片
			OutputStream out = new FileOutputStream(imgFilePath);
			out.write(bytes);
			out.flush();
			out.close();
			return true;
		} catch (Exception e) {
			return false;
		}
	}
	public String makeFileName() {
	
		return UUID.randomUUID().toString() + ".jpg" ;
	}
	
	
	public List<Scenery> getimage(){
		return sceneryDao.findAll();
	}
}
