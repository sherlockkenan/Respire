package respire.Service;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import org.springframework.web.multipart.MultipartFile;

import respire.Dao.SceneryDao;
import respire.Entity.Scenery;
import respire.Entity.User;

@Service
public class SceneryService {
	
	@Autowired
	SceneryDao sceneryDao;
	


	public void uploadfile(HttpServletRequest request, MultipartFile upload,Scenery scenery) {
		try {
			
	    	
			String savefilename = makeFileName(upload.getOriginalFilename());// 得到保存在硬盘的文件名
			String savepath = request.getSession().getServletContext().getRealPath("/") + "image/";
			//

			// create file
			String filepath = savepath + savefilename;

			// 转存文件
			File file = new File(filepath);
			file.getParentFile().mkdirs();

			file.createNewFile();
			upload.transferTo(file);
			
			//设置scenery
			//User user=(User) request.getSession().getAttribute("user");
	    	//scenery.setUserid(user.getUserid());
			scenery.setImage_path("image/"+savefilename);
			sceneryDao.save(scenery);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public String makeFileName(String filename) {
		String ext = filename.substring(filename.lastIndexOf(".") + 1);
		return UUID.randomUUID().toString() + "." + ext;
	}
	
	
	public List<Scenery> getimage(){
		return sceneryDao.findAll();
	}
}
