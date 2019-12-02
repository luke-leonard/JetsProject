package com.skilldistillery.jets;

public class JetImpl extends Jet {

	public JetImpl(String model, double speed, int range, long price) {
		super(model, speed, range, price);
		// TODO Auto-generated constructor stub
	}

	public JetImpl(String... jetString) {
		super(jetString[0], Double.parseDouble(jetString[1]), Integer.parseInt(jetString[2]),
				Long.parseLong(jetString[3]), ((jetString[4].equals("Empty")) ? Pilot.Empty : new Pilot(jetString[4])),
				Integer.parseInt(jetString[5]));

	}

	@Override
	public void fly() {
		System.out.println("Flying Jet " + getTailNumber());
		System.out.println("I can fly for " + timeInAir() + "Hours");

	}

	@Override
	public void kill() {
		System.out.println("Oh no " + getTailNumber() + " has been hit");

	}

	@Override
	public String toString() {
		return "Jet [ Tail number: " + getModel() + "-" + getSerialNumber() + " Speed: " + getSpeed() + Jet.speedUnits
				+ "(Mach " + getSpeedInMach() + " Range: " + getRange() + Jet.rangeUnits + " Price: $" + getPrice()
				+ " Pilot: " + getPilot() + "]";
	}

	@Override
	public String toCSVString() {
		return getModel() + "," + getSpeed() + "," + getRange() + "," + getPrice() + "," + getPilot().getName() + ","
				+ getSerialNumber();
	}

}
