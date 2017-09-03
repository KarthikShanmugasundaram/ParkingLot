package com.abc.parkinglot.test;

import java.util.List;

import org.junit.Assert;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import com.abc.parkinglot.cache.ParkingSlotCache;
import com.abc.parkinglot.model.ParkingSlot;
import com.abc.parkinglot.model.Vehicle;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class ParkingSlotCacheTest {

	@Test
	public void test00AddParkingSlot() {
		ParkingSlotCache cache = ParkingSlotCache.getInstance();
		Assert.assertNotNull(cache);
		
		ParkingSlot parkingSlot = new ParkingSlot(10);
		Vehicle vehicle = new Vehicle("KA-01-HH- 3141", "Black");
		parkingSlot.setVehicle(vehicle);
		
		cache.add(parkingSlot);
		
		List<ParkingSlot> colorSlots = cache.getByColor("Black");
		Assert.assertEquals(colorSlots.size(), 1);
		
		ParkingSlot regNumSlot = cache.getByRegNum("KA-01-HH- 3141");
		Assert.assertNotNull(regNumSlot);
	}
	
	@Test
	public void test01RemoveParkingSlot() {
		ParkingSlotCache cache = ParkingSlotCache.getInstance();
		Assert.assertNotNull(cache);
		
		ParkingSlot parkingSlot = new ParkingSlot(10);
		Vehicle vehicle = new Vehicle("KA-01-HH- 2701", "Blue");
		parkingSlot.setVehicle(vehicle);
		
		cache.add(parkingSlot);
		cache.remove(10);
		
		List<ParkingSlot> colorSlots = cache.getByColor("Blue");
		Assert.assertEquals(colorSlots.size(), 0);
		
		ParkingSlot regNumSlot = cache.getByRegNum("KA-01-HH- 2701");
		Assert.assertNull(regNumSlot);
	}
}
