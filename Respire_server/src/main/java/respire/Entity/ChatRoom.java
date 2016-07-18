package respire.Entity;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.persistence.Id;

@Entity
@Table(name = "chatroom")
public class ChatRoom {
	@Id
	private int cityid;
	
	@NotNull
	private String roomid;
	
	public ChatRoom(int cityid, String roomid){
		super();
		this.cityid = cityid;
		this.roomid = roomid;
	}
	
	public ChatRoom(){
		super();
	}
	
	public int getCityid(){
		return cityid;
	}
	
	public void setCityid(int cityid){
		this.cityid = cityid;
	}
	
	public String getRoomid(){
		return roomid;
	}
	
	public void setRoomid(String roomid){
		this.roomid = roomid;
	}
}
