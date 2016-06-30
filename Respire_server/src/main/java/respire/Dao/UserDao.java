package respire.Dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import respire.Entity.User;

/**
 * A DAO for the entity User is simply created by extending the CrudRepository
 * interface provided by spring. The following methods are some of the ones
 * available from such interface: save, delete, deleteAll, findOne and findAll.
 * The magic is that such methods must not be implemented, and moreover it is
 * possible create new query methods working only by defining their signature!
 * 
 * @author respire
 */
//@Transactional
@Repository 
public interface UserDao extends CrudRepository<User, Long> {


  public User findByUsernameAndPassword(String username,String password);
  
 

} // class UserDao
