package com.skilldistillery.jets;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class AirField {
	private final long timeDelay = 500; 
	private Long userMoney;
	private List<Jet> fleet;
	private int highScore = 0;

	
	public void waitAMoment() {
		long start = System.currentTimeMillis();
		while (start + timeDelay > System.currentTimeMillis()) {};
	}
	

	public AirField() {
		this.userMoney = 10_000L;
		this.fleet = new ArrayList<>();
	}
	public void addJet(Jet jet) {
		fleet.add(jet);
		System.out.println(jet.getTailNumber() + " added");
		userMoney -= jet.getPrice();
	
	}
	public void addJets(List<Jet> jets) {
		for(Jet jet: jets) {
			addJet(jet);
		}
	}
	
	
	public void removeJet(Scanner input) {
		System.out.println("Jets to remove:");
		for (Jet jet: fleet) {
			if (jet != null) {
				System.out.println("\t" + jet.getTailNumber());
			}
		}
		System.out.println("Enter Tail Number:");
		String tailNumber = input.nextLine();
		for(Jet jet: fleet) {
			if(jet.getTailNumber().equals(tailNumber)) {
				fleet.remove(jet);
				System.out.println(tailNumber + " removed");
				return;
			}
		}
		System.out.println(tailNumber + "is not in the airfield");
	}
	

	public void scramble() {
		System.out.println("Scrambling fleet:");
		for (Jet jet : fleet) {
			jet.fly();
		}
	}

	public void printFleet() {

		for (Jet jet: fleet) {
			if (jet != null) {
				System.out.println(jet);
			}
		}
	}
	

	public void printFastestJet() {
		Jet fastestJet = null;
		double fastestSpeed = Double.MIN_VALUE;
		for (Jet jet: fleet) {
			if (jet != null) {
				if (jet.getSpeed() > fastestSpeed) {
					fastestSpeed = jet.getSpeed();
					fastestJet = jet;
				}
			}
		}
		if (fastestJet != null) {
			System.out.println(fastestJet);
		} else {
			System.out.println("There AirField is Empty");
		}

	}

	public void printLongestRangeJet() {
		Jet longestRangeJet = null;
		int longestRange = Integer.MIN_VALUE;
		for (Jet jet: fleet) {
			if (jet != null) {
				if (jet.getRange() > longestRange) {
					longestRange = jet.getRange();
					longestRangeJet = jet;
				}
			}
		}
		if (longestRangeJet != null) {
			System.out.println(longestRangeJet);
		} else {
			System.out.println("There AirField is Empty");
		}

	}

	

	public void dogFight() {
		List<CombatReady> fighters = new ArrayList<>();
		for (Jet jet: fleet) {
			if (jet != null) {
				if (jet instanceof CombatReady) {
					fighters.add((CombatReady)jet);
				}
			}
		}
		int score = 0;
		for(CombatReady fighter:fighters) {
			if(fighter.fight()) {
				System.out.println(((Jet)fighter).getTailNumber() + " won.");
				score++;
			}else {
				System.out.println(((Jet)fighter).getTailNumber() + " lost.");
			}
			waitAMoment();
		}
		
		System.out.println("You won " +score + " times");
		if( score > highScore) {
			System.out.println("New High Score!!");
			highScore = score;
		};
	}
	
	public void collectCargo() {
		List<CargoCarrier> cargoCarriers = new ArrayList<>();
		for (Jet jet: fleet) {
			if (jet != null) {
				if (jet instanceof CargoCarrier) {
					cargoCarriers.add((CargoCarrier)jet);
				}
			}
		}
		long haul = 0;
		for(CargoCarrier cargoCarrier:cargoCarriers) {
			cargoCarrier.loadCargo();
			waitAMoment();
			haul+=cargoCarrier.redeemCargo();
			waitAMoment();
		}
		
		System.out.println("You made $" + haul);
		userMoney+= haul;
	}

	

	public Long getUserMoney() {
		return userMoney;
	}

	public void printUserMoney() {
		System.out.println("You have $" + userMoney);
	}

	public void setUserMoney(Long userMoney) {
		this.userMoney = userMoney;
	}
	public int getHighScore() {
		return highScore;
	}
	public void setHighScore(int highScore) {
		this.highScore = highScore;
	}

}
