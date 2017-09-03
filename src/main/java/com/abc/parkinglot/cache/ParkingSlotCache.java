package com.abc.parkinglot.cache;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.abc.parkinglot.model.ParkingSlot;
import com.abc.parkinglot.model.Vehicle;

public class ParkingSlotCache {
	
	private Map<String, List<ParkingSlot>> colorMap = new HashMap<String, List<ParkingSlot>>();
	private Map<String, ParkingSlot> regNumMap = new HashMap<String, ParkingSlot>();
	private Map<Integer, ParkingSlot> slotMap = new HashMap<Integer, ParkingSlot>();
	
	/**
	 * Singleton helper
	 */
	private static class SingletonHelper {
		private static final ParkingSlotCache INSTANCE = new ParkingSlotCache();
	}

	/**
	 * Returns the instance
	 */
	public static ParkingSlotCache getInstance() {
		return ParkingSlotCache.SingletonHelper.INSTANCE;
	}

	public List<ParkingSlot> getByColor(String color) {
		return colorMap.get(color);
	}
	
	public ParkingSlot getByRegNum(String regNum) {
		return regNumMap.get(regNum);
	}
	
	public void add(ParkingSlot parkingSlot) {
		Vehicle vehicle = parkingSlot.getVehicle();
		
		List<ParkingSlot> slots = colorMap.get(vehicle.getColor());
		if (slots == null) {
			slots = new ArrayList<ParkingSlot>();
			colorMap.put(vehicle.getColor(), slots);
		}
		slots.add(parkingSlot);
		
		regNumMap.put(vehicle.getRegistrationNumber(), parkingSlot);
		slotMap.put(parkingSlot.getSlot(), parkingSlot);
	}
	
	public void remove(int slot) {
		ParkingSlot parkingSlot = slotMap.get(slot);
		Vehicle vehicle = parkingSlot.getVehicle();

		regNumMap.remove(vehicle.getRegistrationNumber());
		slotMap.remove(slot);
		
		List<ParkingSlot> slots = colorMap.get(vehicle.getColor());
		
		boolean found = false;
		int index = 0;
		for (ParkingSlot tempSlot : slots) {
			if (slot == tempSlot.getSlot()) {
				found = true;
				break;
			}
			index++;
		}
		
		slots.remove(index);
	}
	
	public void reset() {
		colorMap = new HashMap<String, List<ParkingSlot>>();
		regNumMap = new HashMap<String, ParkingSlot>();
		slotMap = new HashMap<Integer, ParkingSlot>();
	}
}
