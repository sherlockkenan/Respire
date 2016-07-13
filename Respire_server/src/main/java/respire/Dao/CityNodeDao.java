package respire.Dao;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import respire.Entity.CityNode;

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
public interface CityNodeDao extends CrudRepository<CityNode, Integer> {


  public List<CityNode> findByFatherid(int fatherid);
  
  public CityNode findByCityid(int cityid);

} // class UserDao
