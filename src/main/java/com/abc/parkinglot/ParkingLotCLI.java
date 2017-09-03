package com.abc.parkinglot;

import com.abc.parkinglot.impl.ParkingLotImpl;

public class ParkingLotCLI {

	public static void main(String[] args) throws Exception {
		ParkingLotImpl impl = ParkingLotImpl.getInstance();
		impl.initialize(4);
		
		impl.park("KA-01-HH-1234", "White");
		impl.park("KA-01-HH-1235", "White");
		impl.park("KA-01-HH-1236", "White");
		impl.park("KA-01-HH-1237", "White");
		
		impl.leave(3);
		
		impl.status();
	}
}
