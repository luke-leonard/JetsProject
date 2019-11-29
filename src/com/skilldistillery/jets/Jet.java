package com.skilldistillery.jets;

public abstract class Jet {
	public static final int argsInJet = 4;
	
	private String model;
	private double speed;
	private int range;
	private long price;
	
	public abstract void fly();
	public abstract double getSpeedInMach();
	
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
	public Jet(String model, double speed, int range, long price) {
		super();
		this.model = model;
		this.speed = speed;
		this.range = range;
		this.price = price;
	}
	@Override
	public String toString() {
		return "Jet: {\n\tmodel = " + model + "\n\tspeed = " + speed + "\n\trange = " + range + "\n\tprice = " + price
				 + "\n}";
	}
}
