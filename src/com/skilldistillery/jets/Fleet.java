package com.skilldistillery.jets;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;

public class Fleet {
	String name;
	List<Jet> jets;

	public Fleet(String name) {
		super();
		this.name = name;
		this.jets = new ArrayList<>();
	}

	public Jet removeJet(String tailNumber) {
		for (Jet jet : jets) {
			if (jet.getTailNumber().equals(tailNumber)) {
				jets.remove(jet);
				return jet;
			}
		}
		return null;
	}

	public List<Jet> removeAllJets() {
		List<Jet> returnList = new LinkedList<>();
		for (Jet jet : jets) {
				jets.remove(jet);
				returnList.add(jet);
		}
		return returnList;
	}

	public void addJet(Jet jet) {
		jets.add(jet);
	}

	public List<Jet> getJets() {
		return jets;
	}

	public void addJet(String[] jetString) {
		Jet retJet = null;
		try {
			switch (jetString[0].charAt(0)) {
				case 'F':
					retJet = new FighterJet(jetString);
					break;
				case 'C':
					retJet = new CargoPlane(jetString);
					break;
				case 'J':
					retJet = new JetImpl(jetString);
				default:
					System.err.println("\'" + jetString[0] + "\' does not exist.");
			}

		} catch (Exception e) {
			System.err.println(e);
		}

		jets.add(retJet);
	}

	public void printFastestJets() {
		List<Jet> jetsOrderedBySpeed = new ArrayList<>(jets.size());
		for (Jet jet: jets) {
			jetsOrderedBySpeed.add(jet);
		}
		Comparator<Jet> compareBySpeed = (Jet o1, Jet o2) -> (int) (o2.getSpeed() - o1.getSpeed());
		Collections.sort(jetsOrderedBySpeed, compareBySpeed);

		if (jetsOrderedBySpeed.size() == 0) {
			System.out.println( name + " is Empty");
		}
		if (jetsOrderedBySpeed.size() >= 1) {
			System.out.println("The fastest jet is:");
			System.out.println(jetsOrderedBySpeed.get(0));
		}
		if (jetsOrderedBySpeed.size() >= 2) {
			System.out.println("The second fastest jet is:");
			System.out.println(jetsOrderedBySpeed.get(1));
		}
		if (jetsOrderedBySpeed.size() >= 3) {
			System.out.println("The third jet is:");
			System.out.println(jetsOrderedBySpeed.get(2));
		}
	}

	public void printFleet() {
		System.out.println(name + ":");
		if (jets.size() > 0) {
			for (int i = 0; i < jets.size(); i++) {
				if (jets.get(i) != null) {
					System.out.println("\t" + jets.get(i));
				}
			}
		} else {
			System.out.println( name + " is Empty");
		}

	}

	public void printLongestRangeJets() {
		List<Jet> jetsOrderedByRange = new ArrayList<>(jets.size());
		for (Jet jet: jets) {
			jetsOrderedByRange.add(jet);
		}
		Comparator<Jet> compareByRange = (Jet o1, Jet o2) -> (o2.getRange() - o1.getRange());
		Collections.sort(jetsOrderedByRange, compareByRange);

		if (jetsOrderedByRange.size() == 0) {
			System.out.println( name + " is Empty");
		}
		if (jetsOrderedByRange.size() >= 1) {
			System.out.println("The fastest jet is:");
			System.out.println(jetsOrderedByRange.get(0));
		}
		if (jetsOrderedByRange.size() >= 2) {
			System.out.println("The second fastest jet is:");
			System.out.println(jetsOrderedByRange.get(1));
		}
		if (jetsOrderedByRange.size() >= 3) {
			System.out.println("The third jet is:");
			System.out.println(jetsOrderedByRange.get(2));
		}
	}
}
