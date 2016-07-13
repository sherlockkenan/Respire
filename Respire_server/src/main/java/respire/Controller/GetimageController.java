package respire.Controller;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import javax.imageio.ImageIO;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/image")
public class GetimageController {
	
	@Value("${image.savepath}")
	private String savepath ;
	
	@RequestMapping(value="/{image}")  
	 public byte[] getimage(@PathVariable String image) throws IOException{
		 InputStream is = new FileInputStream(savepath+image+".jpg");

	        // Prepare buffered image.
	        BufferedImage img = ImageIO.read(is);
	        

	        // Create a byte array output stream.
	        ByteArrayOutputStream bao = new ByteArrayOutputStream();

	        // Write to output stream
	        ImageIO.write(img, "jpg", bao);
	        
            return bao.toByteArray();
	 }

}
