package com.abc.parkinglot.model;

public class ParkingSlot implements Comparable<ParkingSlot> {

	private int slot;
	private Vehicle vehicle;

	public ParkingSlot(int slot) {
		this.slot = slot;
	}

	public int getSlot() {
		return slot;
	}

	public Vehicle getVehicle() {
		return vehicle;
	}

	public void setVehicle(Vehicle vehicle) {
		this.vehicle = vehicle;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + slot;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ParkingSlot other = (ParkingSlot) obj;
		if (slot != other.slot)
			return false;
		return true;
	}

	@Override
	public int compareTo(ParkingSlot other) {
		return this.slot - other.slot;
	}
}
