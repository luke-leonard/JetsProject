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

import com.skilldistillery.extrautil.Menu;
import com.skilldistillery.extrautil.MenuFromList;
import com.skilldistillery.extrautil.MenuItem;

public class JetsApplication {
	private Scanner input;
	private AirField airfield;
	private Menu startMenu;
	private Menu mainMenu;

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
		app.writeCSVFile(t, "Assets/test1.txt");
		app.readCSVFile(r, "Assets/test1.txt");
		
		for(int i = 0; i < r.size(); i++) {
			System.out.println(app.strArrToJet(r.get(i)));
		//System.out.print("\n");
		}
		
		app.printStartMenu();
		app.close();
	}

	public JetsApplication() {
		super();
		this.input = new Scanner(System.in);
		this.airfield = new AirField();
		
		Menu.input = this.input;
		
		this.startMenu = new Menu("Start menu");
		startMenu.addItem(new MenuItem("Load Default", this::printUserMenu));
		startMenu.addItem(new MenuFromList("See saved games","Assets/test1.txt",Menu::doNothing));
		startMenu.overrideExit(new MenuItem("Quit", Menu::doNothing));
		

		
		this.mainMenu = new Menu("Main menu");
		mainMenu.addItem(new MenuItem("List fleet", airfield::printFleet));
		mainMenu.addItem(new MenuItem("Fly all Jets", airfield::printFleet));
		mainMenu.addItem(new MenuItem("View fastest jet", airfield::printFleet));
		mainMenu.addItem(new MenuItem("View jet with longest range", airfield::printFleet));
		mainMenu.addItem(new MenuItem("Load all Cargo Jets", airfield::printFleet));
		mainMenu.addItem(new MenuItem("Dogfight!", airfield::printFleet));
		mainMenu.addItem(new MenuItem("Add a jet to Fleet", airfield::printFleet));
		mainMenu.addItem(new MenuItem("Remove a jet from Fleet", airfield::printFleet));

	}
	public void printStartMenu() {
		startMenu.execute();
	}
	
	public void printUserMenu() {
		mainMenu.execute();
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
