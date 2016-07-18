package respire.Dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import respire.Entity.ChatRoom;

@Repository 
public interface ChatRoomDao extends CrudRepository<ChatRoom, Integer>{
	public ChatRoom findByCityid(int cityid);
}
