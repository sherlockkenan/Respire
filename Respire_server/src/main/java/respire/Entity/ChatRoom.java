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
	
	@NotNull
	private String title;
	
	public ChatRoom(int cityid, String roomid, String title){
		super();
		this.cityid = cityid;
		this.roomid = roomid;
		this.title = title;
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
	
	public void setTitle(String title){
		this.title = title;
	}
	
	public String getTitle(){
		return title;
	}
}
