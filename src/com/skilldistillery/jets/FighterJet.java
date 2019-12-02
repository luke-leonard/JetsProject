package com.skilldistillery.jets;

public class FighterJet extends Jet implements CombatReady {
	int skill;

	public FighterJet(String model, double speed, int range, long price, int skill) {
		super(model, speed, range, price);
		this.skill = skill;
	}

	public FighterJet(String...jetString) {
		super(jetString[0], Double.parseDouble(jetString[1]), Integer.parseInt(jetString[2]),
				Long.parseLong(jetString[3]), ((jetString[4].equals("Empty")) ? Pilot.Empty : new Pilot(jetString[4])),
				Integer.parseInt(jetString[5]));

		this.skill = Integer.parseInt(jetString[6]);
	}

	@Override
	public boolean fight() {
		return (Math.random() * 10) < skill;
	}

	@Override
	public void fly() {
		System.out.println("Flying Fighter " + getTailNumber());
		System.out.println("I can fly for " + timeInAir() + "Hours");
	}

	@Override
	public void kill() {
		System.out.println("Oh no " + getTailNumber() + " has been hit");

	}

	@Override
	public String toString() {
		return "Fighter[ Tail number: " + getModel() + "-" + getSerialNumber() + " Speed: " + getSpeed()
				+ Jet.speedUnits + "(Mach " + getSpeedInMach() + ") Range: " + getRange() + Jet.rangeUnits + " Price: $"
				+ getPrice() + " Pilot: " + getPilot() +"]";
	}

	@Override
	public String toCSVString() {
		return getModel() + "," + getSpeed() + "," + getRange() + "," + getPrice() + "," + getPilot().getName() + ","
				+ getSerialNumber() + "," + skill;
	}
}
