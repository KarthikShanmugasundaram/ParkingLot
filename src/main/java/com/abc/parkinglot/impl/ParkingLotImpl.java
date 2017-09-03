package com.abc.parkinglot.impl;

public class ParkingLotImpl {
	
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
}
