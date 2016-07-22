package respire.Dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import respire.Entity.Recommand2;

@Repository 
public interface Recommand2Dao extends CrudRepository<Recommand2,Long>{

	@Query("select r from Recommand r where TO_DAYS(NOW()) - TO_DAYS(time) = 7")
	public List<Recommand2> getrecomad( String userid);

}
