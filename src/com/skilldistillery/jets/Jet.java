package com.skilldistillery.jets;

import java.util.HashMap;
import java.util.Map;

public abstract class Jet {
	public static final String speedUnits = "mph";
	public static final String rangeUnits = "miles";
	private static int currentSerial = 0;

	private int generateSerialNumber() {
		return currentSerial++;
	}

	private int serialNumber;

	private String model;
	private double speed;
	private int range;
	private long price;
	
	private Pilot pilot;
	
	public abstract void fly();
	public abstract void kill();
	public abstract String toCSVString();

	public String getTailNumber() {
		return model + "-" + serialNumber;
	}
	
	
	public double getSpeedInMach() {
		return speed * 0.0013;
	}

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public double getSpeed() {
		return speed;
	}

	public void setSpeed(double speed) {
		this.speed = speed;
	}

	public int getRange() {
		return range;
	}

	public void setRange(int range) {
		this.range = range;
	}

	public long getPrice() {
		return price;
	}

	public void setPrice(long price) {
		this.price = price;
	}
	public Integer getSerialNumber() {
		return serialNumber;
	}

	public double timeInAir() {
		return 1 / (speed / range);
	}

	public Jet(String model, double speed, int range, long price) {
		super();
		this.model = model;
		this.speed = speed;
		this.range = range;
		this.price = price;
		this.serialNumber = generateSerialNumber();
		this.pilot = new Pilot();
	}
	public Jet(String model, double speed, int range, long price, Pilot pilot , int serialNumber) {
		super();
		this.model = model;
		this.speed = speed;
		this.range = range;
		this.price = price;
		this.pilot = pilot;
		this.serialNumber = serialNumber;
		if(serialNumber > currentSerial) {
			currentSerial = serialNumber +1;
		}
	}
	
	public Pilot getPilot() {
		return pilot;
	}
	public void setPilot(Pilot pilot) {
		this.pilot = pilot;
	}

}
