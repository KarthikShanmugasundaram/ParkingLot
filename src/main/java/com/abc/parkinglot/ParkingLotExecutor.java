package com.abc.parkinglot;

public class ParkingLotExecutor {

	public static void main(String[] args) {
		if (args != null && args.length > 0) {
			ParkingLotFileCommands commands = new ParkingLotFileCommands();
			commands.execute(args[0]);
		} else {
			ParkingLotCLICommands commands = new ParkingLotCLICommands();
			commands.execute();
		}
	}
}
