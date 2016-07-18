package respire.Dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import respire.Entity.Click;
import respire.Entity.Item;

@Repository
public interface ItemDao extends CrudRepository<Item, String> {
	public Item findByItemid(String itemid);

}
