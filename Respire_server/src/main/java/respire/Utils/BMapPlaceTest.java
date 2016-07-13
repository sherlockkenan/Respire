package respire.Utils;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;

import respire.Entity.Geocoding;
import respire.Entity.Place;

public class BMapPlaceTest {

	@Test
	public void test() {
		List<Place> places=BMapPlace.getplacebyloc(116.32298699999993, 39.98342407140365,"银行");
		System.out.println(places);
		fail("Not yet implemented");
	}

}
