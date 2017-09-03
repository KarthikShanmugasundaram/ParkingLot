package com.abc.parkinglot;

import java.util.List;
import java.util.Scanner;
import java.util.StringTokenizer;

import com.abc.parkinglot.exception.ParkingLotException;
import com.abc.parkinglot.impl.ParkingLotImpl;

public abstract class ParkingLotCommands {
	
	private static final String STR_SPACE = " ";
	private static final String CMD_CREATE_PARKING_LOT = "create_parking_lot";
	private static final String CMD_PARK = "park";
	private static final String CMD_LEAVE = "leave";
	private static final String CMD_STATUS = "status";
	private static final String CMD_REG_NUM_BY_COLOR = "registration_numbers_for_cars_with_colour";
	private static final String CMD_SLOT_NUM_BY_COLOR = "slot_numbers_for_cars_with_colour";
	private static final String CMD_SLOT_NUM_BY_REG_NUM = "slot_number_for_registration_number";
	
	protected void processCommand(String input) {
		StringTokenizer tokenizer = new StringTokenizer(input, STR_SPACE);
		String token = tokenizer.nextToken();
		
		switch(token) {
			case CMD_CREATE_PARKING_LOT: createParkingLot(tokenizer);
			break;
			
			case CMD_PARK: park(tokenizer);
	         break;

			case CMD_LEAVE: leave(tokenizer);
	         break;

			case CMD_STATUS: status();
	         break;

			case CMD_REG_NUM_BY_COLOR: getRegNumsByColor(tokenizer);
	         break;

			case CMD_SLOT_NUM_BY_COLOR: getSlotNumsByColor(tokenizer);
	         break;
			
			case CMD_SLOT_NUM_BY_REG_NUM: getSlotNumByRegNum(tokenizer);
	         break;
		}		
	}
	
	private void createParkingLot(StringTokenizer tokenizer) {
		if (!tokenizer.hasMoreTokens()) {
			System.out.println("Invalid Input.\n");
		}
		String slot = tokenizer.nextToken();
		
		ParkingLotImpl impl = ParkingLotImpl.getInstance();
		try {
			//System.out.println("\nOutput:");
			impl.initialize(new Integer(slot));			
		} catch (ParkingLotException ple) {
			System.out.println(ple.getMessage());
		}
	}
	
	private void park(StringTokenizer tokenizer) {
		if (!tokenizer.hasMoreTokens()) {
			System.out.println("Invalid Input.\n");
		}
		String regNum = tokenizer.nextToken();
		
		if (!tokenizer.hasMoreTokens()) {
			System.out.println("Invalid Input.\n");
		}
		String color = tokenizer.nextToken();
		
		ParkingLotImpl impl = ParkingLotImpl.getInstance();
		try {
			//System.out.println("\nOutput:");
			impl.park(regNum, color);		
		} catch (ParkingLotException ple) {
			System.out.println(ple.getMessage());
		}
	}
	
	private void leave(StringTokenizer tokenizer) {
		if (!tokenizer.hasMoreTokens()) {
			System.out.println("Invalid Input.\n");
		}
		String slot = tokenizer.nextToken();
		
		ParkingLotImpl impl = ParkingLotImpl.getInstance();
		try {
			//System.out.println("\nOutput:");
			impl.leave(new Integer(slot));		
		} catch (ParkingLotException ple) {
			System.out.println(ple.getMessage());
		}
	}
	
	private void status() {
		ParkingLotImpl impl = ParkingLotImpl.getInstance();
		//System.out.println("\nOutput:");
		impl.status();
	}
	
	private void getRegNumsByColor(StringTokenizer tokenizer) {
		if (!tokenizer.hasMoreTokens()) {
			System.out.println("Invalid Input.\n");
		}
		String color = tokenizer.nextToken();
		
		//System.out.println("\nOutput:\n");
		ParkingLotImpl impl = ParkingLotImpl.getInstance();
		
		try {
			List<String> regNums = impl.getRegistrationNumbersByColor(color);
			for(int i = 0; i < regNums.size(); i++) {
				System.out.print(regNums.get(i));
				if (i < (regNums.size() - 1)) {
					System.out.print(", ");
				}
			}
		} catch (ParkingLotException ple) {
			System.out.println(ple.getMessage());
		}
		System.out.println("");
	}

	private void getSlotNumsByColor(StringTokenizer tokenizer) {
		if (!tokenizer.hasMoreTokens()) {
			System.out.println("Invalid Input.\n");
		}
		String color = tokenizer.nextToken();
		
		//System.out.println("\nOutput:\n");
		ParkingLotImpl impl = ParkingLotImpl.getInstance();
		try {
			List<Integer> slotNums = impl.getSlotNumbersByColor(color);
			for(int i = 0; i < slotNums.size(); i++) {
				System.out.print(slotNums.get(i));
				if (i < (slotNums.size() - 1)) {
					System.out.print(", ");
				}
			}			
		} catch (ParkingLotException ple) {
			System.out.println(ple.getMessage());
		}
	}

	private void getSlotNumByRegNum(StringTokenizer tokenizer) {
		if (!tokenizer.hasMoreTokens()) {
			System.out.println("Invalid Input.\n");
		}
		String regNum = tokenizer.nextToken();
		
		ParkingLotImpl impl = ParkingLotImpl.getInstance();
		//System.out.println("\nOutput:\n");
		try {
			int slot = impl.getSlotNumberByRegistrationNumber(regNum);
			System.out.println(slot);
		} catch (ParkingLotException ple) {
			System.out.println(ple.getMessage());
		}
	}

}
