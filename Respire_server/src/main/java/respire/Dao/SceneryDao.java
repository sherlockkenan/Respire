package respire.Dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


import respire.Entity.Scenery;

@Repository 
public interface SceneryDao extends CrudRepository<Scenery,Long>{
	@Query("select s from Scenery s order by time desc")
      public List<Scenery> findAll();
}

