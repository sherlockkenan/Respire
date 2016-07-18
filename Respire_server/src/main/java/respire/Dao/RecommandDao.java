package respire.Dao;

import org.springframework.data.repository.CrudRepository;

import org.springframework.stereotype.Repository;

import respire.Entity.Datanow;
import respire.Entity.DatanowPK;
import respire.Entity.Recommand;
@Repository 
public interface RecommandDao extends CrudRepository<Recommand,Long> {
	public Recommand findByUserid(String userid);

}
