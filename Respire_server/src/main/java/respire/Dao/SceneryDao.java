package respire.Dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


import respire.Entity.Scenery;

@Repository 
public interface SceneryDao extends CrudRepository<Scenery,Long>{

}

