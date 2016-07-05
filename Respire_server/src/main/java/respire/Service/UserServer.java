package respire.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import respire.Dao.DatanowDao;
import respire.Dao.UserDao;
import respire.Entity.Datanow;
import respire.Entity.User;

@Service
public class UserServer {
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
 		 return userDao.save(user);
 		 	 
      }
     
     public void postdata(Datanow datanow){
 	    
  		 datanowDao.save(datanow);
      	 
       }
     


     
     //user register
     public User update(User user){
    	    
 		return userDao.save(user);
     	 
     }
     
     public User find(long id){
    	 User user = userDao.findOne(id);
    	 return user;
     }

}