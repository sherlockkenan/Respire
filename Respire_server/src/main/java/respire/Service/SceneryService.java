package respire.Service;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import org.springframework.web.multipart.MultipartFile;

import respire.Dao.SceneryDao;
import respire.Entity.Scenery;
import respire.Entity.User;
import sun.misc.BASE64Decoder;

@Service
public class SceneryService {
	
	@Autowired
	SceneryDao sceneryDao;
	


	public void uploadfile(HttpServletRequest request,Scenery scenery) {
		try {
			
	    	
			String savefilename = makeFileName();// 得到保存在硬盘的文件名
			String savepath = request.getSession().getServletContext().getRealPath("/") + "image/";
			//

			// create file
			String filepath = savepath + savefilename;

			// 转存文件
			File file = new File(filepath);
			file.getParentFile().mkdirs();

			file.createNewFile();
			
			GenerateImage(scenery.getPhoto(), filepath);
			
			Scenery find=sceneryDao.findById(scenery.getId());
			if(find==null)
			{
			  scenery.setPhoto("/image/"+savefilename+";");
			  sceneryDao.save(scenery);
			}else{
				String image=find.getPhoto();
				scenery.setPhoto(image+"/image/"+savefilename+";");
				sceneryDao.save(scenery);			}
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

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
