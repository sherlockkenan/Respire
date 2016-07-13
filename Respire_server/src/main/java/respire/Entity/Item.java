package respire.Entity;

import javax.persistence.Entity;
import javax.persistence.Table;


public class Item {
	    String itemid;
	    String recomid;
	    int time;
		public String getItemid() {
			return itemid;
		}
		public void setItemid(String itemid) {
			this.itemid = itemid;
		}
		public String getRecomid() {
			return recomid;
		}
		public void setRecomid(String recomid) {
			this.recomid = recomid;
		}
		public int getTime() {
			return time;
		}
		public void setTime(int time) {
			this.time = time;
		}
		
}
