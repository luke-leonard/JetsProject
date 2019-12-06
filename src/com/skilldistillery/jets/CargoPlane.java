package com.skilldistillery.jets;

public class CargoPlane extends Jet implements CargoCarrier {
	public static final double cargoValue = 0.5;

	public CargoPlane(String model, double speed, int range, long price, Pilot pilot, int serialNumber) {
		super(model, speed, range, price, pilot, serialNumber);

	}

	@Override
	public void loadCargo() {
		System.out.println(getTailNumber() + " loaded $" + (getSpeed() * getRange() * cargoValue) + " in supplies");

	}

	@Override
	public long redeemCargo() {

		System.out.println(getTailNumber() + " cashed $" + (getSpeed() * getRange() * cargoValue) + " in supplies");
		return (long)(getSpeed() * getRange() * cargoValue);

	}

	@Override
	public JetBluePrint toBluePrint() {
		JetBluePrint jbp = new JetBluePrint();

		jbp.addChacteristic("Model", getModel());
		jbp.addChacteristic("Speed", getSpeed());
		jbp.addChacteristic("Range", getRange());
		jbp.addChacteristic("Price", getPrice());
		jbp.addChacteristic("Pilot", getPilot());
		jbp.addChacteristic("SerialNumber", getSerialNumber());

		return jbp;
	}

	@Override
	public void fly() {
		loadCargo();
		System.out.println("Flying Cargo Plane " + getTailNumber());
		System.out.println("I can fly for " + timeInAir() + "Hours");

	}

	@Override
	public String toString() {
		return "Cargo[ Tail number: " + getModel() + "-" + getSerialNumber() + " Speed: " + getSpeed() + Jet.speedUnits
				+ "(Mach " + getSpeedInMach() + ") Range: " + getRange() + Jet.rangeUnits + " Price: $" + getPrice()
				+ " Pilot: " + getPilot() + "]";
	}

}
