package com.skilldistillery.jets;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class JetFactory {
	private static int NextSerialNumber = 0;
	
	public List<Jet> buildJets(JetBluePrint bluePrint,int amount){
		List<Jet> jets = new ArrayList<>();
		for (int i = 0; i < amount; i++) {
			jets.add(buildJet(bluePrint));
		}
		return jets;
	}

	public Jet buildJet(JetBluePrint bluePrint) {
		Map<String, String> Characteristics = bluePrint.getJetCharacteristics();
		String model;
		double speed;
		int range;
		long price;
		Pilot pilot;
		int serialNumber;

		if (Characteristics.containsKey("Model")) {
			model = Characteristics.get("Model");
		} else {
			System.err.println("Incorrect BluePrint");
			return null;
		}
		if (Characteristics.containsKey("Speed")) {
			speed = Double.parseDouble(Characteristics.get("Speed"));
		} else {
			System.err.println("Incorrect BluePrint");
			return null;
		}
		if (Characteristics.containsKey("Range")) {
			range = Integer.parseInt(Characteristics.get("Range"));
		} else {
			System.err.println("Incorrect BluePrint");
			return null;
		}
		if (Characteristics.containsKey("Price")) {
			price = Long.parseLong(Characteristics.get("Price"));
		} else {
			System.err.println("Incorrect BluePrint");
			return null;
		}
		if (Characteristics.containsKey("Pilot")) {
			pilot = new Pilot(Characteristics.get("Pilot"));
		} else {
			pilot = new Pilot();
		}
		if (Characteristics.containsKey("SerialNumber")) {
			serialNumber = Integer.parseInt(Characteristics.get("SerialNumber"));
			if (NextSerialNumber < serialNumber) {
				NextSerialNumber = serialNumber + 1;
			}
		} else {
			serialNumber = NextSerialNumber++;
		}
		
		Jet returnJet;
		switch(model.charAt(0)) {
			case 'F':
				returnJet = new FighterJet(model,speed,range,price,pilot,serialNumber);
				break;
			case 'C':
				returnJet = new CargoPlane(model,speed,range,price,pilot,serialNumber);
				break;
			case 'J':
				returnJet = new JetImpl(model,speed,range,price,pilot,serialNumber);
				break;
			default:
				System.err.println("Incorrect BluePrint");
				return null;
		}
		return returnJet;
	}

}
