package com.skilldistillery.jets;

import java.util.ArrayList;
import java.util.List;

public class AirField {
	private List<Jet> jets;
	
	
	public AirField() {
		this.jets = new ArrayList<Jet>();
	}
	
	public void printFleet() {
		System.out.println("Printing fleet:");
		for (int i = 0; i < jets.size(); i++) {
			if(jets.get(i) != null) {
				System.out.println(jets.get(i));
			}
		}
		System.out.println("End of fleet");
	}

}
