package com.abc.parkinglot;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class ParkingLotFileCommands extends ParkingLotCommands {
	
	public void execute(String filePath) {
		try {
			FileReader reader = new FileReader(filePath);
			BufferedReader br = new BufferedReader(reader);
		
			String input = br.readLine();
			while(input != null && !input.trim().isEmpty()) {
				processCommand(input);
				
				input = br.readLine();
			}			
		} catch (IOException ioe) {
			ioe.printStackTrace();
		}
	}

	public static void main(String args[]) {
		ParkingLotFileCommands commands = new ParkingLotFileCommands();
		commands.execute("/Users/kshanmugasundaram/projects/ParkingLot/file_inputs.txt");
	}
}
