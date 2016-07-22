package respire.Dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


import respire.Entity.Scenery;

@Repository 
public interface SceneryDao extends CrudRepository<Scenery,String>{
	@Query("select s from Scenery s order by time desc")
      public List<Scenery> findAll();
	
	public List<Scenery> findByUid(String uid);
	public Scenery findById(String id);
	
	public List<Scenery> findByTag(String tag);
}

