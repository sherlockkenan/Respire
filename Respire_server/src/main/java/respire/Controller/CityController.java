package respire.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import net.sf.json.JSONArray;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import respire.Dao.RecommandDao;
import respire.Entity.CityNode;
import respire.Entity.Recommand;
import respire.Result.ReturnValue;
import respire.Service.CityNodeService;


/**
 * A class to test interactions with the MySQL database using the UserDao class.
 *
 * @author respire
 */
@Controller
public class CityController {

  @RequestMapping("/getcitylist")
  @ResponseBody
  public ReturnValue getCityNodes(int fatherid) {
      ReturnValue result=new ReturnValue();
      Recommand recommand=new Recommand();
      recommand.setItemid1("1");
      recommandDao.save(recommand);
    try {
         List<CityNode> cityNodes = cityNodeServer.getCityNodes(fatherid);
         if(cityNodes.size()==0){
        	 CityNode cityNode = cityNodeServer.findByCityid(fatherid);
        	 result.setReturn_type("success");
             result.setData(JSONArray.fromObject(cityNode));
             return result;
         }
         
         result.setReturn_type("success");
         result.setData(JSONArray.fromObject(cityNodes));
         return result;
    }
    catch (Exception ex) {

         result.setReturn_type("fail");
         result.setData(ex.toString());
         return result;
    }

  }
  
  @Autowired
  private CityNodeService cityNodeServer;
  @Autowired
  private RecommandDao recommandDao;
}