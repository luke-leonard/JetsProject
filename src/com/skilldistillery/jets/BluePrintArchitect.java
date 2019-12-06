package com.skilldistillery.jets;

import java.util.Scanner;

public class BluePrintArchitect {
	public JetBluePrint userBuildJet(Scanner input) {
		JetBluePrint jbp = new JetBluePrint();
		
		String type = "";
		boolean choiceMade = false;
		String userInput = "";
		System.out.println("Welcome to Blue Print Architect");
		while (!choiceMade) {
			System.out.println("What type of Jet would you like to make?");
			System.out.println("Options:");
			System.out.println("\tFighter(F)");
			System.out.println("\tCargo(C)");
			System.out.println("\tPlain Old Jet(J)");
			userInput = input.nextLine();
			if(userInput.equalsIgnoreCase("F")||userInput.equalsIgnoreCase("C")||userInput.equalsIgnoreCase("J")) {
				type = userInput.toUpperCase();
				choiceMade = true;
			}
		}
		choiceMade = false;
		
		System.out.print("Model:");
		userInput = input.nextLine();
		jbp.addChacteristic("Model", (type + "-" + userInput));

		double speed = 0;
		while (!choiceMade) {
			System.out.print("Speed:");
			userInput = input.nextLine();
			try {
				speed = Double.parseDouble(userInput);
				jbp.addChacteristic("Speed", speed);
				choiceMade = true;
			}catch(Exception e) {
				System.out.println("Invalid Type");
			}
		}
		choiceMade = false;
		
		int range = 0;
		while (!choiceMade) {
			System.out.print("Range:");
			userInput = input.nextLine();
			try {
				range = Integer.parseInt(userInput);
				jbp.addChacteristic("Range", range);
				choiceMade = true;
			}catch(Exception e) {
				System.out.println("Invalid Type");
			}
		}
		choiceMade = false;
		

		System.out.println("This will cost " + (long)(speed * range));
		jbp.addChacteristic("Price", (long)(speed * range));
		
		return jbp;
	}
}
