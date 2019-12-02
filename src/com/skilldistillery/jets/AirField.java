package com.skilldistillery.jets;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.skilldistillery.extrautil.Menu;

public class AirField {
	private Long userMoney;
	private Fleet groundFleet;
	private Fleet airFleet;
	private Fleet KIAFleet;
	private List<Pilot> pilotPool;

	public AirField() {
		this.userMoney = 10_000_000L;
		this.groundFleet = new Fleet("Ground Fleet");
		this.airFleet = new Fleet("Air Fleet");
		this.KIAFleet = new Fleet("KIA Fleet");
		this.pilotPool = new ArrayList<>();
	}

	public AirField(String fileName) {
		int stage = 0;
		this.groundFleet = new Fleet("Ground Fleet");
		this.airFleet = new Fleet("Air Fleet");
		this.KIAFleet = new Fleet("KIA Fleet");
		this.pilotPool = new ArrayList<>();
		String line = "";

		try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
			while ((line = br.readLine()) != null) {
				switch (stage) {
					case 0:
						userMoney = Long.parseLong(line);
						stage++;
						break;
					case 1:
						if (line.equals("Ground Fleet:")) {
							stage++;
							break;
						}
					case 2:
						if (line.equals("Air Fleet:")) {
							stage++;
							break;
						} else {
							this.groundFleet.addJet(line.split(",\\s?"));
							break;
						}
					case 3:
						if (line.equals("KIA Fleet:")) {
							stage++;
							break;
						} else {
							this.airFleet.addJet(line.split(",\\s?"));
							break;
						}
					case 4:
						if (line.equals("Pilots:")) {
							stage++;
							break;
						} else {
							this.KIAFleet.addJet(line.split(",\\s?"));
							break;
						}
					case 5:
						if (line.equals("")) {
							stage++;
							break;
						} else {
							this.pilotPool.add(new Pilot(line));
							break;
						}

					default:
						break;
				}

			}

		} catch (FileNotFoundException e) {
			System.out.println(e);
			e.printStackTrace();
		} catch (IOException e) {
			System.out.println(e);
			e.printStackTrace();
		} catch (Exception e) {
			System.out.println(e);
			e.printStackTrace();
		}
	}

	public void save(String fileName) {
		try (BufferedWriter bw = new BufferedWriter(new FileWriter(fileName))) {

			bw.write(Long.toString(this.userMoney));
			bw.write("\n");
			bw.write("Ground Fleet:\n");
			for (Jet jet : groundFleet.getJets()) {
				bw.write(jet.toCSVString() + "\n");
			}
			bw.write("Air Fleet:\n");
			for (Jet jet : airFleet.getJets()) {
				bw.write(jet.toCSVString() + "\n");
			}
			bw.write("KIA Fleet:\n");
			for (Jet jet : KIAFleet.getJets()) {
				bw.write(jet.toCSVString() + "\n");
			}
			bw.write("Pilots:\n");
			for (Pilot pilot : pilotPool) {
				bw.write(pilot.getName() + "\n");
			}

		} catch (FileNotFoundException e) {
			System.out.println(e);
			e.printStackTrace();
		} catch (IOException e) {
			System.out.println(e);
			e.printStackTrace();
		}
	}

	public void scramble() {
		System.out.println("Scrambling fleet:");
		List<Jet> scrambledFleet = groundFleet.getJets();
		for (Jet jet : scrambledFleet) {
			jet.fly();
			airFleet.addJet(jet);
			airFleet.removeJet(jet.getTailNumber());
		}
	}

	public void flyJet(String tailNumber) {
		Jet scrambledJet = groundFleet.removeJet(tailNumber);
		if (scrambledJet != null) {
			scrambledJet.fly();
			airFleet.addJet(scrambledJet);
		} else {
			System.out.println(tailNumber + " is not a part of this mission.");
		}
	}

	public void killJet(String tailNumber) {
		Jet deadJet = airFleet.removeJet(tailNumber);
		if (deadJet != null) {
			KIAFleet.addJet(deadJet);
		} else {
			System.out.println(tailNumber + " is not a part of this mission.");
		}
	}

	public void killAirFleet() {
		for (Jet jet : airFleet.getJets()) {
			killJet(jet.getTailNumber());
		}
	}

	public void printAllTimeFastestJet() {
		Jet fastestJet = null;
		double fastestSpeed = -1;
		List<Jet> jets = groundFleet.getJets();
		jets.addAll(airFleet.getJets());
		jets.addAll(KIAFleet.getJets());
		for (int i = 0; i < jets.size(); i++) {
			if (jets.get(i) != null) {
				if (jets.get(i).getSpeed() > fastestSpeed) {
					fastestSpeed = jets.get(i).getSpeed();
					fastestJet = jets.get(i);
				}
			}
		}
		if (fastestJet != null) {
			System.out.println(fastestJet);
		} else {
			System.out.println("There AirField is Empty");
		}

	}

	public void printAllTimeLongestRangeJet() {
		Jet longestRangeJet = null;
		int longestRange = -1;
		List<Jet> jets = groundFleet.getJets();
		jets.addAll(airFleet.getJets());
		jets.addAll(KIAFleet.getJets());
		for (int i = 0; i < jets.size(); i++) {
			if (jets.get(i) != null) {
				if (jets.get(i).getRange() > longestRange) {
					longestRange = jets.get(i).getRange();
					longestRangeJet = jets.get(i);
				}
			}
		}
		if (longestRangeJet != null) {
			System.out.println(longestRangeJet);
		} else {
			System.out.println("There AirField is Empty");
		}

	}

	public void jetBuilder() {
		System.out.print("Fighter(F), Cargo(C), or Regular Jet(J):");
		String Type = Menu.input.nextLine();

		System.out.print("Model:");
		String model = Menu.input.nextLine();

		System.out.print("Speed:");
		String Speed = Menu.input.nextLine();
		double speed = Double.parseDouble(Speed);

		System.out.print("Range:");
		String Range = Menu.input.nextLine();
		int range = Integer.parseInt(Range);

		System.out.print("Skill or cargoSpace(if apply):");
		String Other = Menu.input.nextLine();
		int other = Integer.parseInt(Other);

		long cost = (long) speed * range * other;
		System.out.println("This will cost " + cost);

		userMoney -= cost;

		char type;
		switch (Type) {
			case "F":
				type = 'F';
				groundFleet.addJet(new FighterJet((type + model), speed, range, cost, other));
				break;
			case "C":
				type = 'C';
				groundFleet.addJet(new CargoPlane((type + model), speed, range, cost, other));
				break;
			default:
				type = 'J';
				groundFleet.addJet(new JetImpl((type + model), speed, range, cost));

				break;
		}

	}

	public void causeAction() {
		boolean win = false;
		for (Jet jet : airFleet.getJets()) {
			if (jet instanceof FighterJet) {
				if (((FighterJet) jet).fight()) {
					win = true;
				}else {
					killJet(jet.getTailNumber());
				}
			}
		}
		if (win) {
			for (Jet jet : airFleet.getJets()) {
				if (jet instanceof CargoPlane) {
					userMoney += ((CargoPlane) jet).redeemCargo();
				}
			}
		} else {
			//killAirFleet();
		}
		for (Jet jet : airFleet.getJets()) {
			landJet(jet.getTailNumber());
		}
	}

	
	public void landJet(String tailNumber) {
		Jet scrambledJet = airFleet.removeJet(tailNumber);
		if (scrambledJet != null) {
			groundFleet.addJet(scrambledJet);
		} else {
			System.out.println(tailNumber + " is not a part of this mission.");
		}
	}
	
	public Fleet getGroundFleet() {
		return groundFleet;
	}

	public void setGroundFleet(Fleet groundFleet) {
		this.groundFleet = groundFleet;
	}

	public Fleet getAirFleet() {
		return airFleet;
	}

	public void setAirFleet(Fleet airFleet) {
		this.airFleet = airFleet;
	}

	public Fleet getKIAFleet() {
		return KIAFleet;
	}

	public void setKIAFleet(Fleet kIAFleet) {
		KIAFleet = kIAFleet;
	}

	public Long getUserMoney() {
		return userMoney;
	}

	public void printUserMoney() {
		System.out.println(userMoney);
	}

	public void setUserMoney(Long userMoney) {
		this.userMoney = userMoney;
	}

}
