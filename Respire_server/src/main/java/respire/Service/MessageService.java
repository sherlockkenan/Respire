package respire.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.sf.json.JSONString;
import net.sf.json.JSONObject;
import respire.Dao.ChatRoomDao;
import respire.Entity.ChatRoom;
import respire.Dao.CityNodeDao;
import respire.Entity.CityNode;
import respire.Utils.Httprequest;

@Service
public class MessageService {
	@Autowired
	private ChatRoomDao chatRoomDao;
	@Autowired
	private CityNodeDao cityNodeDao;
	
	public ChatRoom findByCityid(int cityid){
		ChatRoom room = chatRoomDao.findByCityid(cityid);
		if(room!=null){
			return room;
		}else{
			CityNode cityNode = cityNodeDao.findByCityid(cityid);
			String result = Httprequest.sendPost_LeanCloud("https://api.leancloud.cn/1.1/classes/_Conversation", "{\"name\": \""+cityNode.getName()+"\",\"tr\": true}");
			JSONObject json = JSONObject.fromObject(result);
			String roomid =  json.getString("objectId");
			if(roomid!=null){
				room = new ChatRoom(cityid,roomid,cityNode.getName());
				chatRoomDao.save(room);
				return room;
			}else{
				return null;
			}
		}
	}
	
}
