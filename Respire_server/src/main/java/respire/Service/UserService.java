package respire.Service;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import respire.Dao.DatanowDao;
import respire.Dao.UserDao;
import respire.Entity.Datanow;
import respire.Entity.User;
import respire.Utils.BMapPlace;

@Service
public class UserService {
    //atuowired the userdao
	@Autowired
	private UserDao userDao;
	@Autowired
	private DatanowDao datanowDao;

	
	//check login
     public User login(String username,String password){
    
		return userDao.findByUsernameAndPassword(username, password);
    	 
     }
     
     //user register
     public User register(User user){
    	 if(userDao.findByUsername(user.getUsername())!=null)
    	 {
    		 return null;
    	 }
    	 user.setUserid(UUID.randomUUID().toString());
 		 return userDao.save(user);
 		 	 
      }
     
     public void postdata(Datanow datanow){
 	     String tag=BMapPlace.gettag(datanow.getLatitude(), datanow.getLongitude());
 	     if(tag!=null){
 	    	 datanow.setTag(tag);
 	     }
  		 datanowDao.save(datanow);
  		 
      	 
       }
     


     
     //user register
     public User update(User user){
    	    
 		return userDao.save(user);
     	 
     }
     
     public User find(String id){
    	 User user = userDao.findOne(id);
    	 return user;
     }

}
