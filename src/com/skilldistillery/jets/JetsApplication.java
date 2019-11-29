package com.skilldistillery.jets;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Scanner;

public class JetsApplication {
	private Scanner input;
	private AirField airfield;

	public static void main(String[] args) {
		JetsApplication app = new JetsApplication();

//		ArrayList<String[]> t = new ArrayList<>();
//		ArrayList<String[]> a = new ArrayList<>();
//		String[] stuff = {"I","am","awesome"};
//		t.add(stuff);
//		t.add(stuff);
//		t.add(stuff);
//		t.add(stuff);
//		
//		app.writeCSVFile(t, "test1.txt");
//		app.readCSVFile(a, "test1.txt");
//		
//		for(int i = 0; i < a.size(); i++) {
//			for(int j = 0; j < a.get(i).length;j++) {
//				System.out.println(a.get(i)[j]);
//			}
//			//System.out.print("\n");
//		}
		ArrayList<String[]> t = new ArrayList<>();
		ArrayList<String[]> r = new ArrayList<>();

		Jet a = new CargoPlane("F-22",100.0,322,10000000L);
		String[] b = app.JetToStrArr(a);
		t.add(b);
		t.add(b);
		t.add(b);
		t.add(b);
		t.add(b);
		app.writeCSVFile(t, "test1.txt");
		app.readCSVFile(r, "test1.txt");
		
		for(int i = 0; i < r.size(); i++) {
			System.out.println(app.strArrToJet(r.get(i)));
		//System.out.print("\n");
		}
		
		
		
		app.close();
	}

	public JetsApplication() {
		super();
		this.input = new Scanner(System.in);
		this.airfield = new AirField();
	}

	public void printMenu() {
		System.out.println("1) List fleet");
		System.out.println("2) Fly all Jets");
		System.out.println("3) View fastest jet");
		System.out.println("4) View jet with longest range");
		System.out.println("5) Load all Cargo Jets");
		System.out.println("6) Dogfight!");
		System.out.println("7) Add a jet to Fleet");
		System.out.println("8) Remove a jet from Fleet");
		System.out.println("9) Quit");
	}

	public String userInput(String prompt) {
		System.out.println(prompt);
		return userInput();
	}

	public String userInput() {
		return input.next();
	}

	public String[] JetToStrArr(Jet jet) {
		String[] strArr = new String[Jet.argsInJet + 1];

		if(jet instanceof FighterJet) {
			strArr[0] = "FIGHTER";
		}
		if(jet instanceof CargoPlane) {
			strArr[0] = "CARGO";
		}
		strArr[1] = jet.getModel();
		strArr[2] = "" + jet.getSpeed();
		strArr[3] = "" + jet.getRange();
		strArr[4] = "" + jet.getPrice();
		
		
		return strArr;
	}
	public Jet strArrToJet(String[] jetString) {
		Jet retJet = null;

		if (jetString.length == Jet.argsInJet + 1) {
			switch (jetString[0].toUpperCase()) {
				case "FIGHTER":
					retJet = new FighterJet(jetString[1], Double.parseDouble(jetString[2]),
							Integer.parseInt(jetString[3]), Long.parseLong(jetString[4]));
					break;
				case "CARGO":
					retJet = new CargoPlane(jetString[1], Double.parseDouble(jetString[2]),
							Integer.parseInt(jetString[3]), Long.parseLong(jetString[4]));
					break;
				default:
					System.err.println("\'" + jetString[0].toUpperCase() + "\' does not exist.");
			}

		} else {
			System.err.println("Incorrect number of arguments{" + jetString.length + "a:" + Jet.argsInJet + "e}");
		}
		return retJet;
	}

	public void readCSVFile(Collection<String[]> c, String file) {
		try (BufferedReader br = new BufferedReader(new FileReader(file))) {
			String line = "";
			while ((line = br.readLine()) != null) {
				c.add(line.split(",\\s?"));
			}
		} catch (FileNotFoundException e) {
			System.out.println(e);
			e.printStackTrace();
		} catch (IOException e) {
			System.out.println(e);
			e.printStackTrace();
		}

	}

	public void writeCSVFile(List<String[]> list, String file) {
		try (BufferedWriter bw = new BufferedWriter(new FileWriter(file))) {
			for (int i = 0; i < list.size(); i++) {
				for (int j = 0; j < list.get(i).length; j++) {
					bw.write(list.get(i)[j] + ",");
				}
				bw.write("\n");
			}

		} catch (FileNotFoundException e) {
			System.out.println(e);
			e.printStackTrace();
		} catch (IOException e) {
			System.out.println(e);
			e.printStackTrace();
		}

	}

	public void close() {
		input.close();
	}

}
