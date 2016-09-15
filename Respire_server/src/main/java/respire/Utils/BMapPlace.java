package respire.Utils;

import java.util.ArrayList;
import java.util.List;

import javax.management.loading.PrivateClassLoader;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import respire.Entity.Geocoding;
import respire.Entity.Place;

public class BMapPlace {
	
	  static private String ak="yn3IeGY1mBSnpjnDzRjGxbeKD2IzPY8s";
	  
	  
      static public Geocoding transformlocation(double latitude,double longitude){
    	  Geocoding geocoding=new Geocoding();
    	  
    	  String url="http://api.map.baidu.com/geocoder/v2/";
    	  String param="ak="+ak+"&location="+latitude+","+longitude+"&output=json&pois=1";
    	  
    	  String res=Httprequest.sendGet(url, param);
    	  
    	  JSONObject jsonObject=JSONObject.fromObject(res);

    	  JSONObject result=jsonObject.getJSONObject("result");
    	  
    	  String name=result.getString("formatted_address");
    	  String uid=null;
    	  
    	  JSONArray pois=result.getJSONArray("pois");
    	  if(pois.size()!=0){
    		  name=pois.getJSONObject(0).getString("name");
    		  uid=pois.getJSONObject(0).getString("uid");
    		  
    	  }
    	  geocoding.setName(name);
    	  geocoding.setUid(uid);
    	  return geocoding;
      }
      static public String gettag(double latitude,double longitude){
    	 
    	  
    	  String url="http://api.map.baidu.com/geocoder/v2/";
    	  String param="ak="+ak+"&location="+latitude+","+longitude+"&output=json&pois=1";
    	  
    	  String res=Httprequest.sendGet(url, param);
    	  
    	  JSONObject jsonObject=JSONObject.fromObject(res);

    	  JSONObject result=jsonObject.getJSONObject("result");
    	  

    	  
    	  JSONArray pois=result.getJSONArray("pois");
    	  if(pois.size()!=0){
    		  String tag=pois.getJSONObject(0).getString("poiType");
    		  if(tag.equals("旅游景点")){
    			  return "scenery";
    			  
    		  }else if(tag.equals("运动健身"))
    		  {
    			  return "sport";
    		  }
    	  }
    	  return null;

      }
      
      static public List<Place> getplacebyloc(double latitude,double longitude ,String query){
    	  List<Place> places=new ArrayList<Place>();
    	  
    	  String url="http://api.map.baidu.com/place/v2/search";
    	  String param="ak="+ak+"&location="+latitude+","+longitude+"&radius=20000&output=json"+"&query="+query;
    	  
    	  String res=Httprequest.sendGet(url, param);
    	  
    	  JSONObject jsonObject=JSONObject.fromObject(res);

    	  JSONArray results=jsonObject.getJSONArray("results");
    	  String name=null;
    	  String address=null;
    	  String uid=null;
    	  double lat;
    	  double lng;
    	  double distance;
    	 
    	  for(int i=0;i<results.size();i++){
    		  
    		  name=results.getJSONObject(i).getString("name");
    		  address=results.getJSONObject(i).getString("address");
    		  uid=results.getJSONObject(i).getString("uid");
    		  lat=results.getJSONObject(i).getJSONObject("location").getDouble("lat");
    		  lng=results.getJSONObject(i).getJSONObject("location").getDouble("lng");
    		  distance= DistanceCompu.GetDistance(lat, lng, latitude, longitude);
    		  Place place=new Place(name,address,uid,lat,lng,distance);
    	      places.add(place);
    	  }
    	  
    	  return places;
      }
}
