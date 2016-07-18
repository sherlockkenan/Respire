package respire.Entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "item")
public class Item {
	    @Id
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
