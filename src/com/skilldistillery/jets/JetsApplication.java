package com.skilldistillery.jets;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

public class JetsApplication {
	private Scanner input;
	private AirField airfield;
	private JetFactory jetFactory = new JetFactory();
	private BluePrintArchitect architect = new BluePrintArchitect();
	private Map<String, JetBluePrint> bluePrints;

	public static void main(String[] args) {
		JetsApplication app = new JetsApplication();

		app.run();
		app.close();
	}

	public JetsApplication() {
		super();
		this.input = new Scanner(System.in);
		this.airfield = new AirField();
		bluePrints = new HashMap<>();
	}

	public void run() {
		System.out.println("Welcome to Jets");
		System.out.println("Jets is an application that lets you build a fleet, make money, and fight");
		System.out.println("Lets Begin:");
		System.out.println("I will give you some default Jets:");
		load("Assets/default.txt");
		System.out.println();
		runMainMenu();
	}

	public void printMainMenu() {
		System.out.println();
		System.out.println("Current High Score is " + airfield.getHighScore());
		System.out.println("Current Money Suply is " + airfield.getUserMoney());
		System.out.println("1) List fleet");
		System.out.println("2) Fly all jets");
		System.out.println("3) View fastest jet");
		System.out.println("4) View jet with longest range");
		System.out.println("5) Collect Money with cargo jets");
		System.out.println("6) Dogfight!");
		System.out.println("7) Order Jets");
		System.out.println("8) Remove a jet from Fleet");
		System.out.println("9) Quit");
	}

	public void orderJets() {
		boolean running = true;
		while (running) {
			System.out.println("1) Build New Blue Print");
			System.out.println("2) Order");
			System.out.println("3) Quit");
			String userInput = input.nextLine();
			switch (userInput) {
				case "1":
					System.out.print("Name:");
					String name = input.nextLine();
					JetBluePrint jbp = architect.userBuildJet(input);
					bluePrints.put(name, jbp);
					break;
				case "2":
					airfield.printUserMoney();
					if (bluePrints.size() > 0) {
						System.out.println("Current BluePrints:");
						Set<String> keys = bluePrints.keySet();
						for (String key : keys) {
							System.out.println(
									"\t" + key + "    $" + bluePrints.get(key).getJetCharacteristics().get("Price"));
						}
						System.out.print("Enter Name:");
						userInput = input.nextLine();
						System.out.print("How Many:");
						
						int amount = Integer.parseInt(input.nextLine());
						if (bluePrints.containsKey(userInput)) {
							if(Long.parseLong(bluePrints.get(userInput).getJetCharacteristics().get("Price")) * amount < airfield.getUserMoney()) {
								airfield.addJets(jetFactory.buildJets(bluePrints.get(userInput), amount));
								
							}else {
								System.out.println("You cant afford that");
							}
						} else {
							System.out.println("Blue print not Found");
						}
					} else {
						System.out.println("No BluePrints made");
					}
					break;
				case "3":
					running = false;
					break;
				default:
					break;
			}
		}
	}

	public void runMainMenu() {
		boolean running = true;
		while (running) {
			printMainMenu();
			String userInput = input.nextLine();
			switch (userInput) {
				case "1":
					airfield.printFleet();
					break;
				case "2":
					airfield.scramble();
					break;
				case "3":
					airfield.printFastestJet();
					break;
				case "4":
					airfield.printLongestRangeJet();
					break;
				case "5":
					airfield.collectCargo();
					break;
				case "6":
					airfield.dogFight();
					break;
				case "7":
					orderJets();
					break;
				case "8":
					airfield.removeJet(input);
					break;
				case "9":
					running = false;
					break;
				default:
					break;
			}
		}

	}

	public void load(String file) {
		List<JetBluePrint> bluePrints = new ArrayList<>();
		try (BufferedReader br = new BufferedReader(new FileReader(file));) {
			String line = "";
			while((line = br.readLine())!=null) {
				bluePrints.add(new JetBluePrint(line));
			}
		} catch (FileNotFoundException e) {
			System.err.println(e);
			e.printStackTrace();
		} catch (IOException e) {
			System.err.println(e);
			e.printStackTrace();

		}
		for(JetBluePrint bp:bluePrints) {
			airfield.addJet(jetFactory.buildJet(bp));
		}
	}

	public void close() {
		input.close();
	}

}
