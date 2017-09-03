package com.abc.parkinglot;

import java.util.Scanner;

public class ParkingLotCLICommands extends ParkingLotCommands {

	public void execute() {
		while(true) {
			System.out.println("\nInput:");
			Scanner scanner = new Scanner(System.in);
			String input = scanner.nextLine();
			
			System.out.println("\nOutput:");
			processCommand(input);			
		}
	}

	public static void main(String[] args) throws Exception {
		ParkingLotCLICommands cli = new ParkingLotCLICommands();
		cli.execute();
	}
}
