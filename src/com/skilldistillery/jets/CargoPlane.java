package com.skilldistillery.jets;

public class CargoPlane extends Jet implements CargoCarrier {
	public static final int cargoValue = 1_000_000;
	int capacity;
	boolean full;

	public CargoPlane(String model, double speed, int range, long price, int capacity) {
		super(model, speed, range, price);
		this.capacity = capacity;
		this.full = false;
	}

	public CargoPlane(String... jetString) {
		super(jetString[0], Double.parseDouble(jetString[1]), Integer.parseInt(jetString[2]),
				Long.parseLong(jetString[3]), ((jetString[4].equals("Empty")) ? Pilot.Empty : new Pilot(jetString[4])),
				Integer.parseInt(jetString[5]));

		this.capacity = Integer.parseInt(jetString[6]);
		this.full = false;
	}

	@Override
	public void loadCargo() {
		System.out
				.println(getModel() + "-" + getSerialNumber() + " loaded $" + (capacity * cargoValue) + " in supplies");
		full = true;
	}

	@Override
	public int redeemCargo() {
		if (full) {
			System.out.println(
					getModel() + "-" + getSerialNumber() + " cashed $" + (capacity * cargoValue) + " in supplies");
			full = false;
			return cargoValue * capacity;
		}
		System.out.println("No cargo loaded");
		return 0;
	}

	@Override
	public void fly() {
		loadCargo();
		System.out.println("Flying Cargo Plane " + getTailNumber());
		System.out.println("I can fly for " + timeInAir() + "Hours");

	}

	@Override
	public void kill() {
		System.out.println("Oh no " + getTailNumber() + " has been hit");
	}

	@Override
	public String toString() {
		return "Cargo[ Tail number: " + getModel() + "-" + getSerialNumber() + " Speed: " + getSpeed() + Jet.speedUnits
				+ "(Mach " + getSpeedInMach() + ") Range: " + getRange() + Jet.rangeUnits + " Price: $" + getPrice()
				+ " Pilot: " + getPilot() + "]";
	}

	@Override
	public String toCSVString() {
		return getModel() + "," + getSpeed() + "," + getRange() + "," + getPrice() + "," + getPilot().getName() + ","
				+ getSerialNumber() + "," + capacity;
	}
}
