package com.abc.parkinglot.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Set;
import java.util.TreeSet;

import com.abc.parkinglot.cache.ParkingSlotCache;
import com.abc.parkinglot.exception.ParkingLotException;
import com.abc.parkinglot.model.ParkingSlot;
import com.abc.parkinglot.model.Vehicle;

import static com.abc.parkinglot.exception.ExceptionMessages.*;

public class ParkingLotImpl {
	
	private ParkingSlotCache cache = ParkingSlotCache.getInstance();

	private int totalSlots;
	private Set<ParkingSlot> slots;
	private Queue<Integer> queue;

	/**
	 * Singleton helper
	 */
	private static class SingletonHelper {
		private static final ParkingLotImpl INSTANCE = new ParkingLotImpl();
	}

	/**
	 * Returns the instance
	 */
	public static ParkingLotImpl getInstance() {
		return ParkingLotImpl.SingletonHelper.INSTANCE;
	}

	public void initialize(int totalSlots) throws ParkingLotException {
		if (slots != null) {
			throw new ParkingLotException(ERROR_PARKING_LOT_ALREADY_INITIALIZED);
		}
		
		this.totalSlots = totalSlots;
		slots = new TreeSet<ParkingSlot>();
		queue = new PriorityQueue<Integer>(totalSlots);
		// initialize the queue with all the open slots
		for (int i = 1; i <= totalSlots; i++) {
			queue.offer(i);
		}
		
		System.out.println("Created parking lot with " + totalSlots + " slots");
	}

	public void park(String registrationNumber, String color) throws ParkingLotException {
		// To avoid creating Vehicle object
		verifySlotAvailable();

		park(new Vehicle(registrationNumber, color));
	}

	public void park(Vehicle vehicle) throws ParkingLotException {
		verifySlotAvailable();

		// Retrieve the next open slot that is near the entrance
		int slot = queue.poll();

		// add the parking slot
		ParkingSlot parkingSlot = new ParkingSlot(slot);
		parkingSlot.setVehicle(vehicle);
		slots.add(parkingSlot);
		
		//add it to cache
		cache.add(parkingSlot);

		System.out.println("Allocated Slot number: " + slot);
	}

	public void leave(int slot) throws ParkingLotException {
		ParkingSlot parkingSlot = new ParkingSlot(slot);
		if (slots.contains(parkingSlot)) {
			//Remove it from cache
			cache.remove(slot);
			
			slots.remove(parkingSlot);
			queue.add(slot);
			System.out.println("Slot number " + slot + " is free");
		} else {
			throw new ParkingLotException(ERROR_PARKING_SLOT_ALREADY_AVAILABLE);
		}
	}

	public int status() {
		if (slots.isEmpty()) {
			System.out.println("Parking Lot is empty");
			return 0;
		}

		System.out.println("Slot No.  Registration No. Status");
		for (ParkingSlot parkingSlot : slots) {
			Vehicle vehicle = parkingSlot.getVehicle();
			System.out.println(
					parkingSlot.getSlot() + "   " + vehicle.getRegistrationNumber() + "   " + vehicle.getColor());
		}
		
		return slots.size();
	}
	
	public List<String> getRegistrationNumbersByColor(String color) throws ParkingLotException {
		List<ParkingSlot> slots = cache.getByColor(color);
		if (slots == null || slots.isEmpty()) {
			throw new ParkingLotException("Not Found.");
		}
		
		List<String> results = new ArrayList<String>();
		for (int i = 0; i < slots.size(); i++) {
			ParkingSlot slot = slots.get(i);
			results.add(slot.getVehicle().getRegistrationNumber());
		}
		
		return results;
	}
	
	public List<Integer> getSlotNumbersByColor(String color) throws ParkingLotException {
		List<ParkingSlot> slots = cache.getByColor(color);
		if (slots == null || slots.isEmpty()) {
			throw new ParkingLotException("Not Found.");
		}
		
		List<Integer> results = new ArrayList<Integer>();
		for (int i = 0; i < slots.size(); i++) {
			ParkingSlot slot = slots.get(i);
			results.add(slot.getSlot());
		}
		
		return results;
	}
	
	public int getSlotNumberByRegistrationNumber(String regNum) throws ParkingLotException {
		ParkingSlot parkingSlot = cache.getByRegNum(regNum);
		if (parkingSlot == null) {
			throw new ParkingLotException("Not Found.");
		}
		return parkingSlot.getSlot();
	}
	
	public void reset() {
		slots = null;
		queue = null;
		cache.reset();
	}

	private void verifySlotAvailable() throws ParkingLotException {
		if (queue.isEmpty()) {
			throw new ParkingLotException(ERROR_PARKING_LOT_FULL);
		}
	}
}
