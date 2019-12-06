package com.skilldistillery.jets;

public class JetImpl extends Jet {

	public JetImpl(String model, double speed, int range, long price,Pilot pilot,int serialNumber) {
		super(model, speed, range, price,pilot,serialNumber);
	}

	@Override
	public void fly() {
		System.out.println("Flying Jet " + getTailNumber());
		System.out.println("I can fly for " + timeInAir() + "Hours");

	}

	@Override
	public String toString() {
		return "Jet [ Tail number: " + getModel() + "-" + getSerialNumber() + " Speed: " + getSpeed() + Jet.speedUnits
				+ "(Mach " + getSpeedInMach() + " Range: " + getRange() + Jet.rangeUnits + " Price: $" + getPrice()
				+ " Pilot: " + getPilot() + "]";
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

}
