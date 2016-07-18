package respire.Dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import respire.Entity.Click;
import respire.Entity.Datanow;
import respire.Entity.DatanowPK;

@Repository 
public interface ClickDao extends CrudRepository<Click, String>{

}
