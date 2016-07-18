package respire.Service;

import static org.junit.Assert.*;

import org.junit.Test;

import respire.Entity.Place;

public class UpdateAirServiceTest {

	@Test
	public void testSearch_for_updatePlace() {
		Place place=new Place();
		place.setLatitude(66.6);
		place.setLongitude(66.6);
		UpdateAirService updateAirService=new UpdateAirService();
		updateAirService.search_for_update(place);
		System.out.println(place.getCo2());
		fail("Not yet implemented"); // TODO
	}

}
