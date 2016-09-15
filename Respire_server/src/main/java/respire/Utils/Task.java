package respire.Utils;


import java.io.IOException;
import java.util.TimerTask;
/**
 * 执行内容
 * @author admin_Hzw
 *
 */
public class Task extends TimerTask {
	public void run() {
		Process proc1,proc2;
		try {
			proc1 = Runtime.getRuntime().exec("python  /home/administrator/respire/recommendations.py");
			proc1.waitFor(); 
			proc2 = Runtime.getRuntime().exec("python  /home/administrator/respire/recommendations2.py");
			proc2.waitFor(); 
		    //System.out.println("我有一头小毛驴");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}  
	  
	}
}