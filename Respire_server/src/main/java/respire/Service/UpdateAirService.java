package respire.Service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import respire.Dao.DatanowDao;
import respire.Dao.SceneryDao;
import respire.Entity.Datanow;
import respire.Entity.Place;
import respire.Entity.Scenery;
import respire.Utils.DistanceCompu;

@Service
public class UpdateAirService {
	
	@Autowired
	private DatanowDao datanowDao;
	@Autowired
	private SceneryDao sceneryDao;
	
	public void search_for_update(Scenery scenery){
		double lat=scenery.getLatitude();
		double lng=scenery.getLongitude();
		List<Datanow>datanows=datanowDao.getair(lat,lng);
		if(datanows.size()!=0){
			double mindis=DistanceCompu.GetDistance(lat,lng, datanows.get(0).getLatitude(), datanows.get(0).getLongitude());
			int min=0;
			for(int i=1;i<datanows.size();i++){
				double temp=DistanceCompu.GetDistance(lat,lng, datanows.get(i).getLatitude(), datanows.get(i).getLongitude());
				if(temp>mindis){
					mindis=temp;
					min=i;
				}
			}
			
			scenery.setCo2(datanows.get(min).getCo2());
			scenery.setPm25(datanows.get(min).getPm25());
			scenery.setSo2(datanows.get(min).getSo2());
			sceneryDao.save(scenery);
		}
		
	}
	public void search_for_update(Place place){
		double lat=place.getLatitude();
		double lng=place.getLongitude();
		try{
		    List<Datanow>datanows=datanowDao.getair(lat,lng);
		
		if(datanows.size()!=0){
			double mindis=DistanceCompu.GetDistance(lat,lng, datanows.get(0).getLatitude(), datanows.get(0).getLongitude());
			int min=0;
			for(int i=1;i<datanows.size();i++){
				double temp=DistanceCompu.GetDistance(lat,lng, datanows.get(i).getLatitude(), datanows.get(i).getLongitude());
				if(temp>mindis){
					mindis=temp;
					min=i;
				}
			}
			
			place.setCo2(datanows.get(min).getCo2());
			place.setPm25(datanows.get(min).getPm25());
			place.setSo2(datanows.get(min).getSo2());
		}
		}catch (Exception e){
			System.out.print(e.toString());
		}
	}
	

}
